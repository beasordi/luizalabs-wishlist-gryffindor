package br.com.wishlist.domain.dto;

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

    @NotNull
    @NotNull(message = "id is required")
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "surname is required")
    private String surname;

    @NotNull(message = "telephone is required")
    private String telephone;

    @NotNull(message = "adress is required")
    private String address;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "login is required")
    private String login;

    @NotNull(message = "password is required")
    private String password;

    private String clientCode;


}
