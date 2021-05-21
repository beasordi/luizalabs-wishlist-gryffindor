package br.com.wishlist.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClientUpdateRequest {
    @NotNull(message = "Nome não pode ser nulo")
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;
}
