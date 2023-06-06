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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
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
                log.error(e.getMessage());
                throw new RestClientException("Error when retrieving similar products");
            }
        }
    }

        @Override
        public ProductSimilar findProductDetail (String productId){
            try {
                return this.productMapper.toDomain(this.productApi.getProductProductId(productId));
            } catch (ApiException e) {
                if (404 == e.getCode()) {
                    throw new ProductNotFoundException(productId);
                }else {
                    log.error(e.getMessage());
                    throw new RestClientException("Error when retrieving product detail information");
                }
            }
        }

    }
