package LagutinaProject.view;

import LagutinaProject.NewGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NewGameWindow extends Stage {
    private NewGameController controller;

    public void initRootLayout(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("layout\\new_game.fxml"));
        AnchorPane page = null;
        try {
            page = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();

        setTitle("Новая игра");
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        Scene scene = new Scene(page);
        setScene(scene);
    }

    public NewGameController getController() {
        return controller;
    }
}
