package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogOwnerRepository;
import com.example.demo.repo.DogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogOwnerRepository dogOwnerRepository;

    public Dog saveDog(DogDto dogDto) {
        Dog dog = new Dog();
        dog.setName(dogDto.getName());
        dog.setAge(dogDto.getAge());

        if(dogOwnerRepository.existsDogOwnerById(dogDto.getDog_owner_dog())){
            log.info("boolean: "+dogOwnerRepository.existsDogOwnerById(dogDto.getDog_owner_dog())+" id " + dogDto.getDog_owner_dog());
            DogOwner dogOwner =dogOwnerRepository.findDogOwnerById(dogDto.getDog_owner_dog());
            dog.setDog_owner_dog(dogOwner);
        }

        return dogRepository.save(dog);
    }

    public List<Dog> findAll(){
        return dogRepository.findAll();
    }

    @Transactional
    public void deleteDog(DogDto dogDto){
        dogRepository.deleteDogById(dogDto.getId());
    }


    public Dog editDog(DogDto dogDto){
        Dog dog = dogRepository.findDogById(dogDto.getId());
        if(dog!=null){
            dog.setName(dogDto.getName());
            dog.setAge(dogDto.getAge());
            if(dogOwnerRepository.existsDogOwnerById(dogDto.getDog_owner_dog())){
                log.info("boolean: "+dogOwnerRepository.existsDogOwnerById(dogDto.getDog_owner_dog())+" id " + dogDto.getDog_owner_dog());
                DogOwner dogOwner =dogOwnerRepository.findDogOwnerById(dogDto.getDog_owner_dog());
                dog.setDog_owner_dog(dogOwner);
            }
            return dogRepository.save(dog);
        }
        else{
            return null;
        }
    }

}
