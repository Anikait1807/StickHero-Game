package ui;

import javafx.scene.Group;
import javafx.scene.Scene;

public class GameController {
    public int Hscore;
    public int life;

    public void setLife(int lifes) {
        life = lifes;
    }

    public void setHscore(int hscore) {
        Hscore = hscore;
    }

    public GameUI gameUI;
    public void startNewGame(Scene scene, Group group,int rec,int life) {
        gameUI = new GameUI(scene, group,this);
        this.Hscore=rec;
    }
}

