import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectController {

    private Scene scene;
    private String fxmlName;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;


    public void setSecondScene(String fxmlName) {
        this.fxmlName = fxmlName;
    }


    public void loadScene() {
        try {
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(this.fxmlName));
            Parent secondPane = secondPageLoader.load();
            this.scene = new Scene(secondPane, 800, 600);
        }
        catch (Exception e) {

        }
    }

    @FXML
    private void goToConnect(ActionEvent event) {
        if (DbManager.getInstance().setCredentials( userField.getText(), passwordField.getText())) {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.loadScene();
            primaryStage.setScene(this.scene);
        }
    }
}

