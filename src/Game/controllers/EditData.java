package Game.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditData implements Initializable {


    private Stage EditStage;

    public void Editfields() throws IOException {

   Parent Edit = FXMLLoader.load(getClass().getResource("FXMLs/EditData.fxml"));
   this.EditStage = new Stage();
   EditStage.setTitle("Edit player's details");
   EditStage.setScene(new Scene(Edit));
   EditStage.showAndWait();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
