package ui;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
public class GameUI extends MenuScreen{
    private Stage stage=new Stage();
    public Scene scene;
    public Group group;
    public int lifes=3;
    private Rectangle firstRectangle;
    private Rectangle secondRectangle;
    private Rectangle feed;
    private Rectangle obstacle1;
    private Rectangle obstacle2;
    private Line line;
    private int distance;
    private int toGo, cycle;
    private boolean adjuster;
    public boolean isEnd = false;
    private final Random random;
    private final Rectangle leftFoot;
    private final Rectangle rightFoot;

    private Rectangle body;
    private Circle head;
    public int score = 0;
    private Label scoreLabel;
    private Label scoreLabel3;
    private Label lifelabel;
    private String playerName;
    private int bestRecord;
    private String bestPlayerName;
    public GameController gameController;
    public  boolean gameEnd=false;

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
    public void setHighScore(GameController g, int highScore) {
        if (highScore > g.Hscore) {
            this.bestRecord = highScore;
            g.setHscore(highScore);
        }
    }
    public GameUI(Scene scene, Group group,GameController gameController)
    {
        super(gameController, gameController.Hscore);
        this.scene = scene;
        this.group = group;
        this.gameController = gameController;
        firstRectangle = new Rectangle();
        secondRectangle = new Rectangle();
        feed = new Rectangle(10, 10);
        obstacle1 = new Rectangle(10, 10);
        obstacle2 = new Rectangle(10, 10);
        feed.setFill(Color.DARKRED);
        obstacle2.setFill(Color.BLACK);
        line = new Line();
        leftFoot = new Rectangle(5, 25);
        rightFoot = new Rectangle(5, 25);
        body = new Rectangle(25, 20);
        head = new Circle(10);
        leftFoot.setX(-20);
        rightFoot.setX(-20);
        body.setX(-20);
        head.setCenterX(-20);
        feed.setX(-20);
        obstacle1.setX(-20);
        obstacle2.setX(-20);
        firstRectangle.setX(0);
        scoreLabel = new Label(String.valueOf(gameController.Hscore));
        scoreLabel.setLayoutX(180);
        scoreLabel3= new Label(String.valueOf(score));
        scoreLabel3.setLayoutX(0);
        lifelabel = new Label(String.valueOf(lifes));
        lifelabel.setLayoutX(175);
        lifelabel.setLayoutY(10);
        random = new Random(System.currentTimeMillis());
        scene.setFill(Color.YELLOWGREEN);
        Text slabel = new Text("BEST SCORE :  ");
        slabel.setFill(Color.BLACK);
        slabel.setLayoutX(108);
        slabel.setLayoutY(13);
        Text slabel2 = new Text("LIFES left:  ");
        slabel2.setFill(Color.BLACK);
        slabel2.setLayoutX(108);
        slabel2.setLayoutY(24);
        Text topLeftText = new Text("PRESS ENTER TO ROTATE THE STICK FOR MOTION");
        topLeftText.setFill(Color.BLACK);
        topLeftText.setLayoutX(300);
        topLeftText.setLayoutY(30);
        Text topLeft2Text = new Text("PRESS SPACEBAR TO MAKE THE STICKMAN GO UPSIDE DOWN");
        topLeft2Text.setFill(Color.BLACK);
        topLeft2Text.setLayoutX(300);
        topLeft2Text.setLayoutY(50);
        group.getChildren().addAll(firstRectangle, secondRectangle, scoreLabel,lifelabel,
                line, leftFoot, rightFoot, body, head, feed, obstacle1, obstacle2,scoreLabel3, topLeftText, topLeft2Text,slabel,slabel2);
        setRandoms();
        SetEventHandler();
    }
    private void startGame() {
        if (line.getEndX() - line.getStartX() < distance ||
                line.getEndX() - line.getStartX() > distance + secondRectangle.getWidth())
            isEnd = true;

        playerMove();
    }
    private void playerMove() {
        toGo = (int) (line.getEndX() - line.getStartX() + firstRectangle.getWidth() / 2);
        cycle = toGo / 15;
        cycle *= 2;

        for (int i = 0; i < cycle; i++) {
            goOneCycle();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SetCherry();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (isEnd) {
            gameOver();
            if (lifes==0){
            gameEnd=true;
            exitGame();
            }
            else{
                gameController.setLife(lifes-1);
                lifes=lifes-1;
            }

        }
        else {
            ++score;
            Platform.runLater(() -> scoreLabel.setText(String.valueOf(gameController.Hscore+1)));
            Platform.runLater(() -> scoreLabel3.setText(String.valueOf(score)));
            setRandoms();
        }
    }
    private void SetCherry() {
        if (cycle <= 0)
            return;

        head.setCenterX(head.getCenterX() + 7.5);
        body.setX(body.getX() + 7.5);
        leftFoot.setX(leftFoot.getX() + 7.5);
        leftFoot.setRotate(0);
        rightFoot.setX(rightFoot.getX() + 7.5);
        rightFoot.setRotate(0);

        --cycle;

        if ((obstacle1.getX() - body.getX() <= 15 && obstacle1.getX() - body.getX() >= 0 &&
                obstacle1.getY() - body.getY() <= 15 && obstacle1.getY() - body.getY() >= 0) ||
                (body.getX() - obstacle1.getX() <= 15 && body.getX() - obstacle1.getX() >= 0 &&
                        body.getY() - obstacle1.getY() <= 15 && body.getY() - obstacle1.getY() >= 0)) {
            isEnd = true;
            if (lifes==0){
            gameOver();}
            else{
                gameController.setLife(lifes-1);
                lifes=lifes-1;gameOver();
            }

        }
        if ((obstacle2.getX() - body.getX() <= 15 && obstacle2.getX() - body.getX() >= 0 &&
                obstacle2.getY() - body.getY() <= 15 && obstacle2.getY() - body.getY() >= 0) ||
                (body.getX() - obstacle2.getX() <= 15 && body.getX() - obstacle2.getX() >= 0 &&
                        body.getY() - obstacle2.getY() <= 15 && body.getY() - obstacle2.getY() >= 0)) {
            isEnd = true;

            if (lifes==0){
                gameOver();}
            else{
                gameController.setLife(lifes-1);
                gameOver();
                lifes=lifes-1;
            }
        }
        if ((feed.getX() - body.getX() <= 15 && feed.getX() - body.getX() >= 0 &&
                feed.getY() - body.getY() <= 15 && feed.getY() - body.getY() >= 0) ||
                (body.getX() - feed.getX() <= 15 && body.getX() - feed.getX() >= 0 &&
                        body.getY() - feed.getY() <= 15 && body.getY() - feed.getY() >= 0)) {
            ++score;
            Platform.runLater(() -> scoreLabel.setText(String.valueOf(gameController.Hscore+1)));
            Platform.runLater(() -> scoreLabel3.setText(String.valueOf(score)));
            feed.setX(-20);
           setHighScore(this.gameController,score);
        }
    }
    private void goOneCycle() {
        head.setCenterX(head.getCenterX() + 7.5);
        body.setX(body.getX() + 7.5);
        leftFoot.setX(leftFoot.getX() + 7.5);
        if (head.getCenterY() < firstRectangle.getY())
            leftFoot.setRotate(20);
        else leftFoot.setRotate(-20);
        rightFoot.setX(rightFoot.getX() + 7.5);
        if (head.getCenterY() < firstRectangle.getY())
            rightFoot.setRotate(-20);
        else rightFoot.setRotate(20);
    }
    private void gameOver()
    {
        gameEnd = true;
        scene.setOnMousePressed(null);
        scene.setOnKeyPressed(null);
        PathTransition pathTransition1 = new PathTransition();
        FadeTransition fadeTransition1 = new FadeTransition();
        PathTransition pathTransition2 = new PathTransition();
        FadeTransition fadeTransition2 = new FadeTransition();
        PathTransition pathTransition3 = new PathTransition();
        FadeTransition fadeTransition3 = new FadeTransition();
        PathTransition pathTransition4 = new PathTransition();
        FadeTransition fadeTransition4 = new FadeTransition();
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();
        path1.getElements().add(new MoveTo(head.getCenterX(), head.getCenterY() - 5));
        path1.getElements().add(new LineTo(head.getCenterX(), 700));

        path2.getElements().add(new MoveTo(body.getX() + 7.5, body.getY()));
        path2.getElements().add(new LineTo(body.getX(), 700));

        path3.getElements().add(new MoveTo(leftFoot.getX(), leftFoot.getY()));
        path3.getElements().add(new LineTo(leftFoot.getX(), 700));

        path4.getElements().add(new MoveTo(rightFoot.getX(), rightFoot.getY()));
        path4.getElements().add(new LineTo(rightFoot.getX(), 700));

        pathTransition1.setPath(path1);
        pathTransition2.setPath(path2);
        pathTransition3.setPath(path3);
        pathTransition4.setPath(path4);

        pathTransition1.setNode(head);
        pathTransition2.setNode(body);
        pathTransition3.setNode(leftFoot);
        pathTransition4.setNode(rightFoot);

        pathTransition1.setDuration(Duration.seconds(1));
        pathTransition2.setDuration(Duration.seconds(1));
        pathTransition3.setDuration(Duration.seconds(1));
        pathTransition4.setDuration(Duration.seconds(1));

        fadeTransition1.setNode(head);
        fadeTransition2.setNode(body);
        fadeTransition3.setNode(leftFoot);
        fadeTransition4.setNode(rightFoot);

        fadeTransition1.setFromValue(1);
        fadeTransition2.setFromValue(1);
        fadeTransition3.setFromValue(1);
        fadeTransition4.setFromValue(1);

        fadeTransition1.setToValue(0.3);
        fadeTransition2.setToValue(0.3);
        fadeTransition3.setToValue(0.3);
        fadeTransition4.setToValue(0.3);

        pathTransition1.play();
        pathTransition2.play();
        pathTransition3.play();
        pathTransition4.play();

        fadeTransition1.play();
        fadeTransition2.play();
        fadeTransition3.play();
        fadeTransition4.play();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("New Game Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to start a new game?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Group root = new Group();
                Scene scene = new Scene(root, 800, 600);
                setHighScore(this.gameController,bestRecord);
                this.gameController.setLife(lifes);
                this.gameController.setHscore(bestRecord);
                group.getChildren().clear();
                gameController.startNewGame(this.scene,this.group,this.bestRecord+1,this.lifes);
            } else {
                exitGame();
            }
        });
    }
    private void setRandoms() {
        int width = Math.abs(random.nextInt()) % 100 + 100;
        int height = 200;
        distance = Math.abs(random.nextInt()) % 300 + 100;
        firstRectangle.setWidth(width);
        firstRectangle.setHeight(height);
        secondRectangle.setWidth(width);
        secondRectangle.setHeight(height);
        firstRectangle.setY(660 - height);
        secondRectangle.setY(660 - height);
        secondRectangle.setX(width + distance);
        if (score % 3 != 0)
            feed.setX(-20);

        obstacle1.setX(-20);
        obstacle2.setX(-20);

        line.setStartX(width);
        line.setEndX(width);
        line.setStartY(660 - height);
        line.setEndY(660 - height);

        leftFoot.setX(width / 2);
        rightFoot.setX(width / 2 + 10);
        body.setX(width / 2);
        head.setCenterX(width / 2 + 7.5);

        leftFoot.setY(660 - height - 25);
        rightFoot.setY(660 - height - 25);
        body.setY(660 - height - 25 - 20);
        head.setCenterY(660 - height - 25 - 20 - 5);
    }
    private void SetEventHandler() {
        scene.setOnMousePressed(e -> {
            adjuster = false;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    if (line.getEndX() - line.getStartX() == 0) {
                        while (!adjuster) {
                            if (e.getButton() == MouseButton.PRIMARY)
                                line.setEndY(line.getEndY() - 2);
                            try {
                                sleep(10);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            };
            thread.start();
        });
        scene.setOnMouseReleased(e -> adjuster = true);
        scene.setOnKeyPressed(e -> {
            Thread thread = new Thread(() -> {
                if (e.getCode().equals(KeyCode.ENTER) && line.getEndY() != line.getStartY())
                {
                    line.setEndX(line.getEndX() + line.getStartY() - line.getEndY());
                    line.setEndY(line.getStartY());

                    int feedX = Math.abs(random.nextInt()) % (distance - 100) + 50;
                    if (score % 3 == 0)
                    {
                        feed.setX(feedX + firstRectangle.getWidth());
                        if (random.nextInt() > 0)
                            feed.setY(firstRectangle.getY() + 40);
                        else feed.setY(firstRectangle.getY() - 40);
                    }
                    obstacle2.setX(secondRectangle.getX());
                    obstacle2.setY(secondRectangle.getY() + 20);
                    startGame();
                }
                if (e.getCode().equals(KeyCode.SPACE) && line.getEndY() == line.getStartY()) {
                    moveUpsideDown();
                }
            });
            thread.start();
        });
    }
    private void moveUpsideDown() {
        if (head.getCenterY() < firstRectangle.getY()) {
            head.setCenterY(head.getCenterY() + 2 * (5 + 20 + 25));
            body.setY(body.getY() + 20 + 25 + 25);
            leftFoot.setY(leftFoot.getY() + 25);
            rightFoot.setY(rightFoot.getY() + 25);
        } else if (head.getCenterY() > firstRectangle.getY()) {
            head.setCenterY(head.getCenterY() - 2 * (5 + 20 + 25));
            body.setY(body.getY() - 20 - 25 - 25);
            leftFoot.setY(leftFoot.getY() - 25);
            rightFoot.setY(rightFoot.getY() - 25);
        }
    }
}
