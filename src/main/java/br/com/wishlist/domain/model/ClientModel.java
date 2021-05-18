package br.com.wishlist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_clients")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    @Id
    @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "adress")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "clientCode", nullable = false, unique = true)
    private String clientCode;
}
