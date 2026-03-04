package com.company.Catalog.repository;

import com.company.Catalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CatalogRepository extends JpaRepository <Catalog,Long> {

}
