package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductWLResponse;
import br.com.wishlist.controller.dto.WishListDeleteRequest;
import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.controller.dto.WishListResponse;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    private int limit = 20;

    public void addWishList(WishListRequest request) {
        log.info("[SERVICE - WishListService - addWishList]");

        ClientModel client = findClient(request.getClientCod());
        ProductModel product = findProduct(request.getSku());
        validDuplicated(client.getId(), product.getId());
        validationLimitWishList(client.getId());

        wishListRepository.save(
                WishListModel
                        .builder()
                        .nameWishList(request.getNameWishList())
                        .idProduct(product)
                        .idClient(client)
                        .build()
        );
    }

    public void remove(WishListDeleteRequest request) {
        log.info("[SERVICE - WishListService - remove]");

        ClientModel client = findClient(request.getClientCod());
        ProductModel product = findProduct(request.getSku());

        wishListRepository.delete(client.getId(), product.getId());
    }

    @SneakyThrows
    public WishListResponse getWishListByClientCode(String clientCode) {
        log.info("[SERVICE - WishListService - getWishListByClientCode]");
        ClientModel client = findClient(clientCode);
        List<WishListModel> models = wishListRepository.findByClientId(client.getId());

        if (models.isEmpty()) {
            throw new WishListNotFoundException();
        }

        return WishListResponse
                .builder()
                .clientCode(client.getClientCode())
                .clientName(client.getName())
                .products(processProduct(models))
                .build();
    }

    public ProductWLResponse getWishListByClientCodeFilterSku(String clientCode, String sku) {
        log.info("[SERVICE - WishListService - getWishListByClientCodeFilterSku]");
        ClientModel client = findClient(clientCode);
        ProductModel product = findProduct(sku);
        WishListModel model = getWishlist(client, product);
        return ProductWLResponse
                .builder()
                .sku(model.getIdProduct().getSku())
                .productPrice(model.getIdProduct().getPrice())
                .productName(model.getIdProduct().getName())
                .stok(model.getIdProduct().getQuantStock())
                .provider(model.getIdProduct().getProvider())
                .build();
    }

    @SneakyThrows
    private void validationLimitWishList(Long id) {
        List<WishListModel> models = wishListRepository.findByClientId(id);
        if (models.size() >= limit) {
            throw new WishListLimitExcededException();
        }
    }

    @SneakyThrows
    private WishListModel getWishlist(ClientModel client, ProductModel product) {
        WishListModel model = wishListRepository.findByClientIdAndProductId(client.getId(), product.getId());
        if (model == null) {
            throw new WishListNotFoundException();
        }
        return model;
    }

    private List<ProductWLResponse> processProduct(List<WishListModel> models) {
        return models.stream().map(ProductWLResponse::new).collect(Collectors.toList());
    }

    @SneakyThrows
    private void validDuplicated(Long idClient, Long idProduct) {
        WishListModel model = wishListRepository.findByClientIdAndProductId(idClient, idProduct);
        if (model != null) {
            throw new DuplicatedProductInWishListException();
        }
    }

    @SneakyThrows
    private ClientModel findClient(String clientCode) {
        ClientModel clientModel = clientRepository.findByClientCode(clientCode);
        if (clientModel == null) {
            throw new ClientNotFoundException();
        }
        return clientModel;
    }

    @SneakyThrows
    private ProductModel findProduct(String sku) {
        ProductModel productModel = productRepository.findBySku(sku);
        if (productModel == null) {
            throw new ProductNotFoundException();
        }
        return productModel;
    }
}