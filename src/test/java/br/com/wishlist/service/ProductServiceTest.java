package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductUpdateRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.domain.repository.WishListRepository;
import br.com.wishlist.exception.DuplicatedSkuException;
import br.com.wishlist.exception.EmptyListException;
import br.com.wishlist.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService target;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private WishListRepository wishListRepository;

    //erro
    @Test(expected = DuplicatedSkuException.class)
    public void addProductWhenValidDuplicatedSkuTest() {
        //given
        ProductRequest request = ProductRequest
                .builder()
                .category("Eletrônicos")
                .name("PS5")
                .sku("987")
                .price(new BigDecimal(5000))
                .provider("Sony")
                .quantStock(5L)
                .build();
        //when
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        target.addProduct(request);
        //then
        verify(productRepository, times(1)).findBySku(any());
    }

    @Test
    public void addProductWhenSuccessTest() {
        //given
        ProductRequest request = ProductRequest
                .builder()
                .category("Electronics")
                .name("PS5")
                .sku("987")
                .price(new BigDecimal(5000))
                .provider("Sony")
                .quantStock(5L)
                .build();
        //when
        when(productRepository.findBySku(any())).thenReturn(null);
        target.addProduct(request);

        //then
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void ListProductWhenSuccessTest() {
        //given
        List<ProductModel> list = new ArrayList<>();
        list.add(new ProductModel(1L, "PS5", "987", 5L, new BigDecimal(5000), "Electronics", "Sony"));
        //when
        when(productRepository.findAll()).thenReturn(list);
        target.listProduct();
        //then
        verify(productRepository, times(1)).findAll();
    }

    @Test(expected = EmptyListException.class)
    public void ListProductWhenErrorTest() {
        //given
        List<ProductModel> list = new ArrayList<>();
        //when
        when(productRepository.findAll()).thenReturn(list);
        target.listProduct();
        //then
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void DeleteProductWhenSuccessTest(){
        //given
        //when
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        target.deleteProduct(any());
        //then
        verify(productRepository,times(1)).deleteBySku(any());

    }

    @Test
    public void UpdateProductWhenSuccessTest(){
        //given
        ProductUpdateRequest request = ProductUpdateRequest
                .builder()
                .price(new BigDecimal(5000))
                .quantStock(5L)
                .build();

        //when
        ProductModel product = new ProductModel();

        when(productRepository.findBySku(any())).thenReturn(product);
        target.updateProduct(any(),request);
        //then
        verify(productRepository,times(1)).save(any());
    }

    @Test(expected = ProductNotFoundException.class)
    public void UpdateProductErroFindProductTest(){
        //given
        ProductUpdateRequest request = ProductUpdateRequest
                .builder()
                .price(new BigDecimal(5000))
                .quantStock(5L)
                .build();

        //when

        when(productRepository.findBySku(any())).thenReturn(null);
        target.updateProduct(any(),request);
        //then
        verify(productRepository,times(1)).save(any());
    }
}