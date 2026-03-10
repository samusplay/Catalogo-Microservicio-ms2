package com.company.Catalog.service.impl;

import com.company.Catalog.entity.Catalog;
import com.company.Catalog.entity.ProcessedEvent;
import com.company.Catalog.events.OrderCreatedEvent;
import com.company.Catalog.repository.CatalogRepository;
import com.company.Catalog.repository.ProcessedEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Limpia la base de datos después de cada test
public class IdempotencyIntegrationTest {

    @Autowired
    private CatalogServiceImpl catalogService;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProcessedEventRepository processedEventRepository;

    private Long savedProductId;
    private final String DUPLICATE_EVENT_ID = "evt-12345";

    @BeforeEach
    void setUp() {
        // Crear un producto inicial con stock 10
        Catalog product = new Catalog();
        product.setName("Producto Test");
        product.setPrecio(100.0);
        product.setStock(10);
        Catalog saved = catalogRepository.save(product);
        savedProductId = saved.getId();
    }

    @Test
    void testProcessOrderCreated_Idempotency() {
        // 1. Preparar el evento
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId(DUPLICATE_EVENT_ID);
        event.setProductId(savedProductId);
        event.setQuantity(2);

        // 2. Procesar el evento por primera vez
        catalogService.processOrderCreated(event);

        // Verificar que el stock bajó de 10 a 8
        Catalog p1 = catalogRepository.findById(savedProductId).orElseThrow();
        assertEquals(8, p1.getStock(), "El stock debería ser 8 después del primer procesamiento");
        assertTrue(processedEventRepository.existsByEventId(DUPLICATE_EVENT_ID), "El evento debería estar registrado");

        // 3. Procesar el MISMO evento por segunda vez
        catalogService.processOrderCreated(event);

        // Verificar que el stock SIGUE en 8 y no bajó a 6
        Catalog p2 = catalogRepository.findById(savedProductId).orElseThrow();
        assertEquals(8, p2.getStock(), "El stock NO debería haber bajado de nuevo (Idempotencia)");
    }

    @Test
    void testProcessOrderCreated_DatabaseConstraint() {
        // Simular un registro manual en la tabla de eventos procesados
        ProcessedEvent pe = new ProcessedEvent();
        pe.setEventId("manual-id");
        processedEventRepository.save(pe);

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId("manual-id");
        event.setProductId(savedProductId);
        event.setQuantity(1);

        // Al intentar procesar, debería detectar el duplicado y saltar la lógica
        catalogService.processOrderCreated(event);

        Catalog p = catalogRepository.findById(savedProductId).orElseThrow();
        assertEquals(10, p.getStock(), "El stock no debe cambiar si el evento ya existe en BD");
    }
}
