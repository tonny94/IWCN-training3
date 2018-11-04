package com.iwcn.training3.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iwcn.training3.Models.ModelProduct;


public interface ProductRepository extends CrudRepository<ModelProduct, String>{
	List<ModelProduct> findAll();
	ModelProduct findByCode(String code);
}
