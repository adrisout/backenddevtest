package com.adrian.backenddevtest.repository;

import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;

import java.util.Set;

public interface ProductRepository {

    Set<String> findSimilarProducts(GetProductSimilarQuery query);

    ProductSimilar findProductDetail(String productId);
}
