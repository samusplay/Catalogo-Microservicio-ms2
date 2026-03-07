package com.company.Catalog.listener;

import com.company.Catalog.config.RabbitMQConfig;
import com.company.Catalog.events.OrderCreatedEvent;
import com.company.Catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventListener {
    private final CatalogService catalogService;

    // metodo de escucha de los mensajes en cola.
    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
    public void listener(OrderCreatedEvent event) {
        log.info("Mensaje recibido desde RabbitMQ para el evento: {}", event.getEventId());

        // descontar stock e idempotencia
        catalogService.processOrderCreated(event);
    }

}
