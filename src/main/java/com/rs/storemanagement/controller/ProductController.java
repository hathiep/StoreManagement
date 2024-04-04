package com.rs.storemanagement.controller;

import com.rs.storemanagement.model.Product;
import com.rs.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/product/create")
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping("/product")
    public Product getById(@RequestParam(name="id") int id){
        return (Product) productService.findById(id).orElse(null);
    }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword){
        return productService.findByKeyword(keyword);
    }

    @DeleteMapping("/product/delete")
    public void delete(@RequestParam(name="id") int id){
        productService.deleteById(id);
    }

    @PutMapping("/product/update")
    public void update(@RequestBody Product product){
        productService.save(product);
    }

}
