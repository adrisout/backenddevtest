package com.adrian.backenddevtest.persistence.impl;

import com.adrian.backenddevtest.api.ApiException;
import com.adrian.backenddevtest.api.client.ProductApi;
import com.adrian.backenddevtest.api.model.ProductDetailDTO;
import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.exception.ProductNotFoundException;
import com.adrian.backenddevtest.exception.RestClientException;
import com.adrian.backenddevtest.persistence.mapper.ProductMapperImpl;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    public static final String PRODUCT_ID_1 = "2";
    public static final String PRODUCT_NAME_1 = "Boots";
    public static final BigDecimal PRODUCT_PRICE_1 = BigDecimal.valueOf(25);

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Mock
    private ProductApi productApi;

    @Spy
    private ProductMapperImpl productMapper;

    @SneakyThrows
    @Test
    void getProductSimilarIds_whenQueryDataOk_returnSimilarIds() {
        GetProductSimilarQuery query = new GetProductSimilarQuery("1");
        Set<String> similarIds = Set.of("2", "3", "4");

        doReturn(similarIds).when(this.productApi).getProductSimilarids("1");

        Set<String> response = this.productRepository.findSimilarProducts(query);

        assertNotNull(response);
        assertEquals(3, response.size());
        verify(this.productApi, times(1)).getProductSimilarids(anyString());

    }


    @SneakyThrows
    @Test
    void findProductDetail_whenQueryDataOk_returnProductDetail() {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setName(PRODUCT_NAME_1);
        productDetailDTO.setId(PRODUCT_ID_1);
        productDetailDTO.setPrice(PRODUCT_PRICE_1);
        productDetailDTO.availability(true);

        doReturn(productDetailDTO).when(this.productApi).getProductProductId(PRODUCT_ID_1);

        ProductSimilar response = this.productRepository.findProductDetail(PRODUCT_ID_1);

        assertNotNull(response);
        assertEquals(PRODUCT_ID_1, response.getId());
        assertEquals(PRODUCT_NAME_1, response.getName());
        assertEquals(PRODUCT_PRICE_1, response.getPrice());
        assertEquals(true, response.getAvailability());
        verify(this.productApi, times(1)).getProductProductId(anyString());
    }

    @SneakyThrows
    @Test
    void findSimilarProducts_whenProductIdDoesNotExist_ThrowProductNotFoundException() {
        GetProductSimilarQuery query = new GetProductSimilarQuery("2");
        doThrow(new ApiException(404, "Not found")).when(this.productApi).getProductSimilarids("2");

        assertThrows(ProductNotFoundException.class, () -> this.productRepository.findSimilarProducts(query));
    }

    @SneakyThrows
    @Test
    void findSimilarProducts_whenErrorInRestCall_ThrowRestClientException() {
        GetProductSimilarQuery query = new GetProductSimilarQuery("2");
        doThrow(ApiException.class).when(this.productApi).getProductSimilarids("2");

        assertThrows(RestClientException.class, () -> this.productRepository.findSimilarProducts(query));
    }

    @SneakyThrows
    @Test
    void findProductDetail_whenErrorInRestCall_ThrowRestClientException() {

        doThrow(ApiException.class).when(this.productApi).getProductProductId("2");

        assertThrows(RestClientException.class, () -> this.productRepository.findProductDetail("2"));
    }
}
