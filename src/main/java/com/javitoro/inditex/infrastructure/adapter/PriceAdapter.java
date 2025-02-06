package com.javitoro.inditex.infrastructure.adapter;

import com.javitoro.inditex.domain.port.PricePort;
import com.javitoro.inditex.domain.model.Price;
import com.javitoro.inditex.infrastructure.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceAdapter implements PricePort {

    private final PriceRepository priceRepository;

    public PriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> findApplicablePrices(int productId, int brandId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrices(productId, brandId, applicationDate);
    }
}