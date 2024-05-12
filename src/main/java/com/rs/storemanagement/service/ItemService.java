package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findByBillId(int billId);
    void saveAll(List<Item> items);
    void deleteByBillId(Integer billId);
}
