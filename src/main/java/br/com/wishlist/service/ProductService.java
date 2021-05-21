package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductResponse;
import br.com.wishlist.controller.dto.ProductUpdateRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.exception.DuplicatedSkuException;
import br.com.wishlist.exception.EmptyListProductException;
import br.com.wishlist.exception.ProductNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @SneakyThrows
    public void addProduct(ProductRequest request) {
        ProductModel model = new ProductModel();
        model.setName(request.getName());
        model.setQuantStock(request.getQuantStock());
        model.setPrice(request.getPrice());
        model.setSku(request.getSku());
        model.setCategory(request.getCategory());
        model.setProvider(request.getProvider());

        validProductDuplicated(request.getSku());
        productRepository.save(model);
    }

    @Transactional
    public void deleteProduct(String sku) throws SQLIntegrityConstraintViolationException {
        findProduct(sku);
        try {
            productRepository.deleteBySku(sku);
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    @SneakyThrows
    public List<ProductResponse> listProduct() {
        List<ProductModel> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new EmptyListProductException();
        }
        return productList.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateProduct(String sku, ProductUpdateRequest request) {
        findProduct(sku);
        ProductModel model = productRepository.findBySku(sku);
        model.setPrice(request.getPrice() != null ? request.getPrice() : model.getPrice());
        model.setQuantStock(request.getQuantStock() != null ? request.getQuantStock() : model.getQuantStock());
        productRepository.save(model);
    }

    @SneakyThrows
    private ProductModel findProduct(String sku) {
        ProductModel productModel = productRepository.findBySku(sku);
        if (productModel == null) {
            throw new ProductNotFoundException();
        }
        return productModel;
    }

    @SneakyThrows
    private void validProductDuplicated(String sku) {
        ProductModel productModel = productRepository.findBySku(sku);
        if (productModel != null) {
            throw new DuplicatedSkuException();
        }
    }
}