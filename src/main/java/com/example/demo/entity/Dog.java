package com.example.demo.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String name;

    @NotNull()
    private Integer age;

    @ManyToOne
    private DogOwner dog_owner_dog;

}
