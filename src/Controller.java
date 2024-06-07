import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;

public class Controller {

    // used to change scenes | Do not make any changes here
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label groovyLabel;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private Button Discussion_form_button;

    @FXML
    private Button Feedback_button;

    @FXML
    private Button Songs_button;

    
    @FXML
    private Pane menuPane;

    @FXML
    private Button newsButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button playButton;

    @FXML
    private Button prevButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button settingsButton;

    @FXML
    private Button signupButton;

    @FXML
    private Label songsLabel;

    @FXML
    private ListView<?> songsList;

    @FXML
    private Slider volume;

    @FXML
    private TextField emailField;


    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button SignupButton;

    @FXML
    private TextField loginEmailField;

    @FXML
    private TextField loginPasswordField;


    @FXML 
    private Button back; 


    


    // DatabaseManager Field 
    GroovyDatabaseManager databaseManager;

    @FXML
    public void initialize() {
        
        try
        {
            databaseManager = new GroovyDatabaseManager();
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    
    }

    
    
    @FXML
    void Login(ActionEvent event) 
    {
        try 
        {
            databaseManager.verifyUser(loginEmailField.getText(), loginPasswordField.getText());
            switchToHome(event); // Switch to home scene after successful login
        } catch(Exception exception) 
        {
            System.out.println(exception);
        }
    }

    @FXML
    void SignUp(ActionEvent event) 
    {
        try
        {
            databaseManager.addUser(nameField.getText(), emailField.getText(), passwordField.getText());
            switchToHome(event); 
            
        } catch(Exception exception) 
        {
            System.out.println(exception);
        }
    }
    

    // Scene switch methods
    public void switchToHome(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("App.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("SignUp.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("Login.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("News.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToNews(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("News.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("News.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void Naviagate_to_feedback(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Feedback_form.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Navigate_to_discussion_form(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("discussion_form_create.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void navigate_To_Songs(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Song_Upload.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}
