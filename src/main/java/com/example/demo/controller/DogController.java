package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogOwnerDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogRepository;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {
    @Autowired
    private DogService dogService;


    @PostMapping("dog")
    ResponseEntity saveDog(@RequestBody DogDto dogDto){
        Dog dog = dogService.saveDog(dogDto);
        return ResponseEntity.ok(dog);
    }

    @GetMapping("dogs")
    ResponseEntity<List<Dog>>findAllDogs(){
        return ResponseEntity.ok(dogService.findAll());
    }

    @DeleteMapping("delete/dog")
    ResponseEntity deleteDog(@RequestBody DogDto dogDto){
        dogService.deleteDog(dogDto);
        return ResponseEntity.ok("usunięto");
    }

    @PostMapping("edit/dog")
    ResponseEntity editDogById( @RequestBody DogDto dogDto){
        Dog dog = dogService.editDog(dogDto);
        if(dog != null){
            return ResponseEntity.ok(dog);
        }
        else {
            return ResponseEntity.ok("takiego psa o takim id nie ma w bazie");
        }
    }
}
