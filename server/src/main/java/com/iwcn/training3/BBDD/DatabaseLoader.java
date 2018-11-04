package com.iwcn.training3.BBDD;

import org.springframework.beans.factory.annotation.Autowired;

import com.iwcn.training3.Models.ModelUser;
import com.iwcn.training3.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class DatabaseLoader {
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {
		
		
//		List<String> adminRoles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
//                new SimpleGrantedAuthority("ROLE_ADMIN"));
//        List<String> userRoles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		List<String> adminRoles = new ArrayList<String>();
		adminRoles.add("ROLE_USER");
		adminRoles.add("ROLE_ADMIN");
		
        List<String> userRoles = new ArrayList<String>();
        userRoles.add("ROLE_USER");
        userRepository.save(new ModelUser("root", "root1", adminRoles));
        userRepository.save(new ModelUser("user", "user1", userRoles));
	}

}
