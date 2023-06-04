package com.adrian.backenddevtest.controller;

import com.adrian.backenddevtest.mapper.ProductRestMapper;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import com.adrian.backenddevtest.usecase.GetProductSimilarUseCase;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductApi;
import org.openapitools.model.ProductDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductRestMapper productRestMapper;

    private final GetProductSimilarUseCase getProductSimilarUseCase;

    @Override
    public ResponseEntity<Set<ProductDetailDTO>> getProductSimilar(final String productId) {
        final GetProductSimilarQuery query = new GetProductSimilarQuery(productId);
        return ResponseEntity.ok().body(productRestMapper.toDto(getProductSimilarUseCase.getProductSimilar(query)));
    }

}
