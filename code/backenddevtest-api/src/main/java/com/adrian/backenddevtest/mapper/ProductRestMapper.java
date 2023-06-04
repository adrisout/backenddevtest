package com.adrian.backenddevtest.mapper;

import com.adrian.backenddevtest.entity.ProductSimilar;
import org.mapstruct.Mapper;
import org.openapitools.model.ProductDetailDTO;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Mapper
public interface ProductRestMapper {

    ProductDetailDTO toDto(ProductSimilar domain);

    Set<ProductDetailDTO> toDto(Set<ProductSimilar> domain);

}
