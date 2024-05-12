package com.rs.storemanagement.service.impl;

import com.rs.storemanagement.model.Product;
import com.rs.storemanagement.repository.ProductRepository;
import com.rs.storemanagement.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        return productRepository.searchProduct(keyword);
    }
    @Override
    public Product findByName(String product_name) {
        return productRepository.findByName(product_name);
    }

    @Override
    public Product save(Product product) {
        Product getProduct = productRepository.findByName(product.getName());
        if(getProduct != null){
            return null;
        }
        if(product.getName() == null|| product.getImage() == null || product.getQuantity() == null || product.getOutPrice() == null || product.getOutPrice() < 0 || product.getQuantity()<0){
            return null;
        }


        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

 /*   @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    } */
    @Override
    public int deleteById(int id) {
        return productRepository.deleteByProductId(id);
    }
}
