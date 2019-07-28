package LagutinaProject.view;

import LagutinaProject.NotificationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NotificationWindow extends Stage{
    private NotificationController controller;

    public void initRootLayout(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("layout\\notification.fxml"));
        AnchorPane page = null;
        try {
            page = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();

        setTitle("Это конец!");
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        Scene scene = new Scene(page);
        setScene(scene);
    }

    public NotificationController getController() {
        return controller;
    }
}
