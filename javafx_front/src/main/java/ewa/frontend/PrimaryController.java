package ewa.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    private TextArea id_;

    @FXML
    private TextArea name_;

    @FXML
    private TextArea surname_;

    @FXML
    private TextArea email_;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Person> personList = ServerConnect.connectToDogOwner();
        System.out.println(personList);
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


        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Person person = ((Person)observable.getValue());
                System.out.println("Observable: " + ((Person)observable.getValue()).getName() + " " +((Person)observable.getValue()).getSurname());
                id_.setText(person.getId().toString());
                name_.setText(person.getName());
                surname_.setText(person.getSurname());
                email_.setText(person.getEmail());
            }
        });

        List<Dog> listDog = ServerConnect.connectToDog();
        TableColumn idDog = new TableColumn("id");
        TableColumn nameDog = new TableColumn("name");
        TableColumn ageDog = new TableColumn("age");
        TableColumn idDogDogOwner = new TableColumn("dog_owner_dog");
        tableView1.getColumns().add(idDog);
        tableView1.getColumns().add(nameDog);
        tableView1.getColumns().add(ageDog);
        tableView1.getColumns().add(idDogDogOwner);
        final ObservableList<Dog> data1 = FXCollections.observableArrayList(listDog);
        idDog.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        nameDog.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        ageDog.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
        idDogDogOwner.setCellValueFactory(new PropertyValueFactory<Person, Long>("dog_owner_dog"));
        tableView1.setItems(data1);



        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                /*String string[]=e.toString().split("\"");
                System.out.println(string[0]);*/
                System.out.println(e.toString());
            }
        };
        tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


    }
}
