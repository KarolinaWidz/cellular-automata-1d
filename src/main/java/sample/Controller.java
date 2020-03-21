package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

class Controller {

	private static final Controller INSTANCE = new Controller();
		private Integer cells;
		private Integer iterations;
		private AtomicBoolean gridFlag;
		private Integer rule;
		private Cell [][] cellsMatrix;
		private final int border = 1000;
		private GridPane cellsGrid;
		private TextField iterationField;
		private TextField cellsNumberField;

	private Controller() {
			this.cells = 11;
			this.gridFlag = new AtomicBoolean(false);
			this.iterations = 11;
			this.rule = 30;
			this.cellsMatrix = new Cell[this.iterations][this.cells];
		}

	GridPane initBoard(){

		cellsGrid = new GridPane();
		GridPane stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label iterationLabel = new Label("Number of iterations: ");
		Label cellsNumberLabel = new Label("Number of cells: ");
		Label ruleChoiceLabel = new Label("Rule: ");

		//fields and combobox
		iterationField = new TextField("11");
		cellsNumberField = new TextField("11");
		ComboBox<String> ruleChoice = new ComboBox<>();

		//tooltips
		Tooltip iterationToolTip = new Tooltip("Number of iterations");
		Tooltip cellsToolTip = new Tooltip("Number of cells");
		Tooltip ruleChoiceToolTip = new Tooltip("Rule");

		//settings
		iterationField.setMaxSize(60,10);
		cellsNumberField.setMaxSize(60,10);
		ruleChoice.setMaxSize(60,10);
		iterationLabel.setLabelFor(iterationField);
		cellsNumberLabel.setLabelFor(cellsNumberField);
		ruleChoiceLabel.setLabelFor(ruleChoice);
		iterationField.setTooltip(iterationToolTip);
		cellsNumberField.setTooltip(cellsToolTip);
		ruleChoice.setTooltip(ruleChoiceToolTip);
		ruleChoice.getItems().addAll("30","60","90","120","225");
		ruleChoice.setValue("30");
		cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);

		//buttons
		Button runButton = new Button("RUN");
		Button gridButton = new Button("SHOW GRID");
		Button setSizeButton = new Button("SET SIZE");

		gridButton.setOnAction((event)-> {
			if(!gridFlag.get()) {
				cellsGrid.setGridLinesVisible(true);
				gridFlag.set(true);
			}
			else{
				cellsGrid.setGridLinesVisible(false);
				gridFlag.set(false);
			}});

		draw(cellsGrid);
		setSizeButton.setOnAction(event -> getValues(cellsNumberField.getText(),iterationField.getText()));

		runButton.setOnAction((event) ->{
			getValues(cellsNumberField.getText(),iterationField.getText());
			rule = Integer.parseInt(ruleChoice.getValue());
			cellsMatrix = new Cell[iterations][cells];
			simulation(rule,cellsGrid);
		});

		//id
		iterationField.setId("iterationsField");
		cellsNumberField.setId("cellsNumberField");
		cellsGrid.setId("cellsGrid");

		menuGrid.addColumn(0,iterationLabel,cellsNumberLabel,ruleChoiceLabel,setSizeButton,gridButton);
		menuGrid.addColumn(1,iterationField,cellsNumberField,ruleChoice,runButton);
		stageGrid.add(menuGrid,0,0);
		ScrollPane scrollPane = new ScrollPane(cellsGrid);
		stageGrid.add(scrollPane,1,0);
		return stageGrid;
	}

	private void draw(GridPane cellsGrid){
		for(int i = 0; i< this.cells; i++){
			for(int j = 0; j< this.iterations; j++){
				cellsGrid.add(new Rectangle(this.border / this.cells, this.border / this.cells,Cell.DEAD.getColor()),i,j);
			}
		}
	}

	private void simulation(Integer rule, GridPane cellsGrid){
		String binaryRule = String.format("%8s",Integer.toBinaryString(rule)).replace(" ","0");
		for(int x=0;x<this.cells;x++){
			if(x==this.cells/2){
				cellsGrid.add(new Rectangle(this.border / this.cells, this.border / this.cells,Cell.ALIVE.getColor()),x,0);
				this.cellsMatrix[0][x]=Cell.ALIVE;
			}
			else{
				cellsGrid.add(new Rectangle(this.border / this.cells, this.border / this.cells,Cell.DEAD.getColor()),x,0);
				this.cellsMatrix[0][x]=Cell.DEAD;
			}
		}
		ActiveCells activeCells;
		for(int y=1;y<this.iterations-1;y++) {
			for (int x = 0; x < this.cells; x++) {
				int position;
				if(x==this.cells-1) activeCells = new ActiveCells(this.cellsMatrix[y-1][x-1], this.cellsMatrix[y-1][x], this.cellsMatrix[y-1][0]);
				else if (x==0) activeCells = new ActiveCells(this.cellsMatrix[y-1][this.cells-1], this.cellsMatrix[y-1][0], this.cellsMatrix[y-1][1]);
				else activeCells = new ActiveCells(this.cellsMatrix[y-1][x-1], this.cellsMatrix[y-1][x], this.cellsMatrix[y-1][x +1]);
				if (activeCells.left.getFlag() && activeCells.center.getFlag() && activeCells.right.getFlag())position=0;
				else if (activeCells.left.getFlag() && activeCells.center.getFlag()) position=1;
				else if (activeCells.left.getFlag() && activeCells.right.getFlag()) position=2;
				else if (activeCells.left.getFlag())position=3;
				else if (activeCells.center.getFlag() && activeCells.right.getFlag()) position=4;
				else if (activeCells.center.getFlag())position=5;
				else if (activeCells.right.getFlag())position=6;
				else position=7;
				next(binaryRule,x,y,position);
			}
		}
	}
	static Cell ruleConverter(char sign){
		if(sign=='0') return Cell.DEAD;
		else if(sign=='1') return Cell.ALIVE;
		else throw new IllegalArgumentException();
	}

	private void next(String binaryRule, int x, int y, int position){
		this.cellsMatrix[y][x]=ruleConverter(binaryRule.charAt(position));
		cellsGrid.add(new Rectangle(this.border / this.cells,this.border / this.cells,
				this.cellsMatrix[y][x].getColor()),x,y);
	}

	private void getValues(String cellsNumber, String iterationsNumber){
		if(Pattern.matches("^\\d*$",cellsNumber) && Pattern.matches("^\\d*$",iterationsNumber)
				&& !cellsNumber.equals("") && !iterationsNumber.equals("")) {
			cellsGrid.getChildren().clear();
			cells = (Integer.parseInt(cellsNumber));
			iterations = (Integer.parseInt(iterationsNumber));
			draw(cellsGrid);
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
				cells=0;
				iterations=0;
			}

		}
	}

	static Controller getInstance() {
		return INSTANCE;
	}
}
