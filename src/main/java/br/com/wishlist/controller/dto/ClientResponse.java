package br.com.wishlist.controller.dto;

import br.com.wishlist.domain.model.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClientResponse {

    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;
    private String clientCode;

    public ClientResponse(ClientModel clientModel) {
        this.name = clientModel.getName();
        this.surname = clientModel.getSurname();
        this.phone = clientModel.getPhone();
        this.address = clientModel.getAddress();
        this.email = clientModel.getEmail();
        this.clientCode = clientModel.getClientCode();
    }

}
