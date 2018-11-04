package com.iwcn.training3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.iwcn.training3.Controllers.ControllerProduct;
import com.iwcn.training3.Repositories.ProductRepository;


@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
}
