package com.adrian.backenddevtest.usecase.product;

import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.repository.ProductRepository;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import com.adrian.backenddevtest.usecase.GetProductSimilarUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductSimilarUseCaseImpl implements GetProductSimilarUseCase {

    private final ProductRepository repository;

    @Override
    @Cacheable(value = "product_similar_cache", key = "#query.productId")
    public Set<ProductSimilar> getProductSimilar(final GetProductSimilarQuery query) {
        Set<String> similarProducts = this.repository.findSimilarProducts(query);
        log.debug("similarProducts: {}", similarProducts);
        return similarProducts.stream().map(this.repository::findProductDetail).collect(Collectors.toSet());
    }

}
