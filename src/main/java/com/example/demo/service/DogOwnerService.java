package com.example.demo.service;

import com.example.demo.dto.DogOwnerDto;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogOwnerRepository;
import com.example.demo.repo.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DogOwnerService {
    @Autowired
    DogOwnerRepository dogOwnerRepository;

    @Autowired
    DogRepository dogRepository;

    public DogOwner saveDogOwner(DogOwnerDto dogOwnerDto){
        if(dogOwnerRepository.existsDogOwnerByEmail(dogOwnerDto.getEmail())){
            return null;
        }
        else {
            DogOwner dogOwner = new DogOwner();
            dogOwner.setName(dogOwnerDto.getName());
            dogOwner.setSurname(dogOwnerDto.getSurname());
            dogOwner.setEmail(dogOwnerDto.getEmail());
            return dogOwnerRepository.save(dogOwner);
        }

    }


    public List<DogOwner> findAll(){
        return dogOwnerRepository.findAll();
    }

    @Transactional
    public void deleteDogOwner(DogOwnerDto dogOwnerDto){
        dogOwnerRepository.deleteDogOwnerByEmail(dogOwnerDto.getEmail());
    }

    public DogOwner editDogOwner(DogOwnerDto dogOwnerDto){
        DogOwner dogOwner = dogOwnerRepository.findDogOwnerById(dogOwnerDto.getId());
        if(dogOwner!=null){
            dogOwner.setName(dogOwnerDto.getName());
            dogOwner.setSurname(dogOwnerDto.getSurname());
            dogOwner.setEmail(dogOwnerDto.getEmail());
            return dogOwnerRepository.save(dogOwner);
        }
        else{
            return null;
        }
    }
}
