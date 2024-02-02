package com.gestion_biblio.gestion_biblio;

import com.gestion_biblio.gestion_biblio.models.Role;
import com.gestion_biblio.gestion_biblio.models.User;
import com.gestion_biblio.gestion_biblio.repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Biblio APIs", version = "1.0", description = "Gestion Bibliotheque"))
public class GestionBiblioApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionBiblioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();
			user.setEmail("dhaouelamri@gmail.com");
			user.setUsername("Dhaou Amri");
			user.setAdresse("Ben Guerden");
			user.setRole(Role.ADMIN);
			user.setNuméroTel(29012558);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
