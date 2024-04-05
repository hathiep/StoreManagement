package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(value = "SELECT * FROM item i WHERE" +
            " i.bill_id LIKE %:keyword% OR", nativeQuery = true)
    List<Item> searchItem(@Param("keyword") String keyword);
}
