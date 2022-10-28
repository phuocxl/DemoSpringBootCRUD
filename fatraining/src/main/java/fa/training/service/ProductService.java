package fa.training.service;

import fa.training.entity.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public boolean deleteProduct(Long id);
    public List<Product> getProduct();
    public Product getOneProduct(Long id);
    List<Product> findByProductNameContaining(String name);
}
