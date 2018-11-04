package com.iwcn.training3.Controllers;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.annotations.common.util.impl.Log_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.iwcn.training3.Models.ModelError;
import com.iwcn.training3.Models.ModelProduct;
import com.iwcn.training3.Repositories.ProductRepository;

@RestController
public class ControllerProduct {
	
	//---------------------------- -PRODUCT- ----------------------------//
	//Variables
	@Autowired
	private ProductRepository productRepository;
	
	//Todos los productos
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<ModelProduct>> listAllProducts() {
    	
        List<ModelProduct> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ModelProduct>>(products, HttpStatus.OK);
    }
	
	
	//Borrar un producto
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        ModelProduct product = productRepository.findByCode(id);
        if (product == null) {
            return new ResponseEntity(new ModelError("No se puede borrar el producto "+id,"","",""),HttpStatus.NOT_FOUND);
        }
        productRepository.delete(productRepository.findByCode(id));
        return new ResponseEntity<ModelProduct>(HttpStatus.NO_CONTENT);
    }
	
    //Nuevo producto
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@ModelAttribute("product") ModelProduct product, UriComponentsBuilder ucBuilder) {
        //logger.info("Creando Producto : {}", product);
 
    	if(productRepository.findByCode(product.getCode()) != null) {
            return new ResponseEntity(new ModelError("El producto " + product.getName() + " ya existe.","","",""),HttpStatus.CONFLICT);
        }
    	productRepository.save(new ModelProduct(product.getCode(),product.getName(),product.getDescription(),product.getPrice()));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/resume/{code}").buildAndExpand(product.getCode()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    //Ver producto
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        ModelProduct product = productRepository.findByCode(id);
        if (product == null) {
        	return new ResponseEntity(new ModelError("El producto " + id + " no existe.","","",""),HttpStatus.CONFLICT);
            }
        return new ResponseEntity<ModelProduct>(product, HttpStatus.OK);
    }
    
}