package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditData implements Initializable {


    private Stage EditStage;
    Main main = new Main();

    public void Editfields() throws IOException {

   Parent Edit = FXMLLoader.load(getClass().getResource("EditData.fxml"));
   this.EditStage = new Stage();
   EditStage.setTitle("Edit player's details");
   EditStage.setScene(new Scene(Edit));
   EditStage.showAndWait();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}