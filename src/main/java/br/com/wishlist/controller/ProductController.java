package br.com.wishlist.controller;

import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
public class ProductController {

    @Autowired //Ponto de injeção da classe bean
    private ProductService productService;

    //Adicionar produto a base de dados
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void addProducts(@RequestBody ProductModel product) {
        productService.addProduct(product);
    }

    //Deletar produto da base de dados
    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestBody Long id) {
        productService.deleteProduct(id);
    }

    //Listar produtos da base de dados
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<ProductModel> listProducts(){
        return productService.listProduct();
    }

    //Alterar produtos da base de dados
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody ProductModel product){
        productService.updateProduct(product);
    }
}
