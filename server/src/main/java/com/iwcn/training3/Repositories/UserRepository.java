package com.iwcn.training3.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iwcn.training3.Models.ModelUser;

public interface UserRepository extends CrudRepository<ModelUser, String>{
	List<ModelUser> findAll();
	ModelUser findByNombre(String nombre);
}
