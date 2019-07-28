package LagutinaProject;

import LagutinaProject.view.MainApp;
import LagutinaProject.view.NewGameWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {
    @FXML
    VBox firstPlayerBox;
    @FXML
    VBox secondPlayerBox;
    @FXML
    Button newGame;
    @FXML
    Text countOfStones;
    @FXML
    Pane table;
    @FXML
    Button save;
    @FXML
    Button load;

    public void init(MainApp app){
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewGameWindow newGameWindow = new NewGameWindow();
                newGameWindow.initRootLayout(app.getPrimaryStage());
                newGameWindow.getController().init(app, newGameWindow);
                newGameWindow.showAndWait();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (app.getGame()!=null) {
                    try {
                        app.saveGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    app.loadGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Pane getTable() {
        return table;
    }

    public Text getCountOfStones() {
        return countOfStones;
    }

    public VBox getFirstPlayerBox() {
        return firstPlayerBox;
    }

    public VBox getSecondPlayerBox() {
        return secondPlayerBox;
    }
}
