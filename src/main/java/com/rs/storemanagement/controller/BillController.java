package com.rs.storemanagement.controller;

import com.rs.storemanagement.model.Bill;
import com.rs.storemanagement.model.DTO;
import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.model.Product;
import com.rs.storemanagement.service.BillService;
import com.rs.storemanagement.service.ItemService;
import com.rs.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private ProductService productService;

    @PostMapping("/bill/save_selected")
    public void saveSelectedItems(@RequestBody DTO data){
        System.out.println(data.getSupplierName());

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
                Product product = (Product) productService.findByName(i.getProductName());
                product.setQuantity(product.getQuantity()+i.getQuantity());
                productService.save(product);
            }
            itemService.saveAll(data.getItemsToSave());
        }
    }

    @GetMapping("/bills")
    public List<Bill> getAll(){
        return billService.findAll();
    }

    @GetMapping("/bill/search")
    public List<Bill> search(@Param("date") String date){
        date = date.replaceAll("/","-");

        LocalDate newdate = LocalDate.parse(date);
        return billService.search(newdate);
    }

    @GetMapping("/bill/get_item")
    public List<Item> getItemByBillId(@Param("billId") Integer billId){
        return itemService.findByBillId(billId);
    }

    @PutMapping("/bill/update")
    public void updateBill(@RequestBody DTO data){
        System.out.println(data.getSupplierName());

        int billId = data.getItemsToSave().get(0).getBillId();
        LocalDate now = billService.findById(billId).get().getDate();

        int sum = 0;
        for(Item i : data.getItemsToSave()){
            sum += i.getTotalPrice();
        }
        Bill bill = new Bill(billId,now, data.getSupplierName(), sum);
        Bill savedBill = billService.save(bill);
        if(savedBill != null) {
            for(Item i : data.getItemsToSave()){
                Product product = (Product) productService.findByName(i.getProductName());
                product.setQuantity(product.getQuantity()+i.getQuantity());
                productService.save(product);
            }
            itemService.saveAll(data.getItemsToSave());
        }
    }

}
