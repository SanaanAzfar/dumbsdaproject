import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class feedBack_conformation {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Feedback_ok_error;

    @FXML
    void Click_to_home(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("App.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}
