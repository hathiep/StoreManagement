package com.rs.storemanagement.repository;

import com.rs.storemanagement.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void findByBillId() {
        int expectedSize = 5;
        List<Item> list = itemRepository.searchItem(24);
        assertNotNull(list);
        assertEquals(expectedSize, list.size());
    }
    @Test
    void updateItemByBillIdSuccess() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemRepository.saveAll(listExpected);
        assertEquals(listExpected.get(0).getQuantity(), list.get(0).getQuantity());
    }

    @Test
    void updateItemByBillIdInPriceNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", null, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemRepository.saveAll(listExpected);
        assertEquals(20000, list.get(0).getInPrice());
    }

    @Test
    void updateItemByBillIdQuantityNull() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, null, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemRepository.saveAll(listExpected);
        assertEquals(90, list.get(0).getQuantity());
    }

    @Test
    void updateItemByBillIdInPriceNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", -20000, 90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemRepository.saveAll(listExpected);
        assertEquals(20000, list.get(0).getInPrice());
    }

    @Test
    void updateItemByBillIdQuantityNegative() {
        List<Item> listExpected = new ArrayList<>();
        listExpected.add(new Item(36, 24, "Dầu ăn Mezan", 20000, -90, 1800000));
        listExpected.add(new Item(37, 24, "Nước mắm Nam Ngư", 20000, 70, 1400000));
        listExpected.add(new Item(38, 24, "Dầu ăn Neptune", 35000, 110, 3850000));
        listExpected.add(new Item(39, 24, "Bột giặt Omo 1kg", 55000, 80, 4400000));
        listExpected.add(new Item(40, 24, "Dầu ăn Neptune 1 lít", 35000, 100, 3500000));
        List<Item> list = itemRepository.saveAll(listExpected);
        assertEquals(90, list.get(0).getQuantity());
    }


    @Test
    void deleteByBillId() {
        int expected_number = 5;
        assertEquals(expected_number, itemRepository.deleteByBillId(24));
    }
}