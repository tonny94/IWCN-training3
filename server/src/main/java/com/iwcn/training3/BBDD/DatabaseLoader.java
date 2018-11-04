package com.iwcn.training3.BBDD;

import org.springframework.beans.factory.annotation.Autowired;

import com.iwcn.training3.Models.ModelProduct;
import com.iwcn.training3.Repositories.ProductRepository;

import java.util.List;

import javax.annotation.PostConstruct;

public class DatabaseLoader {
	@Autowired
	private ProductRepository productRepository;
	
	@PostConstruct
	private void initDatabase() {
//		productRepository.save(
//				new Product("1818","Fanta","Bebida",7)
//		);
		
//		List<Product> productList = productRepository.findAll();
//		productList.stream().forEach( product -> System.out.println("Code: " + product.getCode()) );
	}

}
