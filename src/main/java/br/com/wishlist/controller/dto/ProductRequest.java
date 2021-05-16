package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "sku is required")
    private String sku;
    private String category;
    private String provider;
    private BigDecimal price;
    private Long quantStock;
}