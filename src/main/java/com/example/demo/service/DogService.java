package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogOwnerRepository;
import com.example.demo.repo.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogOwnerRepository dogOwnerRepository;

    public Dog saveDog(DogDto dogDto) {
        Dog dog = new Dog();
        dog.setName(dogDto.getName());
        dog.setAge(dogDto.getAge());
        return dogRepository.save(dog);

    }

    public List<Dog> findAll(){
        return dogRepository.findAll();
    }

}
