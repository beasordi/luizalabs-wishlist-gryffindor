package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequest {

    @NotNull(message = "name is required")
    @NotEmpty
    private String name;

    @NotNull(message = "surname is required")
    @NotEmpty
    private String surname;

    @NotNull(message = "telephone is required")
    @NotEmpty
    private String telephone;

    @NotNull(message = "adress is required")
    @NotEmpty
    private String address;

    @NotNull(message = "email is required")
    @NotEmpty
    private String email;

    @NotNull(message = "login is required")
    @NotEmpty
    private String login;

    @NotNull(message = "password is required")
    @NotEmpty
    private String password;

    @NotNull(message = "password is required")
    @NotEmpty
    private String clientCode;

}
