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
    @PostMapping("/item/create")
    public Item create(@RequestBody Item item){
        return itemService.save(item);
    }

    @GetMapping("/items")
    public List<Item> getAll(){
        return itemService.findAll();
    }

    @GetMapping("/item")
    public Item getById(@RequestParam(name="id") int id){
        return (Item) itemService.findById(id).orElse(null);
    }

    @GetMapping("/items/search")
    public List<Item> searchItems(@RequestParam("keyword") String keyword){
        return itemService.findByKeyword(keyword);
    }

    @DeleteMapping("/item/delete")
    public void delete(@RequestParam(name="id") int id){
        itemService.deleteById(id);
    }

    @PutMapping("/item/update")
    public void update(@RequestBody Item item){
        itemService.save(item);
    }

}
