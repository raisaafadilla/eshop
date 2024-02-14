package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.NoSuchElementException;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateWithNullId() {
        Product product = new Product();
        product.setProductId(null); // Set ID to null
        product.setProductName("Null ID Product");
        product.setProductQuantity(50);

        // Try to create the product with a null ID
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(product));
    }

    @Test
    void testCreateWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("test-id");
        product.setProductName("Negative Quantity Product");
        product.setProductQuantity(-10); // Set negative quantity

        // Try to create the product with a negative quantity
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(product));
    }

    @Test
    void testEditProduct() {
        // Create a product
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Product 1");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Edit the product
        Product editedProduct = new Product();
        editedProduct.setProductId("1");
        editedProduct.setProductName("Edited Product");
        editedProduct.setProductQuantity(20);
        productRepository.edit(editedProduct);

        // Check if the product is edited correctly
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        // Create a product
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Product 1");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Delete the product
        productRepository.deleteProduct(product);

        // Ensure the product is no longer in the repository
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());

        // Attempt to retrieve the deleted product should throw NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            productIterator.next();
        });
    }

    @Test
    void testEditNonExistingProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Product 1");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product modifiedProduct = new Product();
        modifiedProduct.setProductId("2");
        modifiedProduct.setProductName("Modified Product");
        modifiedProduct.setProductQuantity(20);

        productRepository.edit(modifiedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product foundProduct = productIterator.next();
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }
}