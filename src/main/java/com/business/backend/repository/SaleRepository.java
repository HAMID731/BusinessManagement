package com.business.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.backend.model.Sale;

public interface SaleRepository extends MongoRepository<Sale, String> {
    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);
}
