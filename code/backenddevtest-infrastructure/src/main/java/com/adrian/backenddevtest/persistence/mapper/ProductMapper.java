package com.adrian.backenddevtest.persistence.mapper;

import com.adrian.backenddevtest.api.model.ProductDetailDTO;
import com.adrian.backenddevtest.entity.ProductSimilar;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {


    ProductSimilar toDomain(final ProductDetailDTO entity);
}
