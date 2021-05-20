package br.com.wishlist.domain.repository;

import br.com.wishlist.domain.model.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel, Long> {

    @Query(value = "SELECT * FROM tb_wishlists AS twl WHERE twl.id_client = :idClient", nativeQuery = true)
    List<WishListModel> findByClientId(@Param("idClient") Long idClient);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_wishlists AS twl WHERE twl.id_client = :idClient AND twl.id_product = :idProduct", nativeQuery = true)
    void delete(@Param("idClient") Long idClient, @Param("idProduct") Long idProduct);

    @Query(value = "SELECT * FROM tb_wishlists AS twl WHERE twl.id_client = :idClient AND twl.id_product = :idProduct", nativeQuery = true)
    WishListModel findByClientIdAndProductId(@Param("idClient") Long idClient, @Param("idProduct") Long idProduct);
}