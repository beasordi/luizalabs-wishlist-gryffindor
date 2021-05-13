package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message = "name is required")
    @Column(name = "name", length = 255)
    private String name;

    @NotNull(message = "sku is required")
    @Column(name = "sku")
    private Long sku;

    @Column(name = "category")
    private String category;

    @Column(name = "provider")
    private String provider;
}