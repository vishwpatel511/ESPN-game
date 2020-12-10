package Game.controllers;

import Game.DBconfigration;
import Game.beans.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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