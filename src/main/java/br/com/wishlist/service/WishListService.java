package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductWLResponse;
import br.com.wishlist.controller.dto.WishListDeleteRequest;
import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.controller.dto.WishListResponse;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.ClienteRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductRepository productRepository;

    @SneakyThrows
    public void addWishList(WishListRequest request) {
        log.info("[SERVICE - WishListService - addWishList]");

        clientExistValidation(request.getClientCod());
        productExistValidation(request.getSku());
        productDuplicated(request);

        if (findWishList(request.getClientCod()).size() < 20) {
            wishListRepository.save(
                    WishListModel
                            .builder()
                            .sku(request.getSku())
                            .clientCode(request.getClientCod())
                            .nameWishList(request.getNameWishList())
                            .build()
            );
        } else {
            throw new WishListLimitExcededException();
        }
    }

    @SneakyThrows
    private void productDuplicated(WishListRequest wishListRequest) {
        List<WishListModel> models = wishListRepository.findAllByClientCode(wishListRequest.getClientCod());

        for(WishListModel list:models){
            if(list.getSku().equals(wishListRequest.getSku())){
                throw new DuplicatedProductInWishList();
            }
        }
    }

    public void remove(WishListDeleteRequest request) {
        log.info("[SERVICE - WishListService - remove]");
        clientExistValidation(request.getClientCod());
        productExistValidation(request.getSku());

        List<WishListModel> wishListModels = findWishList(request.getClientCod());

        for (WishListModel list : wishListModels) {
            if (list.getSku().equals(request.getSku())) {
                wishListRepository.deleteById(list.getId());
            }
        }
    }


    public WishListResponse getWishListByClientCode(String clientCode) {

        List<WishListModel> wishListModel = findWishList(clientCode);

        List<ProductWLResponse> listProducts = new ArrayList<>();

        ClientModel client = clientExistValidation(clientCode);

        for (WishListModel list : wishListModel) {
            ProductModel product = productExistValidation(list.getSku());

            listProducts.add(
                    ProductWLResponse
                            .builder()
                            .productName(product.getName())
                            .provider(product.getProvider())
                            .sku(product.getSku())
                            .build()
            );
        }

        return WishListResponse
                .builder()
                .clientName(client.getName())
                .clientCode(client.getClientCode())
                .products(listProducts)
                .build();
    }

    @SneakyThrows
    public ProductWLResponse getWishListByClientCodeFilterSku(String clientCode, String sku) {

        List<WishListModel> wl = findWishList(clientCode);

        for (WishListModel list : wl) {
            if (list.getSku().equals(sku)) {
                ProductModel product = productExistValidation(list.getSku());
                return ProductWLResponse
                        .builder()
                        .productName(product.getName())
                        .provider(product.getProvider())
                        .sku(product.getSku())
                        .build();
            }
        }
        throw new NoProductsFoundInWishListExecption();
    }

    @SneakyThrows
    private ProductModel productExistValidation(String sku) {
        ProductModel product = productRepository.findBySku(sku);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @SneakyThrows
    private ClientModel clientExistValidation(String clientCode) {
        ClientModel clientModel = clienteRepository.findByClientCode(clientCode);
        if (clientModel == null) {
            throw new ClientNotFoundException();
        }
        return clientModel;
    }

    @SneakyThrows
    private List<WishListModel> findWishList(String clientCode) {
        return wishListRepository.findAllByClientCode(clientCode);
    }
}
