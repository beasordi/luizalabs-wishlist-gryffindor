package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishListRequest {

    @NotNull(message = "clientCod is required")
    @NotEmpty(message = "clientCod cannot be empty")
    private String clientCod;

    @NotNull(message = "sku is required")
    @NotEmpty(message = "sku cannot be empty")
    private String sku;

    private String nameWishList;
}
