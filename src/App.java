import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class App extends Application 
{

    @Override
    public void start(Stage stage) 
    {
        try 
        {
            // loading up the fxml file 
            Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
            
            
            // scene configuration
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());
            String css = this.getClass().getResource("App.css").toExternalForm();
            scene.getStylesheets().add(css);

            
            // stage configuration
            stage.setScene(scene);
            stage.setTitle("Groovy");


            

            // display the stage
            stage.show();
        } 
        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
