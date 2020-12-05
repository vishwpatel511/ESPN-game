package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewProfile extends Main {

    private Stage profileStage;

    public CreateNewProfile() throws IOException {

    }

    public void testingMethod() throws IOException {
        Parent addplayer = FXMLLoader.load(getClass().getResource("CreateNewProfile.fxml"));
        this.profileStage = new Stage();
        profileStage.setTitle("Add new Player of your choice");
        profileStage.setScene(new Scene(addplayer));
        profileStage.showAndWait();
    }
}

/*



 */