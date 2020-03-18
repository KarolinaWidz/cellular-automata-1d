package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Cellular automaton");
        GridPane cellsGrid = new GridPane();
        GridPane stageGrid = new GridPane();
        GridPane menuGrid = new GridPane();
        //labels and text fields
        Label iterationLabel = new Label("Number of iterations: ");
        TextField iterationField = new TextField("10");
        iterationField.setMaxSize(50,10);
        iterationLabel.setLabelFor(iterationField);
        Label cellsNumberLabel = new Label("Number of cells: ");
        TextField cellsNumberField = new TextField("10");
        cellsNumberField.setMaxSize(50,10);
        cellsNumberLabel.setLabelFor(cellsNumberField);
        //buttons
        Button runButton = new Button();
        runButton.setText("RUN");
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("I have no idea what I am doing now");
            }
        });
        //combo boxes
        Label ruleChoiceLabel = new Label("Rule: ");
        ComboBox<String> ruleChoice = new ComboBox<String>();
        ruleChoice.getItems().addAll("30","60","90","120","225");
        ruleChoice.setValue("30");
        ruleChoiceLabel.setLabelFor(ruleChoice);
        primaryStage.setScene(new Scene(stageGrid, 900, 500));
        cellsGrid.setPadding(new Insets(10));
        menuGrid.setPadding(new Insets(10));
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.add(iterationLabel,0,0);
        menuGrid.add(iterationField,1,0);
        menuGrid.add(cellsNumberLabel,0,1);
        menuGrid.add(cellsNumberField,1,1);
        menuGrid.add(ruleChoiceLabel,0,2);
        menuGrid.add(ruleChoice,1,2);
        menuGrid.add(runButton,1,3);
        stageGrid.add(menuGrid,0,0);

        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                Rectangle cell = new Rectangle(10,10);
                cell.setFill(Color.PINK);
                cellsGrid.add(cell,i,j);
            }
        }
        cellsGrid.setAlignment(Pos.CENTER);
        stageGrid.add(cellsGrid,1,1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
