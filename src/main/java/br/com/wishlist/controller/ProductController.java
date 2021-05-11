package br.com.wishlist.controller;

import br.com.wishlist.domain.dto.ProductRequest;
import br.com.wishlist.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;





@Slf4j //descobrir oq é
@RestController
public class ProductController {

    @Autowired //Ponto de injeção da classe bean
    private ProductService productService;

    //Adicionar produto a base de dados
    @PostMapping(value = "/adicionar")
    public ResponseEntity<?> adicionar(@RequestBody ProductRequest product){
        return null;
//        return productService.save(product);
    }
}