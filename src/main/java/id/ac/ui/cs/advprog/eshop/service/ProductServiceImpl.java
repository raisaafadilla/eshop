package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product create(Product product) {
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productRepository.create(product);
        return product;
    }
    @Override
    public List<Product> findAll() {
        Iterator <Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product getProductId(String id) {
        Product product = null;
        Iterator<Product> products = productRepository.findAll();
        while (products.hasNext()) {
            Product cur = products.next();
            if (cur.getProductId().equals(id)) {
                product = cur;
                break;
            }
        }
        return product;
    }

    public void deleteById(Product product) {
        if (product != null) {
            productRepository.deleteProduct(product);
        }
    }

    @Override
    public Product edit(Product product) {
        productRepository.edit(product);
        return product;
    }
}