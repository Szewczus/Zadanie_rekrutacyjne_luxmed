package ewa.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private TableView tableView;

    @FXML
    private TableView tableView1;

    @FXML
    private TextArea idDogOwnerTextArea;

    @FXML
    private TextArea nameDogOwnerTextArea;

    @FXML
    private TextArea surnameDogOwnerTextArea;

    @FXML
    private TextArea emailDogOwnerTextArea;

    @FXML
    private TextArea idDogTextArea;

    @FXML
    private TextArea nameDogTextArea;

    @FXML
    private TextArea ageDogTextArea;

    @FXML
    private TextArea dogownerDogTextArea;

    @FXML
    private Button dogOwnerDelete;

    @FXML
    private Button dogDelete;

    @FXML
    private Button dogSave;

    @FXML
    private Button dogOwnerSave;

    @FXML
    private Button dogOwnerEdit;

    @FXML
    private Button dogEdit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Person> personList = ServerConnect.connectToDogOwner();
        //System.out.println(personList);
        TableColumn id = new TableColumn("id");
        TableColumn name = new TableColumn("name");
        TableColumn surname = new TableColumn("surname");
        TableColumn email = new TableColumn("email");
        tableView.getColumns().add(id);
        tableView.getColumns().add(name);
        tableView.getColumns().add(surname);
        tableView.getColumns().add(email);
        final ObservableList<Person> data = FXCollections.observableArrayList(personList);
        id.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        tableView.setItems(data);


        //nazwanie kolumn
        TableColumn idDog = new TableColumn("id");
        TableColumn nameDog = new TableColumn("name");
        TableColumn ageDog = new TableColumn("age");
        TableColumn idDogDogOwner = new TableColumn("dog_owner_dog");
        //dodanie kolumn do tabelki
        tableView1.getColumns().add(idDog);
        tableView1.getColumns().add(nameDog);
        tableView1.getColumns().add(ageDog);
        tableView1.getColumns().add(idDogDogOwner);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Person person = ((Person) observable.getValue());
                    System.out.println("Observable: " + ((Person)observable.getValue()).getName() + " " +((Person)observable.getValue()).getSurname());
                    idDogOwnerTextArea.setText(person.getId().toString());
                    nameDogOwnerTextArea.setText(person.getName());
                    surnameDogOwnerTextArea.setText(person.getSurname());
                    emailDogOwnerTextArea.setText(person.getEmail());
                    System.out.println("halo?? ");
                    List<Dog> listDog = ServerConnect.getDogownerDogs(person);
                    System.out.println("halo?? "+listDog.get(0).getName() + listDog.get(0).getAge() + listDog.get(0).getDog_owner_dog());
                    final ObservableList<Dog> data2 = FXCollections.observableArrayList(listDog);


                    idDog.setCellValueFactory(new PropertyValueFactory<Dog, Long>("id"));
                    nameDog.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
                    ageDog.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
                    idDogDogOwner.setCellValueFactory(new PropertyValueFactory<Dog, String>("dog_owner_dog"));
                    tableView1.setItems(data2);

                } catch (Exception e) {
                    System.out.println("Exception: "+e.getLocalizedMessage());
                    List<Dog> listDog = new ArrayList<>();
                    final ObservableList<Dog> data2 = FXCollections.observableArrayList(listDog);


                    idDog.setCellValueFactory(new PropertyValueFactory<Dog, Long>("id"));
                    nameDog.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
                    ageDog.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
                    idDogDogOwner.setCellValueFactory(new PropertyValueFactory<Dog, String>("dog_owner_dog"));
                    tableView1.setItems(data2);
                }
            }
        });


