import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class discussion_form_controller {

	private Stage stage;
    private Scene scene;
    private Parent root;
	discussion_form df;
    @FXML
    private TextField Title_text_field;

    @FXML
    private MenuButton Type_Seletc_menu_button;

    @FXML
    private TextArea commnet_text_area;

    @FXML
    private Button submit_button;

    GroovyDatabaseManager databaseManager;
    
    @FXML
    void submit_form(ActionEvent event) throws SQLException, IOException {
    	df.title=Title_text_field.getText();
    	df.type=Type_Seletc_menu_button.getText();
    	df.comment=commnet_text_area.getText().replaceAll("\n", System.getProperty("line.separator"));
    	databaseManager.addDiscussion_form(df);
    	
    	Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("App.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    	
    }

}
