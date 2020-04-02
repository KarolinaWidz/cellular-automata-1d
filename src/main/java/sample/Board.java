package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
class Board {

	private static Board instance;
	private GridPane cellsGrid;
	private TextField iterationField;
	private Button setSizeButton;
	private TextField cellsNumberField;
	private Button gridButton;
	private Button runButton;
	private ComboBox<String> ruleChoice;
	private GridPane stageGrid;

	private Board(){
		stageGrid=initBoard();
	}

	GridPane initBoard(){

		this.cellsGrid = new GridPane();
		this.stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label iterationLabel = new Label("Number of iterations: ");
		Label cellsNumberLabel = new Label("Number of cells: ");
		Label ruleChoiceLabel = new Label("Rule: ");

		//fields and combobox
		this.iterationField = new TextField("11");
		this.cellsNumberField = new TextField("11");
		this.ruleChoice = new ComboBox<>();

		//tooltips
		Tooltip iterationToolTip = new Tooltip("Number of iterations");
		Tooltip cellsToolTip = new Tooltip("Number of cells");
		Tooltip ruleChoiceToolTip = new Tooltip("Rule");

		//settings
		this.iterationField.setMaxSize(60,10);
		this.cellsNumberField.setMaxSize(60,10);
		this.ruleChoice.setMaxSize(60,10);

		iterationLabel.setLabelFor(this.iterationField);
		cellsNumberLabel.setLabelFor(this.cellsNumberField);
		ruleChoiceLabel.setLabelFor(this.ruleChoice);

		this.iterationField.setTooltip(iterationToolTip);
		this.cellsNumberField.setTooltip(cellsToolTip);
		this.ruleChoice.setTooltip(ruleChoiceToolTip);

		this.ruleChoice.getItems().addAll("30","60","90","120","225");
		this.ruleChoice.setValue("30");
		this.cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);

		//buttons
		this.runButton = new Button("RUN");
		this.gridButton = new Button("SHOW GRID");
		this.setSizeButton = new Button("SET SIZE");

		this.iterationField.setId("iterationsField");
		this.cellsNumberField.setId("cellsNumberField");
		this.cellsGrid.setId("cellsGrid");

		menuGrid.addColumn(0,iterationLabel,cellsNumberLabel,ruleChoiceLabel,this.setSizeButton,this.gridButton);
		menuGrid.addColumn(1,this.iterationField,this.cellsNumberField,this.ruleChoice,this.runButton);

		this.stageGrid.add(menuGrid,0,0);
		ScrollPane scrollPane = new ScrollPane(this.cellsGrid);
		this.stageGrid.add(scrollPane,1,0);

		return this.stageGrid;
	}
	static Board getInstance(){
		if(instance==null) instance = new Board();
		return instance;
	}
}
