package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(value = "SELECT * FROM item i WHERE" +
            " i.bill_id = %:billId% ", nativeQuery = true)
    List<Item> searchItem(@Param("billId") Integer billId);

    @Transactional
    void deleteByBillId(Integer billId);
}
