package ui;

//import game.Game;

import javafx.scene.Group;
import javafx.scene.Scene;

public class GameController {
    public GameUI gameUI;
    //  private Game game;


    public GameController() {
        //this.gameUI = new GameUI(this);
        //  this.game=new Game();
        initializeGame();
    }

    private void initializeGame() {
        // Initialize your game logic here

        // For example, you might want to start the game loop or handle user input
    }

    public void startNewGame(Scene scene, Group group) {
//        game = new Game();

        // Create a new instance of GameUI
        gameUI = new GameUI(scene, group);

        // Make sure to initialize the UI if needed
        // gameUI.initializeUI();
    }


    public GameUI getGameUI() {
        return gameUI;
    }
    //  public Game getGame(){
    //return game;
}
// Add methods to handle user input and game logic


