package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ProductRequest;
import br.com.wishlist.controller.dto.ProductUpdateRequest;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ProductRepository;
import br.com.wishlist.exception.DuplicatedSkuException;
import br.com.wishlist.exception.EmptyListProductException;
import br.com.wishlist.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService target;

    @Mock
    private ProductRepository productRepository;

    @Test(expected = DuplicatedSkuException.class)
    public void addProductWhenValidDuplicatedSkuTest() {

        ProductRequest request = ProductRequest
                .builder()
                .category("Eletrônicos")
                .name("PS5")
                .sku("987")
                .price(new BigDecimal(5000))
                .provider("Sony")
                .quantStock(5L)
                .build();

        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        target.addProduct(request);

        verify(productRepository, times(1)).findBySku(any());
    }

    @Test
    public void addProductWhenSuccessTest() {

        ProductRequest request = ProductRequest
                .builder()
                .category("Electronics")
                .name("PS5")
                .sku("987")
                .price(new BigDecimal(5000))
                .provider("Sony")
                .quantStock(5L)
                .build();

        when(productRepository.findBySku(any())).thenReturn(null);
        target.addProduct(request);

        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void ListProductWhenSuccessTest() {
        List<ProductModel> list = new ArrayList<>();
        list.add(new ProductModel(1L, "PS5", "987", 5L, new BigDecimal(5000), "Electronics", "Sony"));

        when(productRepository.findAll()).thenReturn(list);
        target.listProduct();

        verify(productRepository, times(1)).findAll();
    }

    @Test(expected = EmptyListProductException.class)
    public void ListProductWhenErrorTest() {
        List<ProductModel> list = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(list);
        target.listProduct();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void DeleteProductWhenSuccessTest() throws SQLIntegrityConstraintViolationException {
        when(productRepository.findBySku(any())).thenReturn(ProductModel.builder().build());
        target.deleteProduct(any());

        verify(productRepository, times(1)).deleteBySku(any());
    }

    @Test
    public void UpdateProductWhenSuccessTest() {
        ProductUpdateRequest request = ProductUpdateRequest
                .builder()
                .price(new BigDecimal(5000))
                .quantStock(5L)
                .build();
        ProductModel product = new ProductModel();

        when(productRepository.findBySku(any())).thenReturn(product);
        target.updateProduct(any(), request);

        verify(productRepository, times(1)).save(any());
    }

    @Test(expected = ProductNotFoundException.class)
    public void UpdateProductErrorFindProductTest() {
        ProductUpdateRequest request = ProductUpdateRequest
                .builder()
                .price(new BigDecimal(5000))
                .quantStock(5L)
                .build();

        when(productRepository.findBySku(any())).thenReturn(null);
        target.updateProduct(any(), request);

        verify(productRepository, times(1)).save(any());
    }
}