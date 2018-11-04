package com.iwcn.training3.BBDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.iwcn.training3.Models.ModelUser;
import com.iwcn.training3.Repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

public class DatabaseLoader {
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {
		
		
		List<GrantedAuthority> adminRoles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));
        List<GrantedAuthority> userRoles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        userRepository.save(new ModelUser("root", "root1", adminRoles));
        userRepository.save(new ModelUser("user", "user1", userRoles));
	}

}
