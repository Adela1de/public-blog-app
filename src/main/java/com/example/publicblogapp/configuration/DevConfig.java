package com.example.publicblogapp.configuration;

import com.example.publicblogapp.testdatabase.DatabaseInitiation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DevConfig {

    private final DatabaseInitiation databaseInitiation;

    @Bean
    public void databaseInit() {  }
}
