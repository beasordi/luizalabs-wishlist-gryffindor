package br.com.wishlist.domain.repository;

import br.com.wishlist.domain.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    void deleteBySku(String sku);

    ProductModel findBySku(String sku);
}
