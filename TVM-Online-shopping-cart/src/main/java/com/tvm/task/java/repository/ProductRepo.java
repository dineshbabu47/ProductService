package com.tvm.task.java.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvm.task.java.model.Product;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
