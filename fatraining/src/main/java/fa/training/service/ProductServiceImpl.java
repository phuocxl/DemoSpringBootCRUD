package fa.training.service;

import fa.training.entity.Product;
import fa.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        if(product != null) {
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if(product != null) {
            Product product1 = productRepository.getReferenceById(id);
            if(product1 != null) {
                product1.setProductName(product.getProductName());
                product1.setCatalogy(product.getCatalogy());
                product1.setDesc(product.getDesc());
                product1.setColor(product.getColor());
                product1.setPrice(product.getPrice());
                return productRepository.saveAndFlush(product1);
            }
        }
        return null;
    }



    @Override
    public boolean deleteProduct(Long id) {
        if(id >= 1) {
            Product product = productRepository.getReferenceById(id);
            if(product != null) {
                productRepository.delete(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneProduct(Long id) {
        return productRepository.getReferenceById(id);
    }



    @Override
    public List<Product> findByProductNameContaining(String name) {
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public Page<Product> getPageProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Page<Product> findByProductNameContaining(String name, Pageable pageable) {
        return productRepository.findByProductNameContaining(name, pageable);
    }
}
