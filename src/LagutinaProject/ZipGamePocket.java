package LagutinaProject;

import LagutinaProject.view.Stone;

import java.io.Serializable;
import java.util.ArrayList;

public class ZipGamePocket implements Serializable{
    private Enemy enemy;
    private WinCondition condition;
    private ArrayList<Stone> listOfStones;
    private int numberOfCurrentPlayer;
    private int countOfSelectedStone;
    private boolean isRunning;

    public ZipGamePocket(Enemy enemy, WinCondition condition, ArrayList<Stone> listOfStones, int numberOfCurrentPlayer, int countOfSelectedStone, boolean isRunning) {
        this.enemy = enemy;
        this.condition = condition;
        this.listOfStones = listOfStones;
        this.numberOfCurrentPlayer = numberOfCurrentPlayer;
        this.countOfSelectedStone = countOfSelectedStone;
        this.isRunning = isRunning;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public WinCondition getCondition() {
        return condition;
    }

    public ArrayList<Stone> getListOfStones() {
        return listOfStones;
    }

    public int getNumberOfCurrentPlayer() {
        return numberOfCurrentPlayer;
    }

    public int getCountOfSelectedStone() {
        return countOfSelectedStone;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
