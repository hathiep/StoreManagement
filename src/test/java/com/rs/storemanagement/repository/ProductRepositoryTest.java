package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void searchProduct() {
        int expected_size = 3;
        List<Product> productList = (List<Product>) productRepository.searchProduct("dau");

        assertEquals(expected_size, productList.size());
    }
    @Test
    void findByName() {
    }

    @Test
    void testCreateTrue(){
        Product product = new Product(22, "Dầu ăn", "abc", "abc", 1000, 0);
        Product product2 = productRepository.save(product);
        assertEquals("Dầu ăn", product2.getName());
        assertEquals(1000, product2.getOutPrice());
    }

    @Test
    void testCreateIsNull(){
        Product product = new Product(21, null, "null", "null", 0, 0);
        assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.save(product);
        });
    }
    @Test
    void testCreateFalse(){
        Product product = new Product(22, "Dầu ăn Mezan", "abc", "abc", 1000, 0);
        Product product2 = productRepository.save(product);
        List<Product> list = productRepository.findAll();
        assertEquals(20, list.size());
    }

    @Test
    void delete() {
        int expected_number = 1;
        assertEquals(expected_number, productRepository.deleteByProductId(15));
    }

}