package com.rs.storemanagement.controller;

import com.rs.storemanagement.model.Supplier;
import com.rs.storemanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @PostMapping("/supplier/create")
    public Supplier create(@RequestBody Supplier supplier){
        return supplierService.save(supplier);
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAll(){
        return supplierService.findAll();
    }

}

