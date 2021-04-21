package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class DogOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String name;

    @NotNull()
    private String surname;

    @NotNull()
    private String email;

    @OneToMany(mappedBy = "dog_owner_dog", cascade = CascadeType.ALL)
    private Set<Dog> dogSet;


}
