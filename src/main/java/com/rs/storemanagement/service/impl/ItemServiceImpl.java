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
    public List<Item> saveAll(List<Item> items){
        for(Item i : items){
            if(i.getBillId() == null || i.getProductName() == null || i.getInPrice() == null || i.getQuantity() == null || i.getTotalPrice() == null ) return null;
            if(i.getInPrice() < 0 || i.getQuantity() < 1 || i.getTotalPrice() < 0) return null;
        }
        for(Item i : items){
            if(i.getId()==null && !itemRepository.searchItem(i.getBillId()).isEmpty()){
                return null;
            }
            if(i.getId()!=null && itemRepository.searchItem(i.getBillId()).isEmpty()){
                return null;
            }
        }
        return itemRepository.saveAll(items);
    }
    @Override
    public int deleteByBillId(Integer billId){
        return itemRepository.deleteByBillId(billId);
    }
}