/*        //nazwanie kolumn
        TableColumn idDog = new TableColumn("id");
        TableColumn nameDog = new TableColumn("name");
        TableColumn ageDog = new TableColumn("age");
        TableColumn idDogDogOwner = new TableColumn("dog_owner_dog");
        //dodanie kolumn do tabelki
        tableView1.getColumns().add(idDog);
        tableView1.getColumns().add(nameDog);
        tableView1.getColumns().add(ageDog);
        tableView1.getColumns().add(idDogDogOwner);*/

   /*     //wstawienie do kolumn odpowiednich wartości i wyjecie po odpowienich nazwach z jsona
        idDog.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        nameDog.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        ageDog.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
        idDogDogOwner.setCellValueFactory(new PropertyValueFactory<Person, Long>("dog_owner_dog"));

        //przeparsowanie na FXCollections
        List<Dog> listDog = ServerConnect.connectToDog();
        ObservableList<Dog> data1 = FXCollections.observableArrayList(listDog);
        tableView1.setItems(data1);
*/

        //listener do 2 tabeli i wpisywanie w odpoiednie miejsca pul z tabeli w pola pod spodem

        tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Dog dog = ((Dog) observable.getValue());
                    idDogTextArea.setText(dog.getId().toString());
                    nameDogTextArea.setText(dog.getName());
                    ageDogTextArea.setText(dog.getAge().toString());
                    dogownerDogTextArea.setText(dog.getDog_owner_dog().toString());
                }
                catch (Exception e){

                }
            }
        });

        dogSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dog doge = new Dog();
                if (ageDogTextArea.getText() == "" || dogownerDogTextArea.getText() == "" || nameDogTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    Integer age = Integer.parseInt(ageDogTextArea.getText());
                    Long dog_owner_dog = Long.parseLong(dogownerDogTextArea.getText());
                    System.out.println("Long dogowner: " + dog_owner_dog);
                    doge.setId(1L);
                    doge.setName(nameDogTextArea.getText());
                    doge.setAge(age);
                    doge.setDog_owner_dog(dog_owner_dog);
                    System.out.println("id" + doge.getId() + "Age: " + doge.getAge() + " " + doge.getName() + " " + doge.getDog_owner_dog());
                    ServerConnect.postDog(doge);
                    List<Dog> listDog = ServerConnect.connectToDog();
                    ObservableList<Dog> data1 = FXCollections.observableArrayList(listDog);
                    tableView1.setItems(data1);
                }
            }
        });

        dogOwnerSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person person = new Person();
                if (nameDogOwnerTextArea.getText() == "" || surnameDogOwnerTextArea.getText() == "" || emailDogOwnerTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    person.setId(1L);
                    person.setName(nameDogOwnerTextArea.getText());
                    person.setSurname(surnameDogOwnerTextArea.getText());
                    person.setEmail(emailDogOwnerTextArea.getText());
                    ServerConnect.postPerson(person);
                    List<Person> listDogOwner = ServerConnect.connectToDogOwner();
                    ObservableList<Person> data1 = FXCollections.observableArrayList(listDogOwner);
                    tableView.setItems(data1);
                }
            }
        });

        dogOwnerDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person person = new Person();
                if (nameDogOwnerTextArea.getText() == "" || surnameDogOwnerTextArea.getText() == "" || emailDogOwnerTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    person.setId(1L);
                    person.setName(nameDogOwnerTextArea.getText());
                    person.setSurname(surnameDogOwnerTextArea.getText());
                    person.setEmail(emailDogOwnerTextArea.getText());
                    ServerConnect.deletePerson(person);
                    List<Person> listDogOwner = ServerConnect.connectToDogOwner();
                    ObservableList<Person> data1 = FXCollections.observableArrayList(listDogOwner);
                    tableView.setItems(data1);
                }
            }
        });


        dogDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dog doge = new Dog();
                if (idDog.getText() == "" || ageDogTextArea.getText() == "" || dogownerDogTextArea.getText() == "" || nameDogTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    Integer age = Integer.parseInt(ageDogTextArea.getText());
                    System.out.println("id: "+ idDogTextArea.getText());
                    Long id = Long.parseLong(idDogTextArea.getText());
                    Long dog_owner_dog = Long.parseLong(dogownerDogTextArea.getText());
                    System.out.println("Long dogowner: " + dog_owner_dog);
                    doge.setId(id);
                    doge.setName(nameDogTextArea.getText());
                    doge.setAge(age);
                    doge.setDog_owner_dog(dog_owner_dog);
                    System.out.println("id" + doge.getId() + "Age: " + doge.getAge() + " " + doge.getName() + " " + doge.getDog_owner_dog());
                    ServerConnect.deleteDog(doge);
                    List<Dog> listDog = ServerConnect.connectToDog();
                    ObservableList<Dog> data1 = FXCollections.observableArrayList(listDog);
                    tableView1.setItems(data1);
                }
            }
        });

        dogOwnerEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person person = new Person();
                if (nameDogOwnerTextArea.getText() == "" || surnameDogOwnerTextArea.getText() == "" || emailDogOwnerTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    Long id = Long.parseLong(idDogOwnerTextArea.getText());
                    person.setId(id);
                    person.setName(nameDogOwnerTextArea.getText());
                    person.setSurname(surnameDogOwnerTextArea.getText());
                    person.setEmail(emailDogOwnerTextArea.getText());
                    ServerConnect.editPerson(person);
                    List<Person> listDogOwner = ServerConnect.connectToDogOwner();
                    ObservableList<Person> data1 = FXCollections.observableArrayList(listDogOwner);
                    tableView.setItems(data1);
                }
            }
        });

        dogEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dog doge = new Dog();
                if (idDog.getText() == "" || ageDogTextArea.getText() == "" || dogownerDogTextArea.getText() == "" || nameDogTextArea.getText() == "") {
                    System.out.println("Uzupełnij wszystkie dane!!!");
                } else {
                    Integer age = Integer.parseInt(ageDogTextArea.getText());
                    //System.out.println("id: "+ idDogTextArea.getText());
                    Long id = Long.parseLong(idDogTextArea.getText());
                    Long dog_owner_dog = Long.parseLong(dogownerDogTextArea.getText());
                    //System.out.println("Long dogowner: " + dog_owner_dog);
                    doge.setId(id);
                    doge.setName(nameDogTextArea.getText());
                    doge.setAge(age);
                    doge.setDog_owner_dog(dog_owner_dog);
                    //System.out.println("id" + doge.getId() + "Age: " + doge.getAge() + " " + doge.getName() + " " + doge.getDog_owner_dog());
                    ServerConnect.editDog(doge);
                    List<Dog> listDog = ServerConnect.connectToDog();
                    ObservableList<Dog> data1 = FXCollections.observableArrayList(listDog);
                    tableView1.setItems(data1);
                }
            }
        });

        /*dogEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });*/



/*        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Dog doge = new Dog();
                System.out.println("kliknieto");
                Integer age =Integer.parseInt(ageDogTextArea.getText());
                Long dog_owner_dog =Long.parseLong(dogownerDogTextArea.getText());
                doge.setName(nameDogTextArea.getText());
                doge.setAge(age);
                doge.setDog_owner_dog(dog_owner_dog);
                System.out.println(doge.getAge());
                ServerConnect.postDog(doge);
            }
        };
        dogOwnerDelete.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);*/
        /*ServerConnect.postDog();*/

    }
}
