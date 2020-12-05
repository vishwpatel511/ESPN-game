package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

    public class indexController extends Main implements Initializable {

            @FXML
            private ComboBox<String> playerCombobox = new ComboBox<String>();
             ObservableList<String> list = FXCollections.observableArrayList();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            playerCombobox.setItems(list);
        }

        // Event listener for combobox: playerid
        public void gettingValue(){


        }

        Connection connection = null;
        ResultSet resultSet = null;
        String query;
        String q;

        {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
                Statement statement = connection.createStatement();
                //Statement stmt = connection.createStatement();
                query = "SELECT  * FROM player";
               // int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());
               // q = "Select * from player WHERE player_id = value";
                resultSet = statement.executeQuery(query);
                // ResultSet rs = stmt.executeQuery(q);

                while (resultSet.next()) {
                    list.add(resultSet.getString("player_id"));
                    playerCombobox.getItems().addAll(list);
                }
            }
            catch(SQLException exception)

            {
                exception.printStackTrace();
            }
        }


        // Events of buttons...

        public void ProfileClickAction(ActionEvent actionEvent) throws IOException {
            CreateNewProfile createNewProfile = new CreateNewProfile();
            createNewProfile.testingMethod();
        }

        public void DisplaydataAction(ActionEvent actionEvent) throws IOException {
        //    ShowData showData = new ShowData();
         //   showData.DisplayData();



            try {

             //
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
                int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());
                q = "Select * from player WHERE player_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(q);
                preparedStatement.setInt(1 , value);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){

                    System.out.println("Player Id: " +rs.getInt("player_id") + "-" +
                           "First Name: " + rs.getString("first_name") + "-" +
                         "Last Name: "   + rs.getString("last_name") + "-" +
                           "Address: " + rs.getString("address") +  " - " +
                           "Postal code: " + rs.getString("postal_code") + "-" +
                          "Province: "   + rs.getString("province") +"-" +
                          "phone Number:"  +rs.getString("phone_number"));

                    Parent displayplayers = FXMLLoader.load(getClass().getResource("ShowData.fxml"));
                    Stage displayStage = new Stage();
                    displayStage.setTitle("Show player Data");
                    VBox layout = new VBox();
                    displayStage.setScene(new Scene(layout, 300,300));
                    Label idLabel = new Label("Player Id: " +rs.getInt("player_id"));
                    Label fnameLabel = new Label("First Name: " + rs.getString("first_name"));
                    Label lnameLabel = new Label("Last Name: " + rs.getString("last_name"));
                    Label addressLabel = new Label("Address: " + rs.getString("address"));
                    Label postalcodeLabel = new Label("Postal code: " + rs.getString("postal_code"));
                    Label provinceLabel = new Label("Province: " + rs.getString("province"));
                    Label contactNumberLabel = new Label("Contact Number: " + rs.getString("phone_number"));
                    layout.getChildren().addAll(idLabel, fnameLabel,lnameLabel,addressLabel,postalcodeLabel,provinceLabel,contactNumberLabel);
                    displayStage.showAndWait();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }


        }

        public void UpdateButtonAction(ActionEvent actionEvent) throws IOException {
            EditData edit = new EditData();
            edit.Editfields();
        }


    }