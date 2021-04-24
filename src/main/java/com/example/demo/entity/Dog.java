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

    public Dog() {
    }

    public Dog(@NotNull() String name, @NotNull() Integer age, DogOwner dog_owner_dog) {
        this.name = name;
        this.age = age;
        this.dog_owner_dog = dog_owner_dog;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDog_owner_dog() {
        return dog_owner_dog.getId();
    }

    public void setDog_owner_dog(DogOwner dog_owner_dog) {
        this.dog_owner_dog = dog_owner_dog;
    }
}
