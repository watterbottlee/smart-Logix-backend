package com.mover.services;

import com.mover.payloads.apirequests.OrderRequest;

import java.math.BigDecimal;

public interface PriceGenerator {
    BigDecimal generatePrice(OrderRequest orderRequest);
}
