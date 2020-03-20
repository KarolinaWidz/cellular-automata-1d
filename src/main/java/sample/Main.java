package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Cellular automaton");
        Controller instance = Controller.getInstance();
        GridPane stageGrid = instance.initBoard();
        primaryStage.setScene(new Scene(stageGrid, 900, 500));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
