package com.example.demo;

import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogOwnerRepository;
import com.example.demo.repo.DogRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DogOwnerRepository dogOwnerRepository, DogRepository dogRepository){
        DogOwner dogOwner =new DogOwner("Ewa", "Szewczak", "ewus9999@gmail.com");
        Dog dog1 = new Dog("Fifek", 4, dogOwner);
        /*Dog dog2 = new Dog("Felek", 2, dogOwner);*/
        //Dog dog = Dog.builder().name("Fifek").age(4).dog_owner_dog(dogOwner).build();
        return args -> {
            log.info("Preloading" + dogOwnerRepository.save(dogOwner));
            log.info("Preload" + dogRepository.save(dog1));
            /*log.info("Preload" + dogRepository.save(dog2));*/
        };
    }
}
