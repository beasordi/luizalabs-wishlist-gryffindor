package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductResponse;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductRequest request) {
        ProductModel model = new ProductModel();
        model.setName(request.getName());
        model.setSku(request.getSku());
        model.setCategory(request.getCategory());
        model.setProvider(request.getProvider());
        //try - catch
        productRepository.save(model);
    }

    public List<ProductResponse> listProduct() {
        List<ProductModel> produtos = productRepository.findAll();
        return produtos.stream().map(ProductResponse::new).collect(Collectors.toList());
        // stream = for na lista
        // map = converter
        // collect = retorna para uma lista
        //java 8
    }

    @Transactional
    public void deleteProduct(String sku) {
        productRepository.deleteBySku(sku);
    }

}