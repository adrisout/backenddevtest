package com.adrian.backenddevtest.persistence.impl;

import com.adrian.backenddevtest.api.ApiException;
import com.adrian.backenddevtest.api.client.ProductApi;
import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.exception.ProductNotFoundException;
import com.adrian.backenddevtest.exception.RestClientException;
import com.adrian.backenddevtest.persistence.mapper.ProductMapper;
import com.adrian.backenddevtest.repository.ProductRepository;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductMapper productMapper;

    private final ProductApi productApi;

    @Override
    public Set<String> findSimilarProducts(GetProductSimilarQuery query) {
        try {
            return this.productApi.getProductSimilarids(query.getProductId());
        } catch (ApiException e) {
            if (404 == e.getCode()) {
                throw new ProductNotFoundException(query.getProductId());
            } else {
                throw new RestClientException(this.productApi.getClass().getName());
            }
        }
    }

        @Override
        public ProductSimilar findProductDetail (String productId){
            try {
                return this.productMapper.toDomain(this.productApi.getProductProductId(productId));
            } catch (ApiException e) {
                throw new RestClientException(this.productApi.getClass().getSimpleName());
            }
        }

    }
