package pro.edwx.demo_hexagonal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;

@SpringBootApplication
public class DemoHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHexagonalApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);

            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);
        };
    }
}
