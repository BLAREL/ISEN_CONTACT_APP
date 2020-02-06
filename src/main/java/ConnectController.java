import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnectController {


    @FXML
    private Button connect;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private void goToConnect(ActionEvent event) {
        System.out.println(DbManager.getInstance().setCredentials( userField.getText(), passwordField.getText()));
        System.out.println(DbManager.getInstance().getAllContact().get(0).getAddress());
    }

}
