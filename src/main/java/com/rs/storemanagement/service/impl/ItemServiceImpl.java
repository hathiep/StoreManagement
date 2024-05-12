package com.rs.storemanagement.service.impl;

import com.rs.storemanagement.model.Item;
import com.rs.storemanagement.repository.ItemRepository;
import com.rs.storemanagement.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findByBillId(int billId) {
        return itemRepository.searchItem(billId);
    }

    @Override
    public void saveAll(List<Item> items){
        itemRepository.saveAll(items);
    }
    @Override
    public int deleteByBillId(Integer billId){
        return itemRepository.deleteByBillId(billId);
    }
}
