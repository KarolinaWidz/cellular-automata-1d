package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class Controller {
	GridPane initBoard(){
		AtomicInteger cells = new AtomicInteger(10);
		AtomicBoolean gridFlag = new AtomicBoolean(false);
		final int border = 1000;
		GridPane cellsGrid = new GridPane();
		GridPane stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels and text fields
		Label iterationLabel = new Label("Number of iterations: ");
		TextField iterationField = new TextField("10");
		iterationField.setMaxSize(50,10);
		iterationLabel.setLabelFor(iterationField);
		Tooltip iterationToolTip = new Tooltip("Number of iterations");
		iterationField.setTooltip(iterationToolTip);
		Label cellsNumberLabel = new Label("Number of cells: ");
		TextField cellsNumberField = new TextField("10");
		Tooltip cellsToolTip = new Tooltip("Number of cells");
		cellsNumberField.setTooltip(cellsToolTip);
		cellsNumberField.setMaxSize(50,10);
		cellsNumberLabel.setLabelFor(cellsNumberField);

		//buttons
		Button runButton = new Button();
		runButton.setText("RUN");
		runButton.setOnAction((event) ->System.out.println("RUN BUTTON"));
		Button gridButton = new Button("SHOW GRID");
		gridButton.setOnAction((event)-> {
			if(!gridFlag.get()) {
				cellsGrid.setGridLinesVisible(true);
				gridFlag.set(true);
			}
			else{
				cellsGrid.setGridLinesVisible(false);
				gridFlag.set(false);
			}
		});
		//combo boxes
		Label ruleChoiceLabel = new Label("Rule: ");
		ComboBox<String> ruleChoice = new ComboBox<>();
		ruleChoice.setMaxSize(50,10);
		ruleChoice.getItems().addAll("30","60","90","120","225");
		ruleChoice.setValue("30");
		ruleChoiceLabel.setLabelFor(ruleChoice);
		Tooltip ruleChoiceToolTip = new Tooltip("Rule");
		ruleChoice.setTooltip(ruleChoiceToolTip);

		cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);
		Button setSizeButton = new Button("SET SIZE");
		TextField setSizeField = new TextField();
		setSizeField.setMaxSize(50,10);

		draw(cells,border,cellsGrid);

		setSizeButton.setOnAction(event -> {
			cellsGrid.getChildren().clear();
			cells.set(Integer.parseInt(cellsNumberField.getText()));
			setSizeField.setText(cells.toString());
			setSizeField.setEditable(false);
			draw(cells,border,cellsGrid);
		});


		menuGrid.addColumn(0,iterationLabel,cellsNumberLabel,ruleChoiceLabel,setSizeButton,gridButton);
		menuGrid.addColumn(1,iterationField,cellsNumberField,ruleChoice,setSizeField,runButton);
		stageGrid.add(menuGrid,0,0);


		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(cellsGrid);
		cellsGrid.setAlignment(Pos.CENTER);
		stageGrid.add(scrollPane,1,1);
		return stageGrid;

	}
	public void draw(AtomicInteger cells, int border, GridPane cellsGrid){
		for(int i = 0; i< cells.get(); i++){
			for(int j = 0; j< cells.get(); j++){
				Rectangle cell = new Rectangle(border/ cells.get(),border/ cells.get());
				cell.setFill(Cell.ALIVE.getColor());
				cellsGrid.add(cell,i,j);
			}
		}
	}
}
