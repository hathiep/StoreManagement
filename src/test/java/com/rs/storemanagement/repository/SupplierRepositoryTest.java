package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void createSupplierSuccess(){
        Supplier supplier = new Supplier(10,"Kho Bắc Từ Liêm","Cầu Diễn, Bắc Từ Liêm", "0987452499");
        Supplier supplier1 = supplierRepository.save(supplier);
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
        assertEquals(6, supplierList.size());
        assertEquals(supplier.getName(), supplier1.getName());
        assertEquals(supplier.getAddress(), supplier1.getAddress());
        assertEquals(supplier.getPhone(), supplier1.getPhone());
    }
    @Test
    void searchSupplier(){
        int expected_size = 2;
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.searchSupplier("thanh");
        assertEquals(expected_size, supplierList.size());
    }
    @Test
    void getAllSupplier(){
        int expected_size = 5;
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
        assertEquals(expected_size, supplierList.size());
    }
    @Test
    void findById(){
        Supplier supplierExpected = new Supplier(1,"Kho Hà Đông","Hà Đông","0123456789");
        Optional<Supplier> supplierOptional = (Optional<Supplier>) supplierRepository.findById(1);
        assertEquals(supplierExpected.getId(), supplierOptional.get().getId());
        assertEquals(supplierExpected.getName(), supplierOptional.get().getName());
        assertEquals(supplierExpected.getAddress(), supplierOptional.get().getAddress());
        assertEquals(supplierExpected.getPhone(), supplierOptional.get().getPhone());
    }
}