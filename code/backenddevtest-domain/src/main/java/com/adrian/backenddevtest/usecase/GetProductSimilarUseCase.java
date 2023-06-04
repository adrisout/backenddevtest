package com.adrian.backenddevtest.usecase;

import com.adrian.backenddevtest.entity.ProductSimilar;

import java.util.Set;

public interface GetProductSimilarUseCase {

    Set<ProductSimilar> getProductSimilar(GetProductSimilarQuery query);
}
