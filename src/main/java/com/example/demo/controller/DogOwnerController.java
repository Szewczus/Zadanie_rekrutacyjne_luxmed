package com.example.demo.controller;

import com.example.demo.dto.DogOwnerDto;
import com.example.demo.entity.DogOwner;
import com.example.demo.service.DogOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
@Slf4j
@RestController
public class DogOwnerController {
    @Autowired
    private DogOwnerService dogOwnerService;

    @PostMapping("dogowner")
    ResponseEntity saveDogOwner(@RequestBody DogOwnerDto dogOwnerDto){
        DogOwner dogOwner = dogOwnerService.saveDogOwner(dogOwnerDto);
        if(dogOwner==null){
            return ResponseEntity.ok("taki obiekt o takim emailu już istnieje");
        }
        return ResponseEntity.ok(dogOwner);
    }

    @GetMapping("dogowners")
    ResponseEntity<List<DogOwner>> findAllDogOwners(){
        return ResponseEntity.ok(dogOwnerService.findAll());
    }

    @DeleteMapping("delete/dogowner")
    ResponseEntity deleteDogOwner(@RequestBody DogOwnerDto dogOwnerDto){
        dogOwnerService.deleteDogOwner(dogOwnerDto);
        return ResponseEntity.ok("usunieto");
    }

    @PostMapping("edit/dogowner")
    ResponseEntity editDogOwner( @RequestBody DogOwnerDto dogOwnerDto){
        DogOwner dogOwner = dogOwnerService.editDogOwner(dogOwnerDto);
        if(dogOwner!=null){
            log.info("własciciel zapisał edytowany");
            return ResponseEntity.ok(dogOwner);
        }
        else
        {
            log.info("nie ma takiego właściciela psa o takim emailu");
            return ResponseEntity.ok("nie ma takiego właściciela psa o takim emailu");
        }
    }


}
