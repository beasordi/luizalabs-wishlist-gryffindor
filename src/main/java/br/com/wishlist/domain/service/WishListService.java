package br.com.wishlist.domain.service;

import br.com.wishlist.domain.dto.WishListRequest;
import br.com.wishlist.exception.WishListLimitExceededException;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.WishListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public void process(WishListRequest request) {
        log.info("[SERVICE - WishListService]");

        //1. buscar o PK na tabela produto pelo request.productId - pendente Beatriz
        //2. buscar o PK na tabela cliente pelo request.clientId - pendente Thais

        //3. construir um model para enviar ao repository -ok;
        WishListModel wishListModel = new WishListModel();
        wishListModel.setClientId(1L);
        wishListModel.setProductId(2L);

        wishListRepository.save(wishListModel);
    }

    //4. validar, a wish list pode ter no máximo 20 produtos -ok;
    private void validaroQuantidadeProdutosNaWishList(Long clientId) throws WishListLimitExceededException {
        //ir no banco de dados;
        List<WishListModel> wishLists = wishListRepository.findAllByClientId(clientId);
        // se o retorno da consulta for igual a 20 produtos
        if (wishLists.size() >= 20) {
            // lançar uma exception
            throw new WishListLimitExceededException();
        }
    }
}
