package br.com.wishlist.domain.repository;

import br.com.wishlist.domain.model.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel, Long> {

    List<WishListModel> findAllByClientId(Long clientId);
}