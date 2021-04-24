package ewa.frontend;


public class Dog {
    private String name;
    private Integer age;
    private Long dog_owner_dog;


    public Dog(String name, Integer age, Long idDogDogOwer) {
        this.name = name;
        this.age = age;
        this.dog_owner_dog = idDogDogOwer;
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
        return dog_owner_dog;
    }

    public void setDog_owner_dog(Long dog_owner_dog) {
        this.dog_owner_dog = dog_owner_dog;
    }
}
