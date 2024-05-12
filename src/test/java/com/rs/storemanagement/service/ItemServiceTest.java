package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Item;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void findByBillIdSuccess() {
        int expectedSize = 5;
        List<Item> list = itemService.findByBillId(24);
        assertNotNull(list);
        assertEquals(expectedSize, list.size());
    }
    @Test
    void findByBillIdNull() {
        List<Item> list = itemService.findByBillId(24);
        assertNotNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillIdSuccess() {

    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillIdSuccess() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemService.saveAll(listExpected);
        assertEquals(listExpected.get(0).getQuantity(), list.get(0).getQuantity());
    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillIdInPriceNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", null, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillIdQuantityNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, null, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillIdInPriceNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", -20000, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemService.saveAll(listExpected);
        assertEquals(20000, list.get(0).getInPrice());
    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillIdQuantityNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, -90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemService.saveAll(listExpected);
        assertEquals(90, list.get(0).getQuantity());
    }

    @Test
    @Transactional
    @Rollback
    void deleteByBillIdSuccess() {
        int expected_number = 5;
        assertEquals(expected_number, itemService.deleteByBillId(24));
    }
    @Test
    @Transactional
    @Rollback
    void deleteByBillIdNull() {
        int expected_number = 0;
        assertEquals(expected_number, itemService.deleteByBillId(1));
    }
}