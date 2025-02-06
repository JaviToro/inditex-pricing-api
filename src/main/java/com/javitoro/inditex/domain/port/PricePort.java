package com.javitoro.inditex.domain.port;

import com.javitoro.inditex.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePort {
    List<Price> findApplicablePrices(int productId, int brandId, LocalDateTime applicationDate);
}