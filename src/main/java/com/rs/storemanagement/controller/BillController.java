package com.rs.storemanagement.controller;

import com.rs.storemanagement.model.Bill;
import com.rs.storemanagement.model.DTO;
import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.service.BillService;
import com.rs.storemanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/bill/save_selected")
    public void saveSelectedItems(@RequestBody DTO data){
        System.out.println(data.getSupplierName());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();

        int sum = 0;
        for(Item i : data.getItemsToSave()){
            sum += i.getTotalPrice();
        }
        Bill bill = new Bill(now, data.getSupplierName(), sum);
        Bill savedBill = billService.save(bill);
        if(savedBill != null) {
            for(Item i : data.getItemsToSave()){
                sum += i.getTotalPrice();
                i.setBillId(savedBill.getId());
            }
            itemService.saveAll(data.getItemsToSave());
        }
    }

}
