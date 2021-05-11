package br.com.wishlist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data // getters e setters
@Entity // representa um banco de dados
@Table(name = "tb_products") // representa a tabela
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel{

    @Id
    @NotNull(message = "id is required")
    @NotEmpty(message = "clientCod cannot be empty")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // define o auto increment de um id
    @Column(unique = true, name = "id")
    private Long id;

    @NotNull(message = "name is required")
    @Column(name = "name", length = 255)
    private String name;

    @NotNull(message = "quantStock is required")
    @Column(name = "quantStock")
    private Long quantStock;

    @Column(name = "category")
    private String category;

    @Column(name = "provider")
    private String provider;
    }
