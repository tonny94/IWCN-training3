package com.example.training2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
public class ProductController {

	private Map<String, Product> productos = new HashMap<String, Product>();

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	
	//Todos los productos
		@GetMapping("/list")
		@ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Product>> listAllProducts() {
			
			if (productos.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Product>>(productos.values().stream().collect(Collectors.toList()), HttpStatus.OK);
	    }
		
		@PostMapping("/add")
		@ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<?> addProducts(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
	        //logger.info("Creando Producto : {}", product);
	 
	    	if(productRepository.findByCode(product.getCode()) != null) {
	            return new ResponseEntity(new Error("El producto " + product.getName() + " ya existe.","","",""),HttpStatus.CONFLICT);
	        }
	    	productRepository.save(new Product(product.getCode(),product.getName(),product.getDescription(),product.getPrice()));
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/resume/{code}").buildAndExpand(product.getCode()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
		
		//Ver producto
	    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
	        Product product = productRepository.findByCode(id);
	        if (product == null) {
	        	return new ResponseEntity(new Error("El producto " + id + " no existe.","","",""),HttpStatus.CONFLICT);
	            }
	        return new ResponseEntity<Product>(product, HttpStatus.OK);
	    }
		
		
		
		//Borrar un producto
	    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
	        Product product = productRepository.findByCode(id);
	        if (product == null) {
	            return new ResponseEntity(new Error("No se puede borrar el producto "+id,"","",""),HttpStatus.NOT_FOUND);
	        }
	        productRepository.delete(productRepository.findByCode(id));
	        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	    }
	    
	  //Devolver usuario
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	    public ResponseEntity<?> getUser(@PathVariable("name") String name) {
	        User user = userRepository.findByName(name);
	        if (user == null) {
	        	return new ResponseEntity(new Error("El usuario " + name + " no existe.","","",""),HttpStatus.CONFLICT);
	            }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }