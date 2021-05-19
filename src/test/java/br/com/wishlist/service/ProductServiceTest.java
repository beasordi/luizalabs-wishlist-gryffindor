package br.com.wishlist.service;


import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private WishListService target;

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

//    @Test(expected =  DuplicatedProductInWishListException.class)
//    public void blablablablablaTest(){
//
//        //given(dado alguma coisa)
//        WishListRequest algumaCoisa = new WishListRequest();
//        algumaCoisa.setClientCod("1234");
//        algumaCoisa.setNameWishList("xablau");
//        algumaCoisa.setSku("1023");
//
//        //when(enquanto alguma coisa for / ser / estar /estiver em algum lugar )
//        when(clientRepository.findByClientCode(any())).thenReturn(new ClientModel());
//        when(productRepository.findBySku(any())).thenReturn(new ProductModel());
//        when(wishListRepository.findByClientIdAndProductId(any(), any())).thenReturn(new WishListModel());
//        target.addWishList(algumaCoisa);
//
//        //then(faça alguma validacao, verifique algo)
//
//    }
}

