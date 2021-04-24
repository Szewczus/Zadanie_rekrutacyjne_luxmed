package ewa.frontend;


public class Dog {
    private String name;
    private Integer age;
    private Integer idDogDogOwer;


    public Dog(String name, Integer age, Integer idDogDogOwer) {
        this.name = name;
        this.age = age;
        this.idDogDogOwer = idDogDogOwer;
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

    public Integer getIdDogDogOwer() {
        return idDogDogOwer;
    }

    public void setIdDogDogOwer(Integer idDogDogOwer) {
        this.idDogDogOwer = idDogDogOwer;
    }
}
