package br.com.wishlist.domain.dto;

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
public class ProductRequest {

    @NotNull(message = "id is required")
    @NotEmpty(message = "clientCod cannot be empty")
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "quantStock is required")
    private Long quantStock;

    private String category;
    private String provider;
}
