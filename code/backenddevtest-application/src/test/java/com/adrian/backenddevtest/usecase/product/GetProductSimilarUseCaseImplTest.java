package com.adrian.backenddevtest.usecase.product;

import com.adrian.backenddevtest.entity.ProductSimilar;
import com.adrian.backenddevtest.repository.ProductRepository;
import com.adrian.backenddevtest.usecase.GetProductSimilarQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class GetProductSimilarUseCaseImplTest {

    @InjectMocks
    private GetProductSimilarUseCaseImpl getPriceListUseCase;

    @Mock
    private ProductRepository repository;

    @Test
    void getProductSimilar_WhenDataOk_ReturnSimilarProduct() {
        final GetProductSimilarQuery query = new GetProductSimilarQuery("1");

        when(this.repository.findSimilarProducts(query)).thenReturn(Set.of("2","3","4"));
        when(this.repository.findProductDetail("2")).thenReturn(new ProductSimilar("2","Boots", BigDecimal.valueOf(25.99), true));
        when(this.repository.findProductDetail("3")).thenReturn(new ProductSimilar("3","Shirt", BigDecimal.valueOf(15.22), false));
        when(this.repository.findProductDetail("4")).thenReturn(new ProductSimilar("4","Trousers", BigDecimal.valueOf(20.96), true));

        Set<ProductSimilar> response = this.getPriceListUseCase.getProductSimilar(query);

        assertNotNull(response);
        assertEquals(3, response.size());
        verify(this.repository, times(1)).findSimilarProducts(any());
        verify(this.repository, times(3)).findProductDetail(any());
    }

}