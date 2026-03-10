package com.company.Catalog.listener;

import com.company.Catalog.config.RabbitMQConfig;
import com.company.Catalog.events.OrderCancelledEvent;
import com.company.Catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCancelledEventListener {

    private final CatalogService catalogService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CANCELLED_QUEUE)
    public void handleOrderCancelled(OrderCancelledEvent event) {
        System.out.println("--- Evento de cancelación recibido ---");
        System.out.println("OrderId: " + event.getOrderId());
        System.out.println("ProductId: " + event.getProductId());
        System.out.println("Cantidad a reponer: " + event.getQuantity());

        try {
            catalogService.reponerStock(event.getProductId(), event.getQuantity());
            System.out.println("Stock restaurado exitosamente para producto: " + event.getProductId());
        } catch (Exception e) {
            System.err.println("Error al restaurar stock: " + e.getMessage());
        }
    }
}
