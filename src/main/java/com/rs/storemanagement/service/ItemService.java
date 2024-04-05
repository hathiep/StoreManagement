package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findByKeyword(String keyword);
    Item save(Item product);
    List<Item> findAll();
    Optional<Item> findById(int id);
    void deleteById(int id);
}
