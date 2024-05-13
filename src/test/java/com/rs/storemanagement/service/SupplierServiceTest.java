package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Bill;
import com.rs.storemanagement.model.Supplier;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierServiceTest {
    @Autowired
    private SupplierService supplierService;
    @Test
    @Transactional
    @Rollback
    void createSupplierSuccess(){
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", "0987457252");
        supplierService.save(supplier);
        List<Supplier> supplierList = (List<Supplier>) supplierService.findAll();
        Supplier result = supplierList.get(supplierList.size()-1);
        assertEquals(supplier, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfNameNull(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier(null, "Cầu Diễn, Bắc Từ Liêm", "0987457252");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfAddressNull(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", null, "0987457252");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfPhoneNull(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", null);
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfSameName(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Hà Đông", "Cầu Diễn, Bắc Từ Liêm", "0987457252");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfPhoneNotNumber(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", "abcd");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfPhoneLessThan10Number(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", "098765432");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }

    @Test
    @Transactional
    @Rollback
    void createSupplierIfPhoneMoreThan11Number(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", "0987654323224");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createSupplierIfPhoneFirstNumberDifferent0(){
        Supplier expectedPro = null;
        Supplier supplier = new Supplier("Kho Bắc Từ Liêm", "Cầu Diễn, Bắc Từ Liêm", "1987654321");
        Supplier result = supplierService.save(supplier);
        assertEquals(expectedPro, result);
    }

    @Test
    @Transactional
    @Rollback
    void findAllSuccess(){
        List<Supplier> supplierList = (List<Supplier>) supplierService.findAll();
        assertEquals(5, supplierList.size());
    }
    @Test
    @Transactional
    @Rollback
    void findAllFail(){
        List<Supplier> supplierList = (List<Supplier>) supplierService.findAll();
        assertNotEquals(0, supplierList.size());
    }
}