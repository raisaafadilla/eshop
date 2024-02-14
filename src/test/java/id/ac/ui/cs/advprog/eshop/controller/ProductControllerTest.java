package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String expectedViewName = "createProduct";

        String actualViewName = productController.createProductPage(model);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();

        String expectedViewName = "redirect:list";

        String actualViewName = productController.createProductPost(product, model);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);
        String expectedViewName = "productList";

        String actualViewName = productController.productListPage(model);

        assertEquals(expectedViewName, actualViewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testDeleteProduct() {
        String productId = "1";

        String expectedViewName = "redirect:/product/list";

        Product product = new Product();
        when(productService.getProductId(productId)).thenReturn(product);

        String actualViewName = productController.deleteProduct(productId);

        verify(productService).deleteById(product);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditPage() {
        String id = "1";
        Product product = new Product();
        when(productService.getProductId(id)).thenReturn(product);
        String expectedViewName = "EditProduct";

        String actualViewName = productController.editPage(model, id);

        assertEquals(expectedViewName, actualViewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();

        String expectedViewName = "redirect:list";

        String actualViewName = productController.editProduct(product);

        assertEquals(expectedViewName, actualViewName);
        verify(productService).edit(product);
    }
}

