package br.com.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_wishlist")
@AllArgsConstructor
@NoArgsConstructor
public class WishListModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long idClient;
    private Long idProduct;



}
