package br.com.wishlist.service;

import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.DuplicatedProductInWishListException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WishListServiceTest {

    @InjectMocks
    private WishListService target;

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

    @Test(expected = DuplicatedProductInWishListException.class)
    public void addWishListWhenValidDuplicatedReturnErrorTest(){

        //Given (Dado alguma coisa)
        WishListRequest request = WishListRequest
                .builder()
                .clientCod("35107781896")
                .nameWishList("sonhos")
                .sku("123")
                .build();

        //When (Enquanto essa coisa for alguma coisa)
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        when(wishListRepository.findByClientIdAndProductId(any(),any())).thenReturn(WishListModel.builder().build());

        target.addWishList(request);
    }
}