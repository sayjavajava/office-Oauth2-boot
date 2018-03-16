package com.example.Repositories;

import com.example.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

   List<Product> findAll();
   Product findById(long id);
}
