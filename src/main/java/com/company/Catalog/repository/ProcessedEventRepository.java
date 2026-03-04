package com.company.Catalog.repository;

import com.company.Catalog.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent,Long> {
    //metodo para procesar el eventId
    boolean existsByEventId(String eventId);
}
