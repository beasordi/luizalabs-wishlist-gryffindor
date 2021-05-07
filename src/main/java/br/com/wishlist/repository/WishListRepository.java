package br.com.wishlist.repository;

import br.com.wishlist.model.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel, Long> {

    List<WishListModel> findAllByClientId(Long clientId);
}
