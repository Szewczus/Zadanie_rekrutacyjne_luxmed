package ewa.frontend;

import javafx.beans.property.SimpleStringProperty;

public class Dog {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty age = new SimpleStringProperty();


    public Dog() {
        this("", "");
    }


    public Dog(String name, String age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String fAge) {
        age.set(fAge);
    }

    public static Dog stringParsing(String json){
        return new Dog();
    }
}
