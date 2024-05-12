package com.rs.storemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String supplierName;
    private Integer totalCost;


    public Bill(int id, LocalDate date, String supplierName, Integer totalCost) {
        this.id = id;
        this.date = date;
        this.supplierName = supplierName;
        this.totalCost = totalCost;
    }

    public Bill(LocalDate date, String supplierName, Integer totalCost) {
        this.date = date;
        this.supplierName = supplierName;
        this.totalCost = totalCost;
    }

    public Bill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getsupplierName() {
        return supplierName;
    }

    public void setsupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}
