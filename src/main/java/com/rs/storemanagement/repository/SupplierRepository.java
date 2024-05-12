package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT * FROM supplier s WHERE" +
            " s.name LIKE %:keyword%", nativeQuery = true)
    List<Supplier> searchSupplier(@Param("keyword") String keyword);
}
