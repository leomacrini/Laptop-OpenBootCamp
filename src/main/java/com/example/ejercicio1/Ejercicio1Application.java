package com.example.ejercicio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicio1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejercicio1Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "Lenovo", "Legion", 500, "Intel", 999.99);
		Laptop laptop2 = new Laptop(null, "Sony", "Vaio", 750, "Intel", 800.00);
		Laptop laptop3 = new Laptop(null, "Lenovo", "Yoga", 1000, "Amd", 500.00);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);

	}

}
