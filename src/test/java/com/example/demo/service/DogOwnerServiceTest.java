package com.example.demo.service;

import com.example.demo.dto.DogOwnerDto;
import com.example.demo.entity.DogOwner;
import com.example.demo.repo.DogOwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
class DogOwnerServiceTest {

    @Autowired
    private DogOwnerService dogOwnerService;

    @Autowired
    private DogOwnerRepository dogOwnerRepository;

    /*@Before
    public void setUp(){
        Integer i = 1;
        DogOwner dogOwner = new DogOwner("Ewa", "Szewczak","ewus9999@gmail.com");
        Mockito.when(dogOwnerRepository.findDogOwnerByEmail(dogOwner.getEmail())).thenReturn(dogOwner);
    }*/

    @Test
    public void testSaveDogOwner(){
        /*Mockito.when(dogOwnerService.findAll()).thenReturn("{\"id\":1,\"name\":\"Ewa\",\"surname\":\"Szewczak\",\"email\":\"ewus9999@gmail.com\"}");
        String tekst = dogOwnerService.findAll().toString();
        Assert.assertEquals("Dogowners", tekst);*/

        DogOwner dogOwner =new DogOwner("Anna", "Kowal", "anna@gmail.com");
        DogOwnerDto dogOwnerDto =new DogOwnerDto(null, "Anna", "Kowal", "anna@gmail.com");
        assertEquals(  dogOwnerService.saveDogOwner(dogOwnerDto).getSurname() , dogOwner.getSurname());
        assertEquals(  dogOwnerService.saveDogOwner(dogOwnerDto).getName() , dogOwner.getName());
        assertEquals(  dogOwnerService.saveDogOwner(dogOwnerDto).getEmail() , dogOwner.getEmail());

    }
    @Test
    public void testEditDogOwner(){
        Integer i = 1;
        DogOwnerDto dogOwnerDto =new DogOwnerDto(Long.valueOf(i.longValue()), "Ewus", "Szewczus", "ewus9999@gmail.com");
        DogOwner dogOwner = dogOwnerService.editDogOwner(dogOwnerDto);
        assertEquals(dogOwner.getName(), dogOwnerDto.getName());
        assertEquals(dogOwner.getSurname(), dogOwnerDto.getSurname());
        assertEquals(dogOwner.getEmail(), dogOwnerDto.getEmail());
    }

    @Test
    public void testFindAll(){
        DogOwner dogOwner =new DogOwner("Ewa", "Szewczak", "ewus9999@gmail.com");
          List<DogOwner> list= dogOwnerService.findAll();
          assertEquals( list.size(), 1);
    }

}
