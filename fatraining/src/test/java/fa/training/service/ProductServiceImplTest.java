package fa.training.service;

import fa.training.entity.Product;
import fa.training.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private  ProductServiceImpl productService;
    Product product;

    @BeforeEach
    void setup() {
        product = new Product();
    }

    @Test
    void addProductNullTest() {
        //given

        //when

        //then
        assertNull(productService.addProduct(null));
    }

    @Test
    void addProductSuccessTest() {
        //given

        //when
        when(productRepository.save(product)).thenReturn(product);
        //then
        assertEquals(product, productService.addProduct(product));
    }


    @Test
    void updateProductNullTest() {
        //given

        //when

        //then
        assertNull(productService.updateProduct(null,null));
    }

    @Test
    void updateProductNullIdTest() {
        //given
        long id = 34;
        //when
        when(productRepository.getReferenceById(id)).thenReturn(null);
        //then
        assertNull(productService.updateProduct(id,product));
    }

    @Test
    void updateProductSuccessTest() {
        //given
        long id = 34;
        Product productFromDB = new Product();
        //when
        when(productRepository.getReferenceById(id)).thenReturn(productFromDB);
        when(productRepository.saveAndFlush(productFromDB)).thenReturn(productFromDB);
        //then
        assertEquals(productFromDB, productService.updateProduct(id,product));
    }
}
