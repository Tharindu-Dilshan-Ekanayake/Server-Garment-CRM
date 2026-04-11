package com.garmentsystem.crm.config;

import com.garmentsystem.crm.model.Position;
import com.garmentsystem.crm.model.Role;
import com.garmentsystem.crm.model.User;
import com.garmentsystem.crm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDefaultAdmin(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Only seed when there are no users yet (fresh DB, e.g. new Docker volume)
            if (userRepository.count() == 0) {
                String adminEmail = "admin@garment.local";

                if (userRepository.findByEmail(adminEmail).isEmpty()) {
                    User admin = User.builder()
                            .name("Default Admin")
                            .email(adminEmail)
                            .phone("0000000000")
                            .password(passwordEncoder.encode("admin123"))
                            .role(Role.ADMIN)
                            .position(Position.FIRST)
                            .build();

                    userRepository.save(admin);
                }
            }
        };
    }
}
