package com.example.demo.entity;


import lombok.Builder;

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

    public DogOwner() {
    }

    public DogOwner(@NotNull() String name, @NotNull() String surname, @NotNull() String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public DogOwner(Long id, @NotNull() String name, @NotNull() String surname, @NotNull() String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
