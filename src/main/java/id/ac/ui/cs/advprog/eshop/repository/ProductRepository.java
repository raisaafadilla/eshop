package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteProduct(Product product) {
        productData.remove(product);
    }

    public void edit(Product product) {
        for (int i=0; i < productData.size(); i++) {
            Product curProduct = productData.get(i);
            if (curProduct.getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                return;
            }
        }
    }
}