package com.business.backend.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.business.backend.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ $expr: { $lt: [ '$quantity', '$minThreshold' ] } }")
    List<Product> findLowStockProducts();
}
