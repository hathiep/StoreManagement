package com.rs.storemanagement.service.impl;

import com.rs.storemanagement.model.Bill;
import com.rs.storemanagement.repository.BillRepository;
import com.rs.storemanagement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;


    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
    @Override
    public Bill save(Bill bill){
        return billRepository.save(bill);
    }
}
