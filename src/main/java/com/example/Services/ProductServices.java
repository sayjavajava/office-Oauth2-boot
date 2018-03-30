package com.example.Services;

import com.example.Repositories.ProductRepo;
import com.example.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    ProductRepo productRepo;

    @Autowired
    ProductServices(ProductRepo productrepo){
        this.productRepo=productrepo;
    }

    public void save(Product product){
       this.productRepo.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepo.findAll();
    }
    public Product DeleteProduct(int id ){

        Product product = findOne(id);
        if(product !=null) {
            productRepo.delete(product);}
        return product;
    }

   public Product findOne(int id){
        long pid = id;
        return productRepo.findById(pid);
   }
}
