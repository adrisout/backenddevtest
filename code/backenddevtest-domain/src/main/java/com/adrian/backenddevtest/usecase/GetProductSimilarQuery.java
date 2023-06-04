package com.adrian.backenddevtest.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GetProductSimilarQuery implements Serializable {

    private String productId;

}
