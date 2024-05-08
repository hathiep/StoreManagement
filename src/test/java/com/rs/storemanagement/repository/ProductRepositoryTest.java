package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void searchProduct() {
        int expected_size = 2;
        List<Product> productList = (List<Product>) productRepository.searchProduct("muoi");

        assertEquals(expected_size, productList.size());
    }
    @Test
    void findByName() {
    }

    @Test
    @Transactional

    void save(){
        Product product = new Product(14, "abc", "abc", "abc", 1, 1);
        Product product2 = productRepository.save(product);
        assertEquals("abc", product2.getName());
    }

    @Test
    void delete() {
        int expected_number = 1;
        assertEquals(expected_number, productRepository.deleteByProductId(15));
    }

}