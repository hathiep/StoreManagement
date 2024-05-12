package com.rs.storemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int billId;
    private String productName;
    private Integer inPrice;
    private Integer quantity;
    private Integer totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getInPrice() {
        return inPrice;
    }

    public void setInPrice(Integer inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Item(int id, int billId, String productName, Integer inPrice, Integer quantity, Integer totalPrice) {
        this.id = id;
        this.billId = billId;
        this.productName = productName;
        this.inPrice = inPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Item() {
    }
}
