package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Supplier save(Supplier supplier);
    List<Supplier> findAll();
}
