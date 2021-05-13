package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductResponse;
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

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<String> addProducts(@Valid @RequestBody ProductRequest request) {
        productService.addProduct(request);
        return new ResponseEntity<>("Produto adicionado com sucesso!", HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List<ProductResponse>> listProducts() {
        List<ProductResponse> response = productService.listProduct();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable("sku") String sku) {
        productService.deleteProduct(sku);
        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/product", method = RequestMethod.PUT)
//    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductRequest request){
//        productService.updateProduct(request);
//        return new ResponseEntity<>("Produto atualizado com sucesso!");
//    }
}
