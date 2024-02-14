package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> foundProducts = productService.findAll();

        assertTrue(foundProducts.contains(product));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("1");

        doNothing().when(productRepository).deleteProduct(product);

        productService.deleteById(product);

        verify(productRepository, times(1)).deleteProduct(product);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("1");

        doNothing().when(productRepository).edit(product);

        productService.edit(product);

        verify(productRepository, times(1)).edit(product);
    }

}