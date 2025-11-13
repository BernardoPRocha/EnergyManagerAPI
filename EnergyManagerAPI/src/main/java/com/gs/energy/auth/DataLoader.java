package com.gs.energy.auth;

import com.gs.energy.auth.model.Role;
import com.gs.energy.auth.model.User;
import com.gs.energy.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // cria um admin se não existir
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // senha: admin123
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            userRepository.save(admin);
            System.out.println("Admin user created: username=admin password=admin123");
        }
    }
}
