// MenuScreen.java
package ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class MenuScreen extends VBox {
    private GameController gameController;
    public MenuScreen(GameController gameController,int highScore) {
        this.gameController = gameController;
        //this.highScore=highScore;
        initializeMenu();
        setMenuBackground();
    }
    private void setMenuBackground() {
        Image backgroundImage = new Image("file:./resources/menubackG.jpeg");
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        setBackground(new Background(background));
    }
    public void initializeMenu() {
        Button newGameButton = new Button("New Game");
        Button exitButton = new Button("Exit");
        newGameButton.setOnAction(event -> startNewGameM());
        exitButton.setOnAction(event -> exitGame());
        newGameButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        exitButton.setStyle("-fx-font-size: 16px; -fx-background-color: #f44336; -fx-text-fill: white;");
        setSpacing(30);
        setAlignment(Pos.CENTER);
        getChildren().addAll(newGameButton, exitButton);
    }
    void startNewGameM() {
        Stage primaryStage = (Stage) getScene().getWindow(); // Assuming the MenuScreen is inside a Stage
        Group group = new Group();
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setTitle("Stick Hero");
        primaryStage.show();
        gameController.startNewGame(scene, group,0,3);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }
    void exitGame() {
        System.exit(0);
    }
}
