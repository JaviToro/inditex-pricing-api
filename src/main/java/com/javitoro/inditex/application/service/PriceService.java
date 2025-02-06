package com.javitoro.inditex.application.service;

import com.javitoro.inditex.domain.port.PricePort;
import com.javitoro.inditex.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PricePort pricePort;

    public PriceService(PricePort pricePort) {
        this.pricePort = pricePort;
    }

    public Optional<Price> getApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        List<Price> prices = pricePort.findApplicablePrices(productId, brandId, applicationDate);
        return prices.stream().findFirst();
    }
}