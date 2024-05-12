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
    void findByBillId_Success() {
        int expectedSize = 5;
        List<Item> list = itemService.findByBillId(24);
        assertNotNull(list);
        assertEquals(expectedSize, list.size());
    }
    @Test
    void findByBillId_Null() {
        List<Item> list = itemService.findByBillId(24);
        assertNotNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_Success() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", 20000, 90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertEquals(listExpected.size(), list.size());
        assertEquals(listExpected.get(0).getInPrice(), list.get(0).getInPrice());
        assertEquals(listExpected.get(0).getQuantity(), list.get(0).getQuantity());
        assertEquals(listExpected.get(1).getInPrice(), list.get(1).getInPrice());
        assertEquals(listExpected.get(1).getQuantity(), list.get(1).getQuantity());
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_BillIdIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(null, "Dầu ăn Mezan", 20000, 90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_ProductNameIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, null, 20000, 90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_InPriceIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", null, 90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_QuantityIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", 20000, null, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_TotalPriceIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", 20000, 90, null));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_InPriceIsNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", -20000, 90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void createItemByBillId_QuantityIsNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(28, "Dầu ăn Mezan", 20000, -90, 1800000));
        listExpected.add(new Item(28, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        List<Item> list = itemService.saveAll(listExpected);
        assertNull(list);
    }

    @Test
    @Transactional
    @Rollback
    void updateItemByBillId_Success() {
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
    void updateItemByBillId_BillIdIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, null, "Dầu ăn Mezan", 20000, 90, 1800000));
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
    void updateItemByBillId_ProductNameIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, null, 20000, 90, 1800000));
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
    void updateItemByBillId_InPriceIsNull() {
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
    void updateItemByBillId_QuantityIsNull() {
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
    void updateItemByBillId_TotalPriceIsNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, 90, null));
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
    void updateItemByBillId_InPriceIsNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", -20000, 90, 1800000));
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
    void updateItemByBillId_QuantityIsNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, -90, 1800000));
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
    void deleteByBillId_Success() {
        int expected_number = 5;
        assertEquals(expected_number, itemService.deleteByBillId(24));
    }
    @Test
    @Transactional
    @Rollback
    void deleteByBillId_Null() {
        int expected_number = 0;
        assertEquals(expected_number, itemService.deleteByBillId(1));
    }
}