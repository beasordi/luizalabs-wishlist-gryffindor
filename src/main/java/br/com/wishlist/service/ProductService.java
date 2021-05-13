package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //Adicionando produtos
    public void addProduct(ProductRequest request) {
        //Vamos converter o request em model
        ProductModel model = new ProductModel();
        model.setName(request.getName());
        model.setSku(request.getSku());
        model.setCategory(request.getCategory());
        model.setProvider(request.getProvider());

        //try - catch
        productRepository.save(model);
    }

    //Adicionando produtos
    public void deleteProduct(ProductRequest request, Long sku) {
        //Vamos converter o request em model
        ProductModel model = new ProductModel();
        productRepository.deleteById(productRepository.getOne(model.setSku(request.getSku())));

    }
//    //Deletando produtos
//    public void deleteProduct(ProductRequest request) {
//        //transforma o request em model
//        ProductModel model = new ProductModel();
//        //localizar o produto de acordo com o sku
//        ProductModel devolucao = productRepository.getOne(request.getSku());
//        productRepository.deleteById(request);
//    }

    //Lista de produtos
    public List<ProductModel> listProduct() {
        return (List<ProductModel>) productRepository.findAll();
    }

    //Atualização de produtos
    public ProductModel updateProduct(ProductModel product) {
        ProductModel devolucao = productRepository.getOne(product.getId());
        if (devolucao != null) {
            devolucao.setName(product.getName());
            devolucao.setCategory(product.getCategory());
            devolucao.setSku(product.getSku());
            devolucao.setProvider(product.getProvider());
            productRepository.save(devolucao);
        }
        return productRepository.save(product);
    }

}
