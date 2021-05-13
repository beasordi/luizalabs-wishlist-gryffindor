package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping(value = "/register")
    public ResponseEntity register(@Valid @RequestBody WishListRequest wishListRequest) {
        log.info("[CONTROLLER - WishListController]");
        log.info("[REQUEST - {}}]", wishListRequest);
        wishListService.process(wishListRequest);
        return new ResponseEntity<>("Dados registrados com sucesso", HttpStatus.OK);
    }
}