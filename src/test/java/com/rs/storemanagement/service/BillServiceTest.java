package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Bill;
import com.rs.storemanagement.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillServiceTest {

    @Autowired
    private BillService billService;

    @Test
    @Transactional
    @Rollback
    void createBillSuccess(){
        Bill bill = new Bill(LocalDate.parse("2024-05-13"), "Kho Bắc Từ Liêm", 20000000);
        billService.save(bill);
        List<Bill> billList = (List<Bill>) billService.findAll();
        Bill result = billList.get(billList.size()-1);
        assertEquals(bill, result);
    }
    @Test
    @Transactional
    @Rollback
    void createBillIfDateNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(null, "Kho Bắc Từ Liêm", 20000000);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createBillIfSupplierNameNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(LocalDate.parse("2024-05-13"), null, 20000000);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createBillIfTotalCostNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(LocalDate.parse("2024-05-13"), "Kho Bắc Từ Liêm", null);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createBillIfTotalCostNegative(){
        Bill expectedPro = null;
        Bill bill = new Bill(LocalDate.parse("2024-05-13"), "Kho Bắc Từ Liêm", -1);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void findByIduccess(){
        Bill bill = new Bill(24, LocalDate.parse("2024-05-11"), "Kho Hà Đông", 19550000);
        int id = 24;
        Optional<Bill> result = billService.findById(id);
        assertEquals(bill.getId(), result.get().getId());
        assertEquals(bill.getDate(), result.get().getDate());
        assertEquals(bill.getsupplierName(), result.get().getsupplierName());
        assertEquals(bill.getTotalCost(), result.get().getTotalCost());
    }

    @Test
    @Transactional
    @Rollback
    void finByIdFail(){
        boolean isempty = true;
        int id = 28;
        Optional<Bill> result = billService.findById(id);
        assertEquals(isempty, result.isEmpty());
    }
    @Test
    @Transactional
    @Rollback
    void updateBillSuccess(){
        Bill bill = new Bill(24,LocalDate.parse("2024-05-11"), "Kho Bắc Từ Liêm", 20000000);
        billService.save(bill);
        Optional<Bill> result = (Optional<Bill>) billService.findById(24);
        assertEquals(bill.getId(), result.get().getId());
        assertEquals(bill.getDate(), result.get().getDate());
        assertEquals(bill.getsupplierName(), result.get().getsupplierName());
        assertEquals(bill.getTotalCost(), result.get().getTotalCost());
    }
    @Test
    @Transactional
    @Rollback
    void updateBillIfDateNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(24,null, "Kho Bắc Từ Liêm", 20000000);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateBillIfSupplierNameNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(24,LocalDate.parse("2024-05-13"), null, 20000000);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateBillIfTotalCostNull(){
        Bill expectedPro = null;
        Bill bill = new Bill(24,LocalDate.parse("2024-05-13"), "Kho Bắc Từ Liêm", null);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateBillIfTotalCostNegative(){
        Bill expectedPro = null;
        Bill bill = new Bill(14,LocalDate.parse("2024-05-13"), "Kho Bắc Từ Liêm", -1);
        Bill result = billService.save(bill);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void deleteByIdSuccess(){
        int quantityDelete = 1;
        int actual = billService.deleteById(24);
        Optional<Bill> bill = billService.findById(24);
        assertEquals(true,bill.isEmpty());
        assertEquals(quantityDelete, actual);
    }
    @Test
    @Transactional
    @Rollback
    void deleteByIdFail(){
        int quantityDelete = 0;
        int actual = billService.deleteById(22);
        assertEquals(quantityDelete, actual);
    }
    @Test
    @Transactional
    @Rollback
    void findAllSuccess(){
        List<Bill> billList = (List<Bill>) billService.findAll();
        assertEquals(4, billList.size());
    }
    @Test
    @Transactional
    @Rollback
    void findAllFail(){
        List<Bill> billList = (List<Bill>) billService.findAll();
        assertNotEquals(0, billList.size());
    }
}