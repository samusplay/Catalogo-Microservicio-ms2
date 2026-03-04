package com.company.Catalog.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener {
    //inyectar logica de negocio  ose CatalogService

    //utilizar @Rabbitlistener
    //crear metodo que va recibir la solicitud
    //dicho metodo luego de recibir el evento debe pasarle la responsabilidad al servicio
    //// Asegúrate de importar tu clase OrderCreatedEvent
    //void processOrderCreated(OrderCreatedEvent event);

    //luego crear en CatalogService el metodo para llamar el metodo y pasar impl con criterios de aceptacion

}
