package com.example.sales_service.repository;

import com.example.sales_service.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s ORDER BY s.sale_total DESC")
    List<Sale> getBiggestSale();
}
