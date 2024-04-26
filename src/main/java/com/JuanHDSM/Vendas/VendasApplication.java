package com.JuanHDSM.Vendas;

import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.entities.enums.UserRole;
import com.JuanHDSM.Vendas.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String passwordEncoder = new BCryptPasswordEncoder().encode("123456");

		User user = new User("juan.holy", passwordEncoder, UserRole.ADMIN);
		repository.save(user);
	}
}
