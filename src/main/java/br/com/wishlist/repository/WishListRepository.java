package br.com.wishlist.repository;

import br.com.wishlist.model.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel, Long> {
}
