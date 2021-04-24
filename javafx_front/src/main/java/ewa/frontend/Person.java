package ewa.frontend;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    //private final SimpleStringProperty name = new SimpleStringProperty("");
    //private final SimpleStringProperty surname = new SimpleStringProperty("");
    //private final SimpleStringProperty email = new SimpleStringProperty("");

    String name;
    String surname;
    String email;


    public Person() {
        this("", "", "");
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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
