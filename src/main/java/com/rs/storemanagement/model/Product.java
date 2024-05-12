package com.rs.storemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private String des;
    private Integer outPrice;
    private Integer quantity;

    public Product() {
    }

    public Product(int id, String name, String image, String des, Integer outPrice, Integer quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.des = des;
        this.outPrice = outPrice;
        this.quantity = quantity;
    }

    public Product(String name, String image, String des, Integer outPrice, Integer quantity) {
        this.name = name;
        this.image = image;
        this.des = des;
        this.outPrice = outPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Integer outPrice) {
        this.outPrice = outPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
