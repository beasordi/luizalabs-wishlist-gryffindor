package br.com.wishlist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor

public class ClienteModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull
    private String name;
    private String surname;
    private String telephone;
    private String address;
    private String email;
    private String login;
    private String password;


}





