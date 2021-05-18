package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductResponse;
import br.com.wishlist.controller.dto.ProductUpdateRequest;
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
        productRepository.save(model);
    }

    @Transactional
    public void deleteProduct(String sku) {
        productRepository.deleteBySku(sku);
    }

    public List<ProductResponse> listProduct() {
        List<ProductModel> productList = productRepository.findAll();
        return productList.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateProduct(String sku, ProductUpdateRequest request) {

        //1 - preciso chamar o repository para encontrar o produto pelo SKU - retorna um model
        ProductModel model = productRepository.findBySku(sku);
        //1.2 - preciso atualizar os dados do objeto que voltou do repo com os dados do request

        if (request.getPrice() != null) {
            model.setPrice(request.getPrice());
        }

        if (request.getQuantStock() != null) {
            model.setQuantStock(request.getQuantStock());
        }

        //2 - ultimo passo(fazer o update)
        productRepository.save(model);
    }
}