package br.com.wishlist.controller.dto;

import br.com.wishlist.domain.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String name;
    private String sku;
    private String category;
    private String provider;
    private BigDecimal price;
    private Long quantStock;

    public ProductResponse(ProductModel productModel) {
        this.name = productModel.getName();
        this.sku = productModel.getSku();
        this.category = productModel.getCategory();
        this.provider = productModel.getProvider();
        this.price = productModel.getPrice();
        this.quantStock = productModel.getQuantStock();
    }
}