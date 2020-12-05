package sample;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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


        // intialize method from Initializable interface... Which intilize the stuff here it populates the combobox with lit of items...
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            playerCombobox.setItems(list);
        }

        // Event listener for combobox: playerid
        public void gettingValue() {


        }

        // Creating Database connection
        Connection connection = null;
        ResultSet resultSet = null;
        String query;
        String q;

        {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
                Statement statement = connection.createStatement();
                query = "SELECT  * FROM player";
                resultSet = statement.executeQuery(query);

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
            CreateNewProfile createNewProfile = new CreateNewProfile();
            createNewProfile.testingMethod();
        }
                // respective player's data is displayed when clicked this button
        public void DisplaydataAction(ActionEvent actionEvent) throws IOException {

            try {

                //
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
                int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());
                q = "Select * from player WHERE player_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(q);
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
        public void UpdateButtonAction(ActionEvent actionEvent) throws IOException {
        //    EditData edit = new EditData();
        //    edit.Editfields();


            try {

                //
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false", "root", "");
                int value = Integer.parseInt(this.playerCombobox.getSelectionModel().getSelectedItem());
                String sqlQ = "Select * from player WHERE player_id = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQ);
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
