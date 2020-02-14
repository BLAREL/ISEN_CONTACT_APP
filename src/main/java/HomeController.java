import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

public class HomeController {

    @FXML
    private TableView<Person> listPerson;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        personData.addAll(DbManager.getInstance().getAllContact());
        listPerson.getItems().addAll(personData);
    }
}
