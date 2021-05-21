package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.ProductWLResponse;
import br.com.wishlist.controller.dto.WishListDeleteRequest;
import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.controller.dto.WishListResponse;
import br.com.wishlist.service.WishListService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @ApiOperation(value = "Adicionar um produto na Wishlist do cliente")
    @RequestMapping(value = "/wishlist", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody WishListRequest wishListRequest) {
        log.info("[CONTROLLER - WishListController - POST]");
        log.info("[REQUEST - {}}]", wishListRequest);
        wishListService.addWishList(wishListRequest);
        return new ResponseEntity<>("Dados registrados com sucesso", HttpStatus.OK);
    }

    @ApiOperation(value = "Remover um produto da Wishlist do cliente")
    @RequestMapping(value = "/wishlist/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@Valid @RequestBody WishListDeleteRequest request) {
        log.info("[CONTROLLER - WishListController - DELETE]");
        log.info("[REQUEST - {}}]", request);
        wishListService.remove(request);
        return new ResponseEntity<>("Dados removidos com sucesso", HttpStatus.OK);
    }

    @ApiOperation(value = "Consultar todos os produtos da Wishlist do cliente")
    @RequestMapping(value = "/wishlist/{client-code}", method = RequestMethod.GET)
    public ResponseEntity<WishListResponse> getWishListByClientCode(@PathVariable("client-code") String clientCode) {
        log.info("[CONTROLLER - WishListController - GET - getWishListByClientCode]");
        log.info("[REQUEST - {}}]", clientCode);
        return new ResponseEntity<>(wishListService.getWishListByClientCode(clientCode), HttpStatus.OK);
    }

    @ApiOperation(value = "Consultar se um determinado produto está na Wishlist do cliente")
    @RequestMapping(value = "/wishlist/client-code/{client-code}/sku/{sku}", method = RequestMethod.GET)
    public ResponseEntity<ProductWLResponse> getWishListByClientCodeAndSku(
            @PathVariable("client-code") String clientCode,
            @PathVariable("sku") String sku
    ) {
        log.info("[CONTROLLER - WishListController - GET - getWishListByClientCode]");
        log.info("[REQUEST - {}}]", clientCode);
        return new ResponseEntity<>(wishListService.getWishListByClientCodeFilterSku(clientCode, sku), HttpStatus.OK);
    }
}