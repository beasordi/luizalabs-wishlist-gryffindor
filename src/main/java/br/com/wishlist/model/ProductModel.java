package br.com.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data // para ter os getters e setters
@Entity // representa um banco de dados
@Table(name = "tb_products") // representa tabela
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long quant_stock;
    private String category;
    private String provider;
    }
