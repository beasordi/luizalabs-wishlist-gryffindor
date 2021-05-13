package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
public class ProductController {

    @Autowired //Ponto de injeção da classe bean
    private ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<String> addProducts(@Valid @RequestBody ProductRequest request) { //O valid torna o notnull e o notempty
        productService.addProduct(request);
        return new ResponseEntity<>("Produto adicionado com sucesso!", HttpStatus.OK);
    }
    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@Valid @RequestBody Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Seu produto foi deletado com sucesso!", HttpStatus.OK);
    }
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<String> List<ProductModel> listProducts(){
        return productService.listProduct();
    }
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductRequest request){
        productService.updateProduct(request);
        return new ResponseEntity<>("Produto atualizado com sucesso!");
    }
}
