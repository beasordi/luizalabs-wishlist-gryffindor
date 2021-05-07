package br.com.wishlist.dto;

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

    @NotNull(message = "productCod is required")
    @NotEmpty(message = "productCod cannot be empty")
    private String productCod;
}
