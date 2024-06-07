import java.io.File;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Song_Upload_Controller {

	public Boolean Check_title()
	{
		if(Title_text_field.getText().isEmpty())
		{warning_text.setText("Please enter the Title");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}

	public Boolean Check_artist()
	{
		if(Artist_text_field.getText().isEmpty())
		{warning_text.setText("Please enter the artist name");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	public Boolean Check_lyrics()
	{
		if(Lyrics_text_area.getText().isEmpty())
		{warning_text.setText("Please enter the lyrics");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	public Boolean Check_genre()
	{
		if(Genre_text_field.getText().isEmpty())
		{warning_text.setText("Please enter the genre");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	public Boolean Check_Release_Date()
	{
		if(Release_Date_text_field.getText().isEmpty())
		{warning_text.setText("Please enter release Date");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	public Boolean Check_Time_duration()
	{
		if(Time_duration_text_field.getText().isEmpty())
		{warning_text.setText("Please enter time duration");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	public Boolean Check_Path_Of_File()
	{
		if(Time_duration_text_field.getText().isEmpty())
		{warning_text.setText("Please select File");
		warning_text.setVisible(true);
			return false;}
		warning_text.setText("");
		warning_text.setVisible(false);
		return true;
	}
	
	
	
	private Stage stage;
	
	 @FXML
	 private Text warning_text;
	
    @FXML
    private TextField Artist_text_field;

    @FXML
    private HBox Drag_Drop_Space;

    @FXML
    private TextField Genre_text_field;

    @FXML
    private TextArea Lyrics_text_area;

    @FXML
    private TextField Release_Date_text_field;

    @FXML
    private TextField Time_duration_text_field;
    
    @FXML
    private TextField Path_Of_File;

    @FXML
    private TextField Title_text_field;

    @FXML
    private Button Upload_button;
    
    @FXML
    private Button Browse_File;

    GroovyDatabaseManager databaseManager;
    
    @FXML
    void Dropped_File(DragEvent event) {
        Dragboard db = event.getDragboard();
        File file = db.getFiles().get(0);

        Path_Of_File.setText(file.getPath());
    }

    @FXML
    void Start_drag_drop(DragEvent event) {
    	Dragboard db = event.getDragboard();
        if (db.hasFiles())
        {
            event.acceptTransferModes(TransferMode.COPY);
        }
        else
        {
            event.consume();
        }
    }

    @FXML
    void Select_local_file(ActionEvent event) {
    	FileChooser fc = new FileChooser();

    	FileChooser.ExtensionFilter extesions = 
    	  new FileChooser.ExtensionFilter(".ogg", "*.mp3", "*.wav");

    	fc.getExtensionFilters().add(extesions);
    	fc.setTitle("Upload Song");
    	 stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	File file = fc.showOpenDialog(stage.getScene().getWindow());
    	Path_Of_File.setText(file.getPath());
    }
    
    @FXML
    void Upload_Song(ActionEvent event) throws SQLException {
    	if(Check_Time_duration()&&Check_title()&&Check_artist()&& Check_lyrics()&&Check_Path_Of_File()&&Check_lyrics()&&Check_genre()&&Check_Release_Date())
    	{databaseManager.addSong(Title_text_field.getText(), Artist_text_field.getText(), Genre_text_field.getText(), Time_duration_text_field.getText(),Release_Date_text_field.getText(),Lyrics_text_area.getText().replaceAll("\n", System.getProperty("line.separator")), 0, Path_Of_File.getText());
    	}
    
    }
}
