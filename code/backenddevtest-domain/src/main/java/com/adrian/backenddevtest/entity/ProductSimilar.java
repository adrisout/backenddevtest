package com.adrian.backenddevtest.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProductSimilar implements Serializable {

    private final String id;

    private final String name;

    private final BigDecimal price;

    private final Boolean availability;
}
