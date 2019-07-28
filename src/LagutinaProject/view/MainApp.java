package LagutinaProject.view;

import LagutinaProject.Game;
import LagutinaProject.MainController;
import LagutinaProject.ZipGamePocket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Button turnButton;
    private Game game;
    private MainController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initRootLayout();
        primaryStage.setResizable(false);

        turnButton = new Button("Закончить ход");
        turnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.switchTurn();
            }
        });
        turnButton.setDisable(true);
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("layout\\window.fxml"));
            rootLayout = loader.load();
            controller = loader.getController();
            controller.init(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbleToEndTurn() {
        turnButton.setDisable(false);
    }

    public void saveGame() throws IOException {
        if (game != null) {
            if (!Files.exists(Paths.get("res\\save.out"))) {
                Files.createFile(Paths.get("res\\save.out"));
            }
            FileOutputStream fos = new FileOutputStream("res\\save.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game.getPocket());
            oos.flush();
            oos.close();
        }
    }

    public void loadGame() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get("res\\save.out"))) {
            FileInputStream fos = new FileInputStream("res\\save.out");
            ObjectInputStream oos = new ObjectInputStream(fos);
            ZipGamePocket pocket = (ZipGamePocket) oos.readObject();
            oos.close();
            cleanup();
            if (game == null) {
                game = new Game(this, pocket);
            } else {
                game.unzipPocket(pocket);
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void notificationAboutWin(int numberOfPlayer) {
        NotificationWindow notification = new NotificationWindow();
        notification.initRootLayout(primaryStage);
        notification.getController().init(notification, numberOfPlayer);
        notification.showAndWait();
        turnButton.setDisable(true);
    }

    public void cleanup() {
        this.game = null;
        controller.getTable().getChildren().clear();
    }

    public void setGame(Game game) {
        cleanup();
        this.game = game;
        game.start();
    }

    public void setCountOfStones(int count) {
        controller.getCountOfStones().setText(String.valueOf(count));
    }

    public void drawTheStone(Stone stone) {
        stone.draw(controller.getTable());
    }

    public void removeStone(Stone stone) {
        controller.getTable().getChildren().remove(stone);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setFirstPlayerTurn() {
        if (turnButton.getParent() != null) {
            ((Pane) turnButton.getParent()).getChildren().remove(turnButton);
        }
        turnButton.setDisable(true);
        controller.getFirstPlayerBox().getChildren().add(turnButton);
    }

    public void setSecondPlayerTurn() {
        if (turnButton.getParent() != null) {
            ((Pane) turnButton.getParent()).getChildren().remove(turnButton);
        }
        turnButton.setDisable(true);
        controller.getSecondPlayerBox().getChildren().add(turnButton);
    }
}
