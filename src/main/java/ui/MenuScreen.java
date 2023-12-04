// MenuScreen.java
package ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScreen extends VBox {

    private GameController gameController;
    private int highScore;


    public MenuScreen(GameController gameController, int highScore) {
        this.gameController = gameController;
        this.highScore = highScore;
        initializeMenu();
        setMenuBackground();
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        if (highScore > this.highScore) {
            this.highScore = highScore;
        }
    }

    private void setMenuBackground() {
        Image backgroundImage = new Image("file:./resources/menubackG.jpeg");

        // Set the size to cover the entire MenuScreen
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);

        // Set the background image
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        setBackground(new Background(background));
    }

    private void initializeMenu() {
        // Create buttons
        Button newGameButton = new Button("New Game");
        Button exitButton = new Button("Exit");

        // Set button actions
        newGameButton.setOnAction(event -> startNewGame());
        exitButton.setOnAction(event -> exitGame());

        // Apply styles to the buttons
        newGameButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        exitButton.setStyle("-fx-font-size: 16px; -fx-background-color: #f44336; -fx-text-fill: white;");

        // Create High Score text
        Text highScoreText = new Text("High Score: " + highScore); // You need to implement getHighScore() method

        // Apply styles to the High Score text
        highScoreText.setStyle("-fx-font-size: 16px; -fx-fill: #333333;");

        // Configure layout
        setSpacing(30);
        setAlignment(Pos.CENTER);

        // Add buttons and High Score text to the layout
        getChildren().addAll(newGameButton, exitButton, highScoreText);
    }


    private void startNewGame() {
        // Call the startNewGame method in the GameController


        // Switch to the GameUI screen
        Stage primaryStage = (Stage) getScene().getWindow(); // Assuming the MenuScreen is inside a Stage
        Group group = new Group();
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setTitle("Stick Hero");
        primaryStage.show();
        gameController.startNewGame(scene, group);
        // Use the existing GameUI instance
        GameUI gameUI = gameController.getGameUI();
        if (gameUI.gameEnd) {
            setHighScore(gameUI.score);
            createGameOverScreen(scene, group, gameUI.score, getHighScore());
        }
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    private void createGameOverScreen(Scene originalScene, Group originalGroup, int score, int highScore) {
        // Create a new Group for the game over screen
        Group gameOverGroup = new Group();

        // Create game over text
        Text gameOverText = new Text("Game Over!");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(250);
        gameOverText.setY(100);

        // Display score and high score
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(250);
        scoreText.setY(150);

        Text highScoreText = new Text("High Score: " + highScore);
        highScoreText.setFont(Font.font("Arial", 20));
        highScoreText.setFill(Color.BLACK);
        highScoreText.setX(250);
        highScoreText.setY(180);

        // Create "Play Again" button
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(250);
        playAgainButton.setLayoutY(220);
        playAgainButton.setOnAction(event -> {
            // Handle "Play Again" button click
            originalGroup.getChildren().remove(gameOverGroup);
            startNewGame();
        });

        // Create "Return to Main Menu" button
        Button returnToMenuButton = new Button("Return to Main Menu");
        returnToMenuButton.setLayoutX(250);
        returnToMenuButton.setLayoutY(250);
        returnToMenuButton.setOnAction(event -> {
            // Handle "Return to Main Menu" button click
            originalGroup.getChildren().remove(gameOverGroup);
            switchToMenuScreen();
        });

        // Add elements to the game over screen group
        gameOverGroup.getChildren().add(gameOverText);
        gameOverGroup.getChildren().add(scoreText);
        gameOverGroup.getChildren().add(highScoreText);
        gameOverGroup.getChildren().add(playAgainButton);
        gameOverGroup.getChildren().add(returnToMenuButton);

        // Add the game over screen group to the original scene
        originalGroup.getChildren().add(gameOverGroup);
    }

    private void switchToMenuScreen() {
        Stage primaryStage = (Stage) getScene().getWindow(); // Assuming the MenuScreen is inside a Stage

        // Create a new instance of MenuScreen
        MenuScreen menuScreen = new MenuScreen(gameController, getHighScore());

        // Set the MenuScreen as the content of the primary stage
        primaryStage.setScene(new Scene(menuScreen, 700, 700));

        // Show the primary stage
        primaryStage.show();
    }

    private void exitGame() {
        System.exit(0);
    }
}
