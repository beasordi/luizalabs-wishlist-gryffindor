package br.com.wishlist.domain.service;

import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.dto.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    public void process(ProductRequest request) {
//        log.info("[SERVICE - ProductService]");
        ProductModel productModel = new ProductModel();
        Long id = productModel.getId();

    }
}
