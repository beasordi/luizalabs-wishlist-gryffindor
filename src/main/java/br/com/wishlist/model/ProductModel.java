package br.com.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data // getters e setters
@Entity // representa um banco de dados
@Table(name = "tb_products") // representa a tabela
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) // defini o auto increment de um id
    @Column(unique = true, name = "id", columnDefinition = "INT")
    private Long id;

    @NotNull
    @Column(name = "name", columnDefinition = "VARCHAR (255)")
    private String name;

    @NotNull
    @Column(name = "quant_stock", columnDefinition = "INT")
    private Long quant_stock;

    @Column(name = "category", columnDefinition = "VARCHAR (255)")
    private String category;

    @Column(name = "provider", columnDefinition = "VARCHAR (255)")
    private String provider;
    }
