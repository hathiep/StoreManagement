package com.rs.storemanagement.service.impl;

import com.rs.storemanagement.model.Product;
import com.rs.storemanagement.model.Supplier;
import com.rs.storemanagement.repository.SupplierRepository;
import com.rs.storemanagement.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(supplier.getName() == null || supplier.getAddress() == null || supplier.getPhone() == null ){
            return null;
        }
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
        for (Supplier s : supplierList){
            if(supplier.getName().equals(s.getName())){
                return null;
            }
        }

        Pattern pattern = Pattern.compile("^0\\d{9,10}$");
        Matcher matcher = pattern.matcher(supplier.getPhone());
        if (matcher.matches() == false){
            return null;
        }

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
