package com.adrian.backenddevtest.controller;

import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.mapper.ProductRestMapperImpl;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import com.adrian.backenddevtest.usecase.GetProductSimilarUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.ProductDetailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ProductControllerTest {
    public static final String PRODUCT_ID_1 = "1";
    public static final String PRODUCT_ID_2 = "2";
    public static final String PRODUCT_NAME_2 = "Boots";
    public static final BigDecimal PRODUCT_PRICE_2 = BigDecimal.valueOf(59);
    public static final boolean PRODUCT_AVAILABLE_2 = true;
    @InjectMocks
    private ProductController productController;

    @Mock
    private GetProductSimilarUseCase getProductSimilarUseCase;

    @Spy
    private ProductRestMapperImpl productRestMapper;

    @Test
    void getProductSimilar_WhenDataOk_Return200OK() {
        final GetProductSimilarQuery query = new GetProductSimilarQuery(PRODUCT_ID_1);
        ProductSimilar productSimilar = new ProductSimilar(PRODUCT_ID_2, PRODUCT_NAME_2, PRODUCT_PRICE_2, PRODUCT_AVAILABLE_2);

        doReturn(Set.of(productSimilar)).when(this.getProductSimilarUseCase).getProductSimilar(query);

        final ResponseEntity<Set<ProductDetailDTO>> response = this.productController.getProductSimilar(PRODUCT_ID_1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        verify(this.getProductSimilarUseCase, times(1)).getProductSimilar(query);
    }

}
