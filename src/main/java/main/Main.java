
package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.GameController;
import ui.MenuScreen;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();

        // Create the menu screen
        MenuScreen menuScreen = new MenuScreen(gameController, 0);

        // Set the initial scene to the menu screen
        Scene scene = new Scene(menuScreen, 800, 600);

        // Set the primaryStage scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stick Hero Game");
        primaryStage.show();
    }
}



