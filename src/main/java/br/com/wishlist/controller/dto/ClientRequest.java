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

public class ClientRequest {

    @NotNull(message = "name is required")
    @NotEmpty
    private String name;

    @NotNull(message = "surname is required")
    @NotEmpty
    private String surname;

    @NotNull(message = "phone is required")
    @NotEmpty
    private String phone;

    @NotNull(message = "adress is required")
    @NotEmpty
    private String address;

    @NotNull(message = "email is required")
    @NotEmpty
    private String email;

    @NotNull(message = "clientCode is required")
    @NotEmpty
    private String clientCode;

}
