package com.business.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.backend.model.Product;
import com.business.backend.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product addProduct(@RequestBody Product product) {
        return inventoryService.addProduct(product);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/low-stock")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Product> getLowStockProducts() {
        return inventoryService.getLowStockProducts();
    }

    @PutMapping("/{id}/stock")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Product updateStock(@PathVariable String id, @RequestParam int change) {
        return inventoryService.updateStock(id, change);
    }
}
