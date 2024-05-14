package com.rs.storemanagement.service;

import com.rs.storemanagement.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    @Rollback
    void createProductIfExist(){
        Product expectedPro = null;
        Product data = new Product("Nước mắm Nam Ngư","abc","abc",123000,12);
        assertEquals(expectedPro, productService.save(data));
    }

    @Test
    @Transactional
    @Rollback
    void createProductSuccess(){
        Product expectedPro = new Product("Xi Dau","xidau.image","",123000,12);
        productService.save(expectedPro);
        Product result = productService.findByName("Xi Dau");
        assertEquals(expectedPro, result);
    }


    @Test
    @Transactional
    @Rollback
    void createProductIfNameNull(){
        Product expectedPro = null;
        Product data = new Product(null,"xidau.image","",123000,12);
        Product result =  productService.save(data);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createProductIfImageNull(){
        Product expectedPro = null;
        Product data = new Product("Xi Dau",null,"",123000,12);
        Product result =  productService.save(data);
        assertEquals(expectedPro, result);
    }
    @Test
    @Transactional
    @Rollback
    void createProductIfOutPriceNull(){
        Product data = new Product("Xi Dau","xidau.image","",null,12);
        Product result = productService.save(data);
        assertEquals(null, result);
    }
    @Test
    @Transactional
    @Rollback
    void createProductIfQuantityNull(){
        Product data = new Product("Xi Dau","xidau.image","",123456,null);
        Product result = productService.save(data);
        assertEquals(null, result);
    }

    @Test
    @Transactional
    @Rollback
    void createProductIfOutPriceNegative(){
        Product data = new Product("Xi Dau","xidau.image","",-123456,0);
        Product result = productService.save(data);
        assertEquals(null, result);
    }

    @Test
    @Transactional
    @Rollback
    void updateProductIsExist(){
        String update = "Dầu ăn Mezan";
        Product data = new Product(20,update,"https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,2);
        assertEquals(null, productService.save(data));
    }

    @Test
    @Transactional
    @Rollback
    void updateProductSuccess(){
        String update = "Mì ly Nissin goi to";
        Product data = new Product(20,update,"https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,1);
        productService.save(data);
        Product result = productService.findByName("Mì ly Nissin goi to");
        assertEquals(data.getName(), result.getName());
    }


    @Test
    @Transactional
    @Rollback
    void updateProductIfNameNull(){
        String update = null;
        Product data = new Product(20,update,"https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,0);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateProductIfImageNull(){
        String update = null;
        Product data = new Product(20,"Mì ly Nissin",update,"Mì ly Nissin",8000,0);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateProductIfOutPriceNull(){
        Integer update = null;
        Product data = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",update,0);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateProductIfQuantityNull(){
        Integer update = null;
        Product data = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,update);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }
    @Test
    @Transactional
    @Rollback
    void updateProductIfOutPriceNegative(){
        Integer update = -200;
        Product data = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin", update,0);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }

    @Test
    @Transactional
    @Rollback
    void updateProductIfNameExist(){
        String update = "Dầu ăn Mezan";
        Product data = new Product(20,update,"https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,0);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }

    @Test
    @Transactional
    @Rollback
    void updateProductIfNameExist2(){
        String update = "Mì ly Nissin";
        Product data = new Product(20,update,"https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",9000,0);
        Product result = productService.save(data);;
        assertEquals(data.getOutPrice(), result.getOutPrice());
    }

    @Test
    void findByIdSuccess(){
        Product expected = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,0);
        Integer id = 20;
        Optional<Product> productList = productService.findById( id);
        assertEquals(expected.getId(),productList.get().getId());

    }

    @Test
    void findByIdFail(){
        boolean isempty = true;
        Integer id = 21;
        Optional<Product> productList = productService.findById( id);
        assertEquals(isempty, productList.isEmpty());

    }

    @Test
    void findByKeywordSuccess(){
        int quantity = 3;
        List<Product> listPro = productService.findByKeyword("Dầu ăn");
        assertEquals(quantity, listPro.size());
    }
    @Test
    void findByKeywordFail(){
        int quantity = 0;
        List<Product> listPro = productService.findByKeyword("Bánh Chuối");
        assertEquals(quantity, listPro.size());
    }

    @Test
    @Transactional
    @Rollback
    void deleteByIdSuccess(){
        int quantityDelete = 1;
        int actual = productService.deleteById(20);
        Optional<Product> product = productService.findById(20);
        assertEquals(true,product.isEmpty());
        assertEquals(quantityDelete, actual);
    }

    @Test
    @Transactional
    @Rollback
    void deleteByIdFail(){
        int quantityDelete = 0;
        int actual = productService.deleteById(22);

        assertEquals(quantityDelete, actual);
    }

    @Test
    @Transactional
    @Rollback
    void updateProductIfQuantityNegative(){
        Integer update = -10;
        Product data = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,update);
        Product result = productService.save(data);;
        assertEquals(null, result);
    }

    @Test
    void findByNameSuccess(){
        Product data = new Product(20,"Mì ly Nissin","https://www.nissinfoods.vn/upload/cuaxotcaysingapore_-04-01-2021-09-51-30.png","Mì ly Nissin",8000,0);
        Product findByName = productService.findByName("Mì ly Nissin");
        assertEquals(data.getName(),findByName.getName());
    }
    @Test
    void findByNameFail(){
        Product data = null;
        Product findByName = productService.findByName("Mỳ li Nissin");
        assertEquals(data,findByName);
    }

    @Test
    void findAllSuccess(){
        int expectedSize = 20;
        int actual = productService.findAll().size();
        assertEquals(expectedSize,actual);
    }












}