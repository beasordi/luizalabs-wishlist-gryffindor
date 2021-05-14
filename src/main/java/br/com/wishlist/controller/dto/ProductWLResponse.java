package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWLResponse {

    private String productName;
    private String sku;
    private String provider;
    private String productPrice;
    private Long stok;
}
