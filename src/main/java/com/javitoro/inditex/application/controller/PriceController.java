package com.javitoro.inditex.application.controller;

import com.javitoro.inditex.domain.model.Price;
import com.javitoro.inditex.application.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam int productId,
            @RequestParam int brandId,
            @RequestParam String applicationDate) {

        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Optional<Price> price = priceService.getApplicablePrice(productId, brandId, dateTime);

        return price.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
