package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query(value = "SELECT * FROM product p WHERE" +
            " p.name LIKE %:keyword%", nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM product p WHERE" +
            " p.name = %:product_name% LIMIT 1", nativeQuery = true)
    Product findByName(@Param("product_name") String product_name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Product p WHERE p.id = :id")
    int deleteByProductId(@Param("id") int id);
}
