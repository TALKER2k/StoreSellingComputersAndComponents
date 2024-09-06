package ru.company.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.company.test.models.Desktop;
import ru.company.test.models.Product;
import ru.company.test.repositories.ProductRepository;
import ru.company.test.services.impl.ProductServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TestApplicationServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        Product product = new Desktop();
        product.setId(1L);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.addProduct(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getProductById() {
        Product product = new Desktop();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertEquals(Optional.of(product), result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void editProductNotFound() {
        Product updatedProduct = new Desktop();
        updatedProduct.setPrice(150.0);
        updatedProduct.setQuantity(20);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                productService.editProduct(1L, updatedProduct)
        );

        assertEquals("Product not found", thrown.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void getAllProductsByType() {
        Product product = new Desktop();
        product.setId(1L);

        when(productRepository.findByProductType(Product.ProductType.DESKTOP))
                .thenReturn(Collections.singletonList(product));

        List<Product> result = productService.getAllProductsByType(Product.ProductType.DESKTOP);

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository, times(1)).findByProductType(Product.ProductType.DESKTOP);
    }

}
