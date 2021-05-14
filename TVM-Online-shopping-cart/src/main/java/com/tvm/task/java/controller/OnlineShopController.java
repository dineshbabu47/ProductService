package com.tvm.task.java.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvm.task.java.model.Product;
import com.tvm.task.java.repository.ProductRepo;
import com.tvm.task.java.service.OnlineShopService;



@Controller
public class OnlineShopController {

	@Autowired
	private OnlineShopService Service;
	@Autowired
	private ProductRepo repo;

	// display list of products
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Product> productalllist = repo.findAll();
		model.addAttribute("listProducts", productalllist);
		return "index";
	}
	
	@GetMapping("/showNewProductForm")
	public String showNewProductForm(Model model) {
		// create model attribute to bind form data
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_employee";
	}
	
	@RequestMapping(value = "image/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

	    File serverFile = new File("/home/user/uploads/" + imageName + ".jpg");

	    return Files.readAllBytes(serverFile.toPath());
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product) {
		// save employee to database
		
		
		Service.saveProduct(product);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get product from the service
		Product product = Service.getProductById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("product", product);
		return "update_employee";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.Service.deleteProductById(id);
		return "redirect:/";
	}
	
}