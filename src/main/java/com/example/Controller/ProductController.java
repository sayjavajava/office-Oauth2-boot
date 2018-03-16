package com.example.Controller;

import com.example.Entities.Product;
import com.example.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/product"})
public class ProductController {

    @Autowired
    ProductServices productServices;


@GetMapping
public List<Product> findall(){
    return  this.productServices.getAllProducts();
}


    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product products)  {
        System.out.println("I am being called");
        if(products !=null){

            System.out.println(products.isInStock());
            this.productServices.save(products);
        return new ResponseEntity<Product>(products,HttpStatus.CREATED);
        }else {
        return new ResponseEntity<Product>(products,HttpStatus.CONFLICT);}
    }
    @DeleteMapping(path={"/{id}"})
    public Product delete(@PathVariable("id") int id) {
            return productServices.DeleteProduct(id);
    }


}
