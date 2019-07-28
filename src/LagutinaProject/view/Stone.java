package LagutinaProject.view;

import LagutinaProject.Game;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.concurrent.ThreadLocalRandom;

public class Stone extends ImageView implements Serializable{
    private boolean isSelected = false;
    private static Image image;
    static {
        try {
            image = new Image(new File("res\\stone.png").toURI().toURL().toString(),true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Stone(Game game) {
        init(game);
    }

    public void init(Game game){
        setImage(image);
        setScaleX(0.1);
        setScaleY(0.1);setLayoutX(ThreadLocalRandom.current().nextInt(-200,  200));
        setLayoutY(ThreadLocalRandom.current().nextInt(-200, 0));
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isSelected = true;
                setVisible(false);
                game.stoneIsSelect(Stone.this);
            }
        });
    }

    public void draw(Pane pane){
        pane.getChildren().add(this);
    }

    public boolean isSelected() {
        return isSelected;
    }
}
