import java.io.IOException;
import java.sql.SQLException;

import  javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class feedback_form_controller {

	
	   private Stage stage;
	    private Scene scene;
	    private Parent root;
public Boolean Check_title()
{
	if(Title_text_field.getText().isEmpty())
	{Warning_text.setText("Please enter the Title");
	Warning_text.setVisible(true);
		return false;}
	Warning_text.setText("");
	Warning_text.setVisible(false);
	return true;
}
public Boolean Check_Type()
{
	if(Type_Seletc_menu_button.getTypeSelector().isEmpty())
	{Warning_text.setText("Please enter the Title");
	Warning_text.setVisible(true);
		return false;}
	Warning_text.setText("");
	Warning_text.setVisible(false);
	return true;
}
public Boolean Check_comment()
{
	if(commnet_text_area.getText().isEmpty())
	{Warning_text.setText("Please enter the comment");
	Warning_text.setVisible(true);
		return false;}
	Warning_text.setText("");
	Warning_text.setVisible(false);
	return true;
}
public Boolean Check_rating()
{
	if(Rating.getSelectedToggle().isSelected())
	{Warning_text.setText("Please Select rating");
	Warning_text.setVisible(true);
		return false;}
	Warning_text.setText("");
	Warning_text.setVisible(false);
	return true;
}


    @FXML
    private ToggleGroup Rating;

    @FXML
    private TextField Title_text_field;

    @FXML
    private MenuButton Type_Seletc_menu_button;

    @FXML
    private TextArea commnet_text_area;

    @FXML
    private Text Warning_text;
    
    @FXML
    private Button submit_button;

    feedback_form FormItem;
    

    GroovyDatabaseManager databaseManager;
    
    @FXML
    void submit_feedback(ActionEvent event) throws SQLException, IOException {
    	if(Check_title()&&Check_rating()&&Check_comment()&&Check_Type())
    	{FormItem.comment=commnet_text_area.getText().replaceAll("\n", System.getProperty("line.separator"));
    	FormItem.type=Type_Seletc_menu_button.getText();
    	RadioButton Selectedradio = (RadioButton) Rating.getSelectedToggle();
    	FormItem.rating=Integer.valueOf(Selectedradio.getText());
    	FormItem.Title=Title_text_field.getText();
    	databaseManager.addFeedback(FormItem);
    	
    	Parent root = FXMLLoader.load(getClass().getResource("FeedBack_Conformation.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("FeedBack_Conformation.fxml").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    	}
    }

}

