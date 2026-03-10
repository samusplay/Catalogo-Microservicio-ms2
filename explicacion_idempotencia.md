# Explicación Técnica: Manejo de Colas e Idempotencia en Catálogo

Este documento explica cómo el microservicio de Catálogo gestiona los eventos de RabbitMQ, asegura la integridad de los datos mediante transacciones y evita operaciones duplicadas (Idempotencia).

## 1. Escucha de Eventos (RabbitMQ Listener)

El punto de entrada para los eventos de orden creada es la clase `OrderCreatedEventListener`.

- **Archivo**: `OrderCreatedEventListener.java`
- **Lógica**: Utiliza la anotación `@RabbitListener` para escuchar la cola `order.created.queue`. Cuando llega un mensaje, Spring lo convierte automáticamente de JSON al objeto `OrderCreatedEvent` y llama al método `listener`.

```java
@RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
public void listener(OrderCreatedEvent event) {
    // Llama al servicio para procesar la lógica de negocio e idempotencia
    catalogService.processOrderCreated(event);
}
```

## 2. Lógica de Idempotencia y Transaccionalidad

La lógica principal reside en `CatalogServiceImpl.processOrderCreated`.

- **Archivo**: `CatalogServiceImpl.java`
- **Anotación `@Transactional`**: Es CRUCIAL. Asegura que tanto el descuento de stock como el registro del evento procesado ocurran como una única unidad atómica. Si algo falla (ej. error al guardar en la tabla de eventos), el descuento de stock se deshace (Rollback).

### Flujo de Procesamiento:

1.  **Verificación de Idempotencia**: Antes de hacer nada, consultamos la tabla `processed_events` usando el `eventId` único que viene en el evento.
    ```java
    if (processedEventRepository.existsByEventId(eventId)) {
        log.warn("Evento duplicado detectado. Omitiendo...");
        return; // Salimos sin hacer nada, el mensaje se considera "procesado"
    }
    ```
2.  **Operación de Negocio**: Si el evento no ha sido procesado, llamamos a `descontarStock`.
3.  **Registro del Evento**: Guardamos el `eventId` en la tabla `processed_events`. Debido a la restricción `unique = true` en la entidad `ProcessedEvent`, si dos hilos intentaran registrar el mismo ID simultáneamente, la base de datos lanzaría un error y la transacción fallaría, manteniendo la consistencia.

## 3. Entidades y Repositorios

- **ProcessedEvent**: Entidad JPA que mapea a la tabla `processed_events`. Su campo `eventId` es único.
- **ProcessedEventRepository**: Proporciona el método `existsByEventId` para la verificación rápida.

## 4. Resumen del Mecanismo de Seguridad

| Componente | Función |
| :--- | :--- |
| `eventId` | Identificador único universal generado por el emisor del evento. |
| `processed_events` | Tabla que actúa como "memoria" de lo que ya se hizo. |
| `@Transactional` | Garantiza que "o se hace todo o no se hace nada". |
| `existsByEventId` | El guardia que impide que una orden descuente stock dos veces. |
