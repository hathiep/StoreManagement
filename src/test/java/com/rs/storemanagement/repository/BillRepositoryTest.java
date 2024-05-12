package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Bill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Test
    void createBillSuccess(){
       Bill bill = new Bill(LocalDate.parse("2024-05-12"), "Kho Hà Đông", 19000000);
       Bill bill1 = billRepository.save(bill);
       List<Bill> billList = (List<Bill>) billRepository.findAll();
       assertEquals(5,billList.size());
       assertEquals(bill.getDate(), bill1.getDate());
       assertEquals(bill.getsupplierName(), bill1.getsupplierName());
       assertEquals(bill.getTotalCost(), bill1.getTotalCost());
    }

    @Test
    void searchBillByLocalDate(){}
}