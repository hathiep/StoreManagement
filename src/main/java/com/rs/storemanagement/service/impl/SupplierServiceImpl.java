package com.rs.storemanagement.service.impl;

import com.rs.storemanagement.model.Supplier;
import com.rs.storemanagement.repository.SupplierRepository;
import com.rs.storemanagement.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findByKeyword(String keyword) {
        return supplierRepository.searchSupplier(keyword);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(int id) {
        return supplierRepository.findById(id);
    }

}
