package LagutinaProject;

import LagutinaProject.view.Stone;
import LagutinaProject.view.MainApp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private MainApp app;
    private Enemy enemy;
    private WinCondition condition;
    private ArrayList<Stone> listOfStones;
    private int numberOfCurrentPlayer;
    private int countOfSelectedStone;

    private boolean isRunning = false;

    public Game(MainApp app, Enemy enemy, WinCondition condition) {
        this.enemy = enemy;
        this.app = app;
        this.condition = condition;
        listOfStones = new ArrayList<>();
        init();
    }

    public Game(MainApp app, ZipGamePocket pocket) {
        this.app = app;
        unzipPocket(pocket);
    }

    private void redraw() {
        for (Stone stone : listOfStones) {
            stone.init(this);
            app.drawTheStone(stone);
        }
    }

    public void reload() {
        redraw();
        switch (numberOfCurrentPlayer) {
            case 1:
                app.setFirstPlayerTurn();
                break;
            case 2:
                app.setSecondPlayerTurn();
                break;
        }
        if (countOfSelectedStone > 0) {
            app.setAbleToEndTurn();
        }
        app.setCountOfStones(listOfStones.size());
    }

    public void unzipPocket(ZipGamePocket pocket){
        this.condition = pocket.getCondition();
        this.countOfSelectedStone = pocket.getCountOfSelectedStone();
        this.enemy = pocket.getEnemy();
        this.listOfStones = pocket.getListOfStones();
        this.numberOfCurrentPlayer = pocket.getNumberOfCurrentPlayer();
        this.isRunning = pocket.isRunning();
        reload();
    }

    public ZipGamePocket getPocket(){
        ZipGamePocket pocket = new ZipGamePocket(enemy,condition,listOfStones,numberOfCurrentPlayer,countOfSelectedStone,isRunning);
        return pocket;
    }

    private void init() {
        int countOfStones = ThreadLocalRandom.current().nextInt(10, 100);
        for (int i = 0; i < countOfStones; i++) {
            Stone stone = new Stone(this);
            listOfStones.add(stone);
        }
    }

    public void start() {
        if (isRunning) {
            try {
                throw new Exception("Game is already running.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            isRunning = true;
            numberOfCurrentPlayer = 1;
            countOfSelectedStone = 0;
            redraw();
            app.setFirstPlayerTurn();
            app.setCountOfStones(listOfStones.size());
        }
    }

    public void switchTurn() {
        switch (numberOfCurrentPlayer) {
            case 1:
                numberOfCurrentPlayer = 2;
                app.setSecondPlayerTurn();
                if (enemy.equals(Enemy.AI)) {
                    countOfSelectedStone = 0;
                    AIMakeMove();
                    switchTurn();
                }
                break;
            case 2:
                numberOfCurrentPlayer = 1;
                app.setFirstPlayerTurn();
                break;
        }
        countOfSelectedStone = 0;
    }

    private void AIMakeMove() {
        int size = listOfStones.size();
        switch (condition) {
            case takeLastStone:
                if (size >= 9) {
                    if ((size - 9) % 8 == 0) {
                        AISelectStones(1);
                    } else {
                        int tmp = (size - 9) % 8;
                        if (tmp > 5) {
                            AISelectStones(tmp % 5);
                        } else {
                            if (tmp > 1) {
                                AISelectStones(tmp - 1);
                            } else {
                                AISelectStones(1);
                            }
                        }
                    }
                } else {
                    if (size > 5) {
                        AISelectStones(size - 5);
                    } else {
                        if (size == 5) {
                            AISelectStones(ThreadLocalRandom.current().nextInt(1, 4));
                        } else {
                            AISelectStones(size);
                        }
                    }
                }
                break;
            case giveLastStone:
                if (size > 11) {
                    AISelectStones((size - 11) % 5);
                } else {
                    if (size > 6) {
                        AISelectStones(size - 6);
                    } else {
                        if (size == 6) {
                            AISelectStones(ThreadLocalRandom.current().nextInt(1, 4));
                        } else {
                            AISelectStones(size - 1);
                        }
                    }
                }
                break;
        }
    }

    private void AISelectStones(int count) {
        for (int i = 0; i < count; i++) {
            if (listOfStones.size() > 0) {
                stoneIsSelect(listOfStones.get(0));
            }
        }
    }

    public void stoneIsSelect(Stone stone) {
        countOfSelectedStone++;
        listOfStones.remove(stone);
        app.removeStone(stone);
        app.setCountOfStones(listOfStones.size());
        if (!checkConditionForWin()) {
            if (countOfSelectedStone > 0) {
                app.setAbleToEndTurn();
                if (countOfSelectedStone == 4 && !(enemy == Enemy.AI && numberOfCurrentPlayer == 2)) {
                    switchTurn();
                }
            }
        }
    }

    public boolean checkConditionForWin() {
        if (listOfStones.size() == 0) {
            switch (condition) {
                case takeLastStone:
                    app.notificationAboutWin(numberOfCurrentPlayer);
                    break;
                case giveLastStone:
                    app.notificationAboutWin(3 - numberOfCurrentPlayer);
                    break;
            }
            return true;
        }
        return false;
    }


    public Enemy getEnemy() {
        return enemy;
    }

    public WinCondition getCondition() {
        return condition;
    }
}
