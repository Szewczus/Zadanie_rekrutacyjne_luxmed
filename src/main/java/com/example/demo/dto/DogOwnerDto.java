package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogOwnerDto {
    Long id;
    String name;
    String surname;
    String email;
    Long dog_owner_dog;
}
