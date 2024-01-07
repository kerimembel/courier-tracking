package com.migrosone.couriertracking.config;

import com.migrosone.couriertracking.service.contract.StoreService;
import com.migrosone.couriertracking.service.contract.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class to execute tasks during startup to create needed data.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
@AllArgsConstructor
public class StartupConfig {

    private final UserService userService;
    private final StoreService storeService;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            userService.createAdminUser();
            storeService.loadStoreLocationsFromJson();
        };
    }
}