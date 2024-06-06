import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GroovyDatabaseManager 
{
    private static final String URL = "jdbc:mysql://localhost:3306/Groovy";
    private static final String USER = "root";
    private static final String PASSWORD = "capybaralord";

    private Connection connection;

    public GroovyDatabaseManager() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void addSong(String name, String artist, String genre, String timeDuration, String releaseDate, String lyrics, int plays, String filePath) throws SQLException {
        String sql = "INSERT INTO Song (name, artist, genre, timeDuration, releaseDate, lyrics, plays, filePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, name);
            statement.setString(2, artist);
            statement.setString(3, genre);
            statement.setString(4, timeDuration);
            statement.setString(5, releaseDate);
            statement.setString(6, lyrics);
            statement.setInt(7, plays);
            statement.setString(8, filePath);
            statement.executeUpdate();
        }
    }
    
    public void addFeedback(feedback_form FormItem)
    		throws SQLException {
        String sql = "INSERT INTO Feedback (type, Title, comment, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, FormItem.type);
            statement.setString(2, FormItem.Title);
            statement.setString(3, FormItem.comment);
            statement.setInt(4, FormItem.rating);
            statement.executeUpdate();
        }
    }
    
    
    public void addFeedback(String type,String Title,String comment,int rating)
    		throws SQLException {
        String sql = "INSERT INTO Feedback (type, Title, comment, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, type);
            statement.setString(2, Title);
            statement.setString(3, comment);
            statement.setInt(4, rating);
            statement.executeUpdate();
        }
    }
    public Song getSong(int id) throws SQLException 
    {
        String sql = "SELECT * FROM Song WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                if (resultSet.next()) 
                {

                    return new Song(
                            resultSet.getString("name"),
                            resultSet.getString("artist"),
                            resultSet.getString("genre"),
                            resultSet.getString("timeDuration"),
                            resultSet.getString("releaseDate"),
                            resultSet.getString("lyrics"),
                            resultSet.getInt("plays"),
                            resultSet.getString("filePath")
                    );
                }
            }
        }
        return null;
    }

    public ResultSet getSongs() throws SQLException 
    {
        String sql = "SELECT * FROM Song";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    public List<String> getSongNames() throws SQLException {
        List<String> songNames = new ArrayList<>();
        String sql = "SELECT name FROM Song";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            songNames.add(resultSet.getString("name"));
        }
        return songNames;
    }

    public Song getSongByName(String name) throws SQLException 
    {
        String sql = "SELECT * FROM Song WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    return new Song(
                            resultSet.getString("name"),
                            resultSet.getString("artist"),
                            resultSet.getString("genre"),
                            resultSet.getString("timeDuration"),
                            resultSet.getString("releaseDate"),
                            resultSet.getString("lyrics"),
                            resultSet.getInt("plays"),
                            resultSet.getString("filePath")
                    );
                }
            }
        }
        return null;
    }


    public void addUser(String name, String email, String password) 
    {
        String sql = "INSERT INTO User (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
    
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setHeaderText(null);
            alert.setContentText("Sign Up successful! User registered!");
            alert.showAndWait();
        } catch (SQLException e) 
        {
            if (e.getErrorCode() == 1062) 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User already exists with this email!");
                alert.showAndWait();
            } 
            else 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while adding the user!");
                alert.showAndWait();
            }
        }
    }
    

    public boolean verifyUser(String email, String password) 
    {
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                boolean userExists = resultSet.next();
                if (userExists) 
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Status");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successful!");
                    alert.showAndWait();
                }
                return userExists;
            }
        } 
        catch (SQLException e) 
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while verifying user credentials!");
            alert.showAndWait();
            return false;
        }
    }
    
    
}
