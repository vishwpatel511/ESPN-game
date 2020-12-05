package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowData extends Main implements Initializable {


    @FXML
    public TableColumn Displayname = new TableColumn();
    @FXML
    public TableColumn DisplayLastname = new TableColumn();
    @FXML
    public TableColumn DisplayAddress= new TableColumn();
    @FXML
    public TableColumn DisplayPostalCode = new TableColumn();
    @FXML
    public TableColumn DisplayProvince = new TableColumn();
    @FXML
    public TableColumn DisplayContact = new TableColumn();

    @FXML
    TableView Displaytableview = new TableView();

    indexController object = new indexController();

    private Stage displayStage;

    Connection connection = null;
    Statement statement = null;
    {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
            statement = connection.createStatement();
            String query = "SELECT * from player";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

              //  System.out.println(resultSet.getString("first_name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void DisplayData() throws IOException{
        Parent displayplayers = FXMLLoader.load(getClass().getResource("ShowData.fxml"));
        this.displayStage = new Stage();
        displayStage.setTitle("Show player Data");
        displayStage.setScene(new Scene(displayplayers));
        displayStage.showAndWait();

    }



}