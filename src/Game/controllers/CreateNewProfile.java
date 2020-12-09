package Game.controllers;

import Game.DBconfigration;
import Game.Main;
import Game.beans.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateNewProfile extends Main implements Initializable {

    private Stage profileStage;
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
    private Button submitBtn;


    private static final String addQuery = "INSERT INTO `player`(`player_id`, `first_name`, `last_name`, `address`, `postal_code`, `province`, `phone_number`) VALUES (?,?,?,?,?,?,?)";


    public CreateNewProfile(){


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader addplayer = null;

      /*  try {
           addplayer = FXMLLoader.load(getClass().getResource("FXMLs/CreateNewProfile.fxml"));
            Parent root = addplayer.load();
            Stage profileStage = new Stage();
            profileStage.setTitle("Add new Player of your choice");
            profileStage.setScene(new Scene(root));
            profileStage.showAndWait();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
*/
    }
        public void SubmitClicked (ActionEvent actionEvent){

            try (Connection connection = DBconfigration.getconnection();
                 PreparedStatement preparedStatementforadd = connection.prepareStatement(addQuery);

            ) {
                Player player = new Player();
                player.set_firstname(fnametxt.getText());
                player.set_lastname(lnametxt.getText());
                player.set_address(addresstxt.getText());
                player.set_playerId(Integer.parseInt(playerIDtxt.getText()));
                player.set_phoneNumber(contacttxt.getText());
                player.set_postalcode(postalcodetxt.getText());
                player.set_province(provincetxt.getText());

                preparedStatementforadd.setString(1, String.valueOf(player.get_playerId()));
                preparedStatementforadd.setString(2, player.get_firstname());
                preparedStatementforadd.setString(3, player.get_lastname());
                preparedStatementforadd.setString(4, player.get_address());
                preparedStatementforadd.setString(5, player.get_postalcode());
                preparedStatementforadd.setString(6, player.get_province());
                preparedStatementforadd.setString(7, player.get_phoneNumber());
                preparedStatementforadd.executeUpdate();


                System.out.println("Player has been added successfully!");
            } catch (SQLException exception) {
                System.out.println("Error Adding data...!!!");
                exception.printStackTrace();

            }
        }


    }





/*

 */