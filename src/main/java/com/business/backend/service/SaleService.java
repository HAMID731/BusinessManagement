package com.business.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.backend.model.Sale;
import com.business.backend.repository.SaleRepository;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Sale logSale(Sale sale) {
        sale.setSaleDate(LocalDateTime.now());
        return saleRepository.save(sale);
    }

    public List<Sale> getTodaySales() {
        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return saleRepository.findBySaleDateBetween(start, end);
    }
    
    public String exportSalesToCsv(List<Sale> sales) {
        StringBuilder sb = new StringBuilder();
        sb.append("Item Name,Quantity,Price,Date\n");
        for (Sale sale : sales) {
            sb.append(sale.getItemName()).append(",")
              .append(sale.getQuantity()).append(",")
              .append(sale.getPrice()).append(",")
              .append(sale.getSaleDate()).append("\n");
        }
        return sb.toString();
    }
}
