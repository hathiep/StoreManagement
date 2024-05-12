package com.rs.storemanagement.controller;

import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/items/search")
    public List<Item> searchItems(@RequestParam(name="bill_id") int billId){
        return itemService.findByBillId(billId);
    }

    @PostMapping("/items/save_selected")
    public void saveSelectedItems(@RequestBody List<Item> selectedItems){
        itemService.saveAll(selectedItems);
    }

}
