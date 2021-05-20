package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ClientRequest;
import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.ClientNotFoundException;
import br.com.wishlist.exception.DuplicatedProductInWishListException;
import br.com.wishlist.exception.ProductNotFoundException;
import br.com.wishlist.exception.WishListLimitExcededException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
    public void addWishListWhenValidDuplicatedReturnErrorTest() {

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
        when(wishListRepository.findByClientIdAndProductId(any(), any())).thenReturn(WishListModel.builder().build());

        target.addWishList(request);
    }

    @Test(expected = ClientNotFoundException.class)
    public void addWishListWhenClientNotFoundTest() {

        //Given (Dado alguma coisa)
        WishListRequest request = WishListRequest
                .builder()
                .clientCod("35107781896")
                .nameWishList("sonhos")
                .sku("123")
                .build();

        //When (Enquanto essa coisa for alguma coisa)
        when(clientRepository.findByClientCode(any())).thenReturn(null);

        target.addWishList(request);
    }

    @Test(expected = ProductNotFoundException.class)
    public void addWishListWhenProductNotFoundTest() {

        //Given (Dado alguma coisa)
        WishListRequest request = WishListRequest
                .builder()
                .clientCod("35107781896")
                .nameWishList("sonhos")
                .sku("123")
                .build();

        //When (Enquanto essa coisa for alguma coisa)
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any())).thenReturn(null);

        target.addWishList(request);
    }

    @Test(expected = WishListLimitExcededException.class)
    public void addWishListWhenWishListLimitExcededTest() {
        //Given (Dado alguma coisa)
        WishListRequest request = WishListRequest
                .builder()
                .clientCod("35107781896")
                .nameWishList("sonhos")
                .sku("123")
                .build();

        List<WishListModel> wishListModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            wishListModelList.add(WishListModel.builder().build());
        }
        //When (Enquanto essa coisa for alguma coisa)
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        when(wishListRepository.findByClientIdAndProductId(any(), any())).thenReturn(null);
        when(wishListRepository.findByClientId(any())).thenReturn(wishListModelList);

        target.addWishList(request);
    }

    @Test
    public void addWishListWhenWishListNoLimitExcededTest() {
        //Given (Dado alguma coisa)
        WishListRequest request = WishListRequest
                .builder()
                .clientCod("35107781896")
                .nameWishList("sonhos")
                .sku("123")
                .build();

        List<WishListModel> wishListModelList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            wishListModelList.add(WishListModel.builder().build());
        }
        //When (Enquanto essa coisa for alguma coisa)
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        when(wishListRepository.findByClientIdAndProductId(any(), any())).thenReturn(null);
        when(wishListRepository.findByClientId(any())).thenReturn(wishListModelList);

        target.addWishList(request);

        //then
        verify(wishListRepository, times(1)).findByClientId(any());
        verify(wishListRepository, times(1)).save(any());
        verify(wishListRepository, times(1)).findByClientIdAndProductId(any(),any());
        verify(clientRepository, times(1)).findByClientCode(any());
        verify(productRepository, times(1)).findBySku(any());
    }

}


