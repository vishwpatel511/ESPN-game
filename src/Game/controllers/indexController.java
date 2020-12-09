package Game.controllers;

import Game.DBconfigration;
import Game.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class indexController extends Main implements Initializable {


        private static String sqlQ = "Select * from player WHERE player_id = ?";
        private static String query = "SELECT  * FROM player";
        private static String addQuery = "INSERT INTO `player`(`player_id`, `first_name`, `last_name`, `address`, `postal_code`, `province`, `phone_number`) VALUES (?,?,?,?,?,?,?)";
        private static String q = "Select * from player where player_id = ?";

        @FXML
        private ComboBox<String> playerCombobox = new ComboBox<String>();
        ObservableList<String> list = FXCollections.observableArrayList();

        // intialize method from Initializable interface... Which intialize the stuff here it populates the combobox with lit of items...
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Parent index = null;
         /*   try {
                index = FXMLLoader.load(getClass().getResource("FXMLs/sample.fxml"));
                Stage profileStage = new Stage();
                profileStage.setTitle("Add new Player of your choice");
              //  profileStage.setScene(new Scene(root));
                profileStage.showAndWait();
                playerCombobox.setItems(list);

            } catch (IOException exception) {
                exception.printStackTrace();
            }


*/
        }

        // Event listener for combobox: playerid

        public void gettingValue() {


        }

        // Creating Database connection



        {

            try(Connection connection = DBconfigration.getconnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                  ) {

                while (resultSet.next()) {
                    list.add(resultSet.getString("player_id"));
                    playerCombobox.getItems().addAll(list);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }


        // Events of buttons...

        public void ProfileClickAction(ActionEvent actionEvent) throws IOException {
      /*      CreateNewProfile createNewProfile = new CreateNewProfile();
            createNewProfile.testingMethod();
        */

     //     CreateNewProfile createNewProfile = loader.getController();



/*
                Stage displayStage = new Stage();
                displayStage.setTitle("Add new player");
                VBox layout = new VBox(10);
                displayStage.setScene(new Scene(layout, 300, 500));

                Label idLabel = new Label("Player ID: ");
                Label fnameLabel = new Label("First Name: ");
                Label lnameLabel = new Label("Last Name: ");
                Label addressLabel = new Label("Address: ");
                Label postalcodeLabel = new Label("Postal code: ");
                Label provinceLabel = new Label("Province: ");
                Label contactNumberLabel = new Label("Contact Number: ");

                TextField playeridTxt = new TextField();
                TextField fnametxt = new TextField();
                TextField lnametxt = new TextField();
                TextField addresstxt = new TextField();
                TextField postalcodetxt = new TextField();
                TextField provincetxt = new TextField();
                TextField contacttxt = new TextField();
                Button AddButton = new Button("Add player");

                playeridTxt.setMaxWidth(200);
                fnametxt.setMaxWidth(200);
                lnametxt.setMaxWidth(200);
                addresstxt.setMaxWidth(200);
                postalcodetxt.setMaxWidth(200);
                provincetxt.setMaxWidth(200);
                contacttxt.setMaxWidth(200);

                layout.setPadding(new Insets(20));
                layout.getChildren().addAll(idLabel,playeridTxt,fnameLabel, fnametxt, lnameLabel, lnametxt, addressLabel, addresstxt, postalcodeLabel, postalcodetxt, provinceLabel, provincetxt, contactNumberLabel, contacttxt
                        , AddButton);




                    EventHandler<ActionEvent> addplayer = new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try(Connection connection = DBconfigration.getconnection();
                                PreparedStatement preparedStatementforadd = connection.prepareStatement(addQuery);

                            ) {
                                preparedStatementforadd.setString(1, playeridTxt.getText());
                                preparedStatementforadd.setString(2, fnametxt.getText());
                                preparedStatementforadd.setString(3, lnametxt.getText());
                                preparedStatementforadd.setString(4, addresstxt.getText());
                                preparedStatementforadd.setString(5, postalcodetxt.getText());
                                preparedStatementforadd.setString(6, provincetxt.getText());
                                preparedStatementforadd.setString(7, contacttxt.getText());
                                preparedStatementforadd.executeUpdate();

                                System.out.println("Player has been added successfully!");
                            }
                            catch (SQLException exception){
                                System.out.println("Error loading data...!!!");
                                exception.printStackTrace();

                            }
                        }
                    };

                    AddButton.setOnAction(addplayer);



                    displayStage.showAndWait();
*/





                }






                // respective player's data is displayed when clicked this button
        public void DisplaydataAction(ActionEvent actionEvent) throws IOException {

            try(Connection connection = DBconfigration.getconnection();
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


        }

        // Player can update their data by clicking the upadate button
        public void UpdateButtonAction(ActionEvent actionEvent) throws IOException, SQLException {
        //    EditData edit = new EditData();
        //    edit.Editfields();


            try (Connection connection = DBconfigration.getconnection();

                 PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQ);
                 ){

                //
                int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());

                preparedStatement1.setInt(1, value);
                ResultSet rs1 = preparedStatement1.executeQuery();


                while (rs1.next()) {
                 //   Parent displayplayers = FXMLLoader.load(getClass().getResource("EditData.fxml"));
                    Stage displayStage = new Stage();
                    displayStage.setTitle("Edit player's data");
                    VBox layout = new VBox(10);
                    displayStage.setScene(new Scene(layout, 300, 500));

                    Label fnameLabel = new Label("First Name: ");
                    Label lnameLabel = new Label("Last Name: ");
                    Label addressLabel = new Label("Address: ");
                    Label postalcodeLabel = new Label("Postal code: ");
                    Label provinceLabel = new Label("Province: ");
                    Label contactNumberLabel = new Label("Contact Number: ");


                    TextField fnametxt = new TextField(rs1.getString("first_name"));
                    TextField lnametxt = new TextField(rs1.getString("last_name"));
                    TextField addresstxt = new TextField(rs1.getString("address"));
                    TextField postalcodetxt = new TextField(rs1.getString("postal_code"));
                    TextField provincetxt = new TextField(rs1.getString("province"));
                    TextField contacttxt = new TextField(rs1.getString("phone_number"));
                    Button submitButton = new Button("Submit Changes");
                   EventHandler<ActionEvent> Submitchangesevent = new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent actionEvent) {
                        try {
                            String updateQuery = "Update player set first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ? , phone_number = ? where player_id = ?";
                            PreparedStatement prestmt = connection.prepareStatement(updateQuery);

                            prestmt.setString(1, fnametxt.getText());
                            prestmt.setString(2, lnametxt.getText());
                            prestmt.setString(3, addresstxt.getText());
                            prestmt.setString(4, postalcodetxt.getText());
                            prestmt.setString(5, provincetxt.getText());
                            prestmt.setString(6, contacttxt.getText());
                            prestmt.setInt(7, value);
                            prestmt.executeUpdate();
                            System.out.println("Database successfully updated!");
                        }
                        catch (SQLException exception){
                            System.out.println("Error loading data...!!!");
                            exception.printStackTrace();

                        }
                       }
                   };

                   submitButton.setOnAction(Submitchangesevent);

                    fnametxt.setMaxWidth(200);
                    lnametxt.setMaxWidth(200);
                    addresstxt.setMaxWidth(200);
                    postalcodetxt.setMaxWidth(200);
                    provincetxt.setMaxWidth(200);
                    contacttxt.setMaxWidth(200);

                    layout.setPadding(new Insets(20));
                    layout.getChildren().addAll(fnameLabel, fnametxt, lnameLabel, lnametxt, addressLabel, addresstxt, postalcodeLabel, postalcodetxt, provinceLabel, provincetxt, contactNumberLabel, contacttxt
                            , submitButton);


                    displayStage.showAndWait();

                }
            }
            catch(SQLException e){
                    e.printStackTrace();
                    System.out.println("Error loading data");
                }
            }
        }

