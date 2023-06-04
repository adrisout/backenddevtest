package com.adrian.backenddevtest.config;

import com.adrian.backenddevtest.api.ApiClient;
import com.adrian.backenddevtest.api.client.ProductApi;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class ProductRestClientConfiguration {

    @Value("${backenddevtest.rest.client.product.base-url}")
    private String baseUrl;

    @Value("${backenddevtest.rest.client.product.connection-timeout}")
    private int connectionTimeout;

    @Value("${backenddevtest.rest.client.product.read-timeout}")
    private int readTimeout;

    @Bean
    @ConfigurationProperties(prefix = "backenddevtest.rest.client.product")
    public OkHttpClient productRestClient()  {
        return new OkHttpClient.Builder()
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public ApiClient productApiClient(@Qualifier("productRestClient") final OkHttpClient productRestClient) {
        final ApiClient apiClient = new ApiClient(productRestClient);
        apiClient.setBasePath(baseUrl);
        return apiClient;
    }

    @Bean
    public ProductApi productApi(@Qualifier("productApiClient") ApiClient apiClient){
        ProductApi productApi = new ProductApi();
        productApi.setApiClient(apiClient);
        return productApi;
    }
}