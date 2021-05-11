package br.com.wishlist.model;

import br.com.wishlist.domain.dto.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data // getters e setters
//@Entity // representa um banco de dados
@Table(name = "tb_products") // representa a tabela
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel{

    @Id
    @NotNull(message = "id is required")
    @NotEmpty(message = "clientCod cannot be empty")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // define o auto increment de um id
    @Column(unique = true, name = "id", columnDefinition = "INT", nullable = false)
    private Long id;

    @NotNull(message = "name is required")
    @Column(name = "name", columnDefinition = "VARCHAR (255)", nullable = false)
    private String name;

    @NotNull(message = "quant_stock is required")
    @Column(name = "quant_stock", columnDefinition = "INT",nullable = false)
    private Long quant_stock;

    @Column(name = "category", columnDefinition = "VARCHAR (255)")
    private String category;

    @Column(name = "provider", columnDefinition = "VARCHAR (255)")
    private String provider;
    }
