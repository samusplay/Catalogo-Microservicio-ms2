package com.company.Catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "processed_events")
//tabla para que no haya duplicados
public class ProcessedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    //campo para no duplicar
    private String eventId;
    //Agregar mas campos si es necesario
}
