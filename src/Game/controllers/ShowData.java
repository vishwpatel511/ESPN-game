package Game.controllers;

import Game.DBconfigration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Game.beans.Player;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ResourceBundle;

public class ShowData extends Main implements Initializable {

    indexController control = new indexController();
    private static String q = "Select * from player where player_id = ?";
  //  int value = Integer.parseInt(control.playerCombobox.getSelectionModel().getSelectedItem());

    private Stage displayStage;
    @FXML
    private TextField playerIDtxt;
    @FXML
    private TextField fnametxt;
    @FXML
    private TextField lnametxt;
    @FXML
    private TextField addresstxt;
    @FXML
    private TextField postalcodetxt;
    @FXML
    private TextField provincetxt;
    @FXML
    private TextField contacttxt;
    @FXML
    Player player = new Player();

    {

        try(Connection connection = DBconfigration.getconnection();
            PreparedStatement stmt = connection.prepareStatement(q);) {
            stmt.setInt(1, Integer.parseInt(control.value));
            ResultSet set = stmt.executeQuery();
            while(set.next()){

            player.set_playerId(set.getInt("player_id"));
            player.set_firstname(set.getString("first_name"));
            player.set_lastname(set.getString("last_name"));
            player.set_address(set.getString("address"));
            player.set_postalcode(set.getString("postal_code"));
            player.set_province(set.getString("province"));
            player.set_phoneNumber(set.getString("phone_number"));

            playerIDtxt.setText(String.valueOf(player.get_playerId()));
            fnametxt.setText(player.get_firstname());
            lnametxt.setText(player.get_lastname());
            addresstxt.setText(player.get_address());
            postalcodetxt.setText(player.get_postalcode());
            provincetxt.setText(player.get_province());
            contacttxt.setText(player.get_phoneNumber());

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void DisplayData() throws IOException{
        Parent displayplayers = FXMLLoader.load(getClass().getResource("../FXMLs/ShowData.fxml"));
        this.displayStage = new Stage();
        displayStage.setTitle("Show player Data");
        displayStage.setScene(new Scene(displayplayers));
        displayStage.showAndWait();
    }


    /* try(Connection connection = DBconfigration.getconnection();
    PreparedStatement preparedStatement = connection.prepareStatement(q);
                ) {

        //
        int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());

        preparedStatement.setInt(1, value);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {

            // For Testing purpose
            System.out.println("Player Id: " + rs.getInt("player_id") + "-" +
                    "First Name: " + rs.getString("first_name") + "-" +
                    "Last Name: " + rs.getString("last_name") + "-" +
                    "Address: " + rs.getString("address") + " - " +
                    "Postal code: " + rs.getString("postal_code") + "-" +
                    "Province: " + rs.getString("province") + "-" +
                    "phone Number:" + rs.getString("phone_number"));

            //       Parent displayplayers = FXMLLoader.load(getClass().getResource("ShowData.fxml"));

            // New Stage is created and designed with some nodes in VBox container
            Stage displayStage = new Stage();
            displayStage.setTitle("Show player Data");
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20));
            displayStage.setScene(new Scene(layout, 300, 300));
            Label idLabel = new Label("Player Id: " + rs.getInt("player_id"));
            Label fnameLabel = new Label("First Name: " + rs.getString("first_name"));
            Label lnameLabel = new Label("Last Name: " + rs.getString("last_name"));
            Label addressLabel = new Label("Address: " + rs.getString("address"));
            Label postalcodeLabel = new Label("Postal code: " + rs.getString("postal_code"));
            Label provinceLabel = new Label("Province: " + rs.getString("province"));
            Label contactNumberLabel = new Label("Contact Number: " + rs.getString("phone_number"));
            layout.getChildren().addAll(idLabel, fnameLabel, lnameLabel, addressLabel, postalcodeLabel, provinceLabel, contactNumberLabel);
            displayStage.showAndWait();
        }
    } catch (SQLException e) {
        System.out.println("Error loading data!");
        e.printStackTrace();
    }






*/

}