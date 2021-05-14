package com.tvm.task.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvm.task.java.model.Product;
import com.tvm.task.java.repository.ProductRepo;



@Service
public class OnlineShopService {

	@Autowired
	private ProductRepo repo;

	public List<Product> getAllEmployees() {
		return repo.findAll();
	}

	public void saveProduct(Product product) {
		this.repo.save(product);
	}

	public Product getProductById(long id) {
		Optional<Product> optional = repo.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Product not found for id :: " + id);
		}
		return product;
	}

	public void deleteProductById(long id) {
		this.repo.deleteById(id);
	}

}
