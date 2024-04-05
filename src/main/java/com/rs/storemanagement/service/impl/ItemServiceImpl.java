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
    public List<Item> findByKeyword(String keyword) {
        return itemRepository.searchItem(keyword);
    }

    @Override
    public Item save(Item Item) {
        return itemRepository.save(Item);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(int id) {
        return itemRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }
}
