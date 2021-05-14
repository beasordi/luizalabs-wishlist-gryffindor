package br.com.wishlist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tb_wishlists")
@AllArgsConstructor
@NoArgsConstructor
public class WishListModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_wishList", nullable = false)
    private String nameWishList;

    @Column(name = "client_code", nullable = false)
    private String clientCode;

    @Column(name = "sku", nullable = false)
    private String sku;
}