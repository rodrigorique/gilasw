package com.riques.notification.server;

import com.riques.notification.server.model.User;
import com.riques.notification.server.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("Ana Caroline Rique", "carol@riques.com", "21999888777", Arrays.asList("Sports", "Movies"), Arrays.asList("SMS", "E-Mail")));
            userRepository.save(new User("Arthur Rique", "athur@riques.com", "21999888666", Arrays.asList("Finance", "Sports"), Arrays.asList("E-Mail", "Push Notification")));
            userRepository.save(new User("Rodrigo Rique", "rodrigo@riques.com", "21999888555", Arrays.asList("Finance","Movies"), Arrays.asList("E-Mail", "SMS")));
        };
    }
}
