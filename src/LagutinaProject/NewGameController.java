package LagutinaProject;

import LagutinaProject.view.MainApp;
import LagutinaProject.view.NewGameWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class NewGameController {
    @FXML
    ChoiceBox<String> enemy;
    @FXML
    ChoiceBox<String> condition;
    @FXML
    Button createNewGame;
    @FXML
    Button cancel;


    public void init(MainApp app, NewGameWindow newGameWindow) {
        createNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (enemy.getValue() != null || condition.getValue() != null) {
                   Enemy e = null;
                    switch (enemy.getValue()) {
                        case "Другой игрок":
                            e =Enemy.anotherPlayer;
                            break;
                        case "Компьютер":
                            e =Enemy.AI;
                            break;
                    }
                    WinCondition c = null;
                    switch (condition.getValue()) {
                        case "Взять последний камень":
                            c = WinCondition.takeLastStone;
                            break;
                        case "Отдать последний камнь противнику":
                            c = WinCondition.giveLastStone;
                            break;
                    }
                    app.setGame(new Game(app, e, c));
                    newGameWindow.close();
                }
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGameWindow.close();
            }
        });
    }
}
