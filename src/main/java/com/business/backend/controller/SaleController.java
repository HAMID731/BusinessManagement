package com.business.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.backend.model.Sale;
import com.business.backend.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Sale logSale(@RequestBody Sale sale) {
        return saleService.logSale(sale);
    }

    @GetMapping("/today")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Sale> getTodaySales() {
        return saleService.getTodaySales();
    }

    @GetMapping("/export")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> exportSales() {
        List<Sale> sales = saleService.getTodaySales();
        String csv = saleService.exportSalesToCsv(sales);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sales.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}
