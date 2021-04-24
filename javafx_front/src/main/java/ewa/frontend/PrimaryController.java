package ewa.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private TableView tableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Person> personList = ServerConnect.connect();
        System.out.println(personList);
        TableColumn name = new TableColumn("name");
        TableColumn surname = new TableColumn("surname");
        TableColumn email = new TableColumn("email");
        tableView.getColumns().add(name);
        tableView.getColumns().add(surname);
        tableView.getColumns().add(email);
        final ObservableList<Person> data = FXCollections.observableArrayList(personList);
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        tableView.setItems(data);

    }
}
