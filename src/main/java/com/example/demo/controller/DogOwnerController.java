package com.example.demo.controller;

import com.example.demo.dto.DogOwnerDto;
import com.example.demo.entity.DogOwner;
import com.example.demo.service.DogOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogOwnerController {
    @Autowired
    private DogOwnerService dogOwnerService;

    @PostMapping("dogowner")
    ResponseEntity<DogOwner> saveDogOwner(@RequestBody DogOwnerDto dogOwnerDto){
        DogOwner dogOwner = dogOwnerService.saveDogOwner(dogOwnerDto);
        return ResponseEntity.ok(dogOwner);
    }

    @GetMapping("dogowners")
    ResponseEntity<List<DogOwner>> findAllDogOwners(){
        return ResponseEntity.ok(dogOwnerService.findAll());
    }

    @DeleteMapping("delete/dogowner")
    ResponseEntity deleteDogOwner(@RequestBody DogOwnerDto dogOwnerDto){
        dogOwnerService.deleteDogOwner(dogOwnerDto);
        return ResponseEntity.ok("Usunieto");
    }



}
