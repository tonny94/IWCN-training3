package com.example.training2.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.training2.Models.Product;
import com.example.training2.Repositories.ProductRepository;
import com.example.training2.Models.Error;

@RestController
public class ProductList {

	private Map<String, Product> productos = new HashMap<String, Product>();

	@Autowired
	private ProductRepository productRepository;
	
	//Todos los productos
		@GetMapping("/list")
		@ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<?> listAllProducts() {
			//productos.put("12345", prod);
			//return productos.values().stream().collect(Collectors.toList());
	    	
	        List<Product> products = productRepository.findAll();
	        if (products.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	    }
		
		
		
		
		
		
		
}