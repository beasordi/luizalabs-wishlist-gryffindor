package br.com.wishlist.service;

import br.com.wishlist.controller.dto.WishListDeleteRequest;
import br.com.wishlist.controller.dto.WishListRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.model.WishListModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        // Then (faça ou verifique isso) -- utilizados NO GERAL pra métodos que NÃO VOID
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

        //Then (Então faça isso ou faça aquilo)
        verify(wishListRepository, times(1)).findByClientId(any());
        verify(wishListRepository, times(1)).save(any());
        verify(wishListRepository, times(1)).findByClientIdAndProductId(any(),any());
        verify(clientRepository, times(1)).findByClientCode(any());
        verify(productRepository, times(1)).findBySku(any());
    }

    @Test
    public void removeWhenWishListTest(){

        //Given
        WishListDeleteRequest request = WishListDeleteRequest
                .builder()
                .clientCod("1")
                .sku("123")
                .build();

        //When
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());

        target.remove(request);

        //Then
        verify(wishListRepository, times(1)).delete(any(), any());
    }

    @Test(expected = WishListNotFoundException.class)
    public void getWishListNotFoundTest(){

        //When
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(wishListRepository.findByClientId(any())).thenReturn(new ArrayList<WishListModel>());
        target.getWishListByClientCode("35107781896");

        //Then
        verify(wishListRepository,times(1)).findByClientId(any());
    }

    @Test
    public void getWishListSuccessTest(){
        // Given
        List<WishListModel> list = new ArrayList<>();
        list.add(WishListModel
                .builder()
                .idClient(new ClientModel(1L,"Joao","da Silva", "1199999999","Rua do Sol, 10", "joao@email.com", "35107781896"))
                .idProduct(new ProductModel(1L,"PS5","123",1L,new BigDecimal(5000),"Games","Sony"))
                .build());
        //When
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        when(wishListRepository.findByClientId(any())).thenReturn(list);
        target.getWishListByClientCode("35107781896");

        //Then
        verify(wishListRepository,times(1)).findByClientId(any());
        assertEquals(list.iterator().next().getIdClient().getClientCode(), "35107781896");
    }

    @Test(expected = WishListNotFoundException.class)
    public void getWishListByClientCodeFilterSkuTest(){

        //When
        when(clientRepository.findByClientCode(any()))
                .thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any()))
                .thenReturn(ProductModel.builder().build());
        when(wishListRepository.findByClientIdAndProductId(any(),any()))
                .thenReturn(null);
        target.getWishListByClientCodeFilterSku("35107781896", "1");
        }

    @Test
    public void getWishListByClientCodeFilterSkuSuccessTest(){

        //Given
        WishListModel wishListModel = WishListModel
                .builder()
                .idClient(new ClientModel(1L, "Joao", "da Silva", "1199999999", "Rua do Sol, 10", "joao@email.com", "35107781896"))
                .idProduct(new ProductModel(1L, "PS5", "123", 1L, new BigDecimal(5000), "Games", "Sony"))
                .build();

        //When
        when(clientRepository.findByClientCode(any()))
                .thenReturn(ClientModel.builder().build());
        when(productRepository.findBySku(any()))
                .thenReturn(ProductModel.builder().build());
        when(wishListRepository.findByClientIdAndProductId(any(),any()))
                .thenReturn(wishListModel);
        target.getWishListByClientCodeFilterSku("35107781896", "1");
        //then
        verify(clientRepository,times(1)).findByClientCode(any());
        verify(productRepository, times(1)).findBySku(any());
        verify(wishListRepository,times(1)).findByClientIdAndProductId(any(),any());

        assertEquals(wishListModel.getIdClient().getClientCode(), "35107781896");
    }

}