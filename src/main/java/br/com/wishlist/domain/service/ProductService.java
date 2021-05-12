package br.com.wishlist.domain.service;

import br.com.wishlist.domain.dto.ProductRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    //Adicionando produtos
    public ProductModel addProduct(ProductModel product){
        return productRepository.save(product);
    }

    //Deletando produtos
    public void deleteProduct(Long id){
        ProductModel devolucao = productRepository.getOne(id);
        if (devolucao != null){
            productRepository.deleteById(id);
        }
    }

    //Lista de produtos
    public List<ProductModel> listProduct(){
        return (List<ProductModel>) productRepository.findAll();
    }

    //Atualização de produtos
    public ProductModel updateProduct(ProductModel product){
        ProductModel devolucao = productRepository.getOne(product.getId());
        if (devolucao != null){
            devolucao.setName(product.getName());
            devolucao.setCategory(product.getCategory());
            devolucao.setQuantStock(product.getQuantStock());
            devolucao.setProvider(product.getProvider());
            productRepository.save(devolucao);
        }
        return productRepository.save(product);
    }

}
