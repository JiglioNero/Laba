package LagutinaProject;

import LagutinaProject.view.NotificationWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class NotificationController {
    @FXML
    Text text;
    @FXML
    Button cancel;

    public void init(NotificationWindow window, int numberOfWinnerPlayer){
        text.setText(String.format("Победил Игрок%d",numberOfWinnerPlayer));
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
    }
}
