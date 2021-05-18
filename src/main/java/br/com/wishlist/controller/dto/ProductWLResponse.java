package br.com.wishlist.controller.dto;

import br.com.wishlist.domain.model.WishListModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWLResponse {

    private String productName;
    private String sku;
    private String provider;
    private BigDecimal productPrice;
    private Long stok;

    public ProductWLResponse(WishListModel wishListModel) {
        this.productName = wishListModel.getIdProduct().getName();
        this.sku = wishListModel.getIdProduct().getSku();
        this.provider = wishListModel.getIdProduct().getProvider();
        this.productPrice = wishListModel.getIdProduct().getPrice();
        this.stok = wishListModel.getIdProduct().getQuantStock();
    }
}
