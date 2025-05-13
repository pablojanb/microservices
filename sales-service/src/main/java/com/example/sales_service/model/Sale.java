package com.example.sales_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long sale_id;
    @Temporal(TemporalType.DATE)
    private LocalDate sale_date;
    private Long cart_id;
    @ElementCollection
    private List<Long> productsList;
    private double sale_total;
}
