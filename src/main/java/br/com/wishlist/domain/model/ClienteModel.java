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


    @Id
    @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adress")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "clientCode", nullable = false, unique = true)
    private String clientCode;




}





