package br.com.wishlist.domain;

import br.com.wishlist.domain.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductTest {

    @Test
    public void createProductIntance(){
        ProductModel productModel = new ProductBuilder().defaultValues();
        assertThat(productModel).isNotNull();
    }
}
