package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    private Button profile;

   // private Scene firstScene;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 475, 475));
        primaryStage.show();
//Adding event Filter
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        ResultSet resultSet = null;
        launch(args);
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
            Statement statement = connection.createStatement();
            String query = "SELECT * From player ";
             resultSet = statement.executeQuery(query);
            while(resultSet.next()){
//                System.out.println("Name is: " + resultSet.getString("first_name"));

            }

        }
        catch (SQLException exception){
            exception.printStackTrace();
        }finally {

            if(connection.isClosed() == false){
                connection.close();
            }
            if(resultSet.isClosed() == false){
                resultSet.close();
            }
           // playerchoiceBox.getSelectionModel().getSelectedItem();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}