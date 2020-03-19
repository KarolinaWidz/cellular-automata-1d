package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.atomic.AtomicBoolean;

class Controller {

	private static final Controller INSTANCE = new Controller();
		private Integer cells;
		private Integer iterations;
		private AtomicBoolean gridFlag;
		private Integer rule;
		private Cell [][] cellsMatrix;
		private final int border = 1000;
		private GridPane cellsGrid;
		private GridPane stageGrid;
		private GridPane menuGrid;

		private Controller() {
			this.cells = 11;
			this.gridFlag = new AtomicBoolean(false);
			this.iterations = 10;
			this.rule = 30;
			this.cellsMatrix = new Cell[this.iterations][this.cells];
		}

	GridPane initBoard(){
		cellsGrid = new GridPane();
		stageGrid = new GridPane();
		menuGrid = new GridPane();

		//labels and text fields
		Label iterationLabel = new Label("Number of iterations: ");
		TextField iterationField = new TextField("10");
		iterationField.setMaxSize(60,10);
		iterationLabel.setLabelFor(iterationField);
		Tooltip iterationToolTip = new Tooltip("Number of iterations");
		iterationField.setTooltip(iterationToolTip);
		Label cellsNumberLabel = new Label("Number of cells: ");
		TextField cellsNumberField = new TextField("11");
		Tooltip cellsToolTip = new Tooltip("Number of cells");
		cellsNumberField.setTooltip(cellsToolTip);
		cellsNumberField.setMaxSize(60,10);
		cellsNumberLabel.setLabelFor(cellsNumberField);

		//buttons
		Button runButton = new Button();
		runButton.setText("RUN");
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
		ruleChoice.setMaxSize(60,10);
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

		draw(cellsGrid);

		setSizeButton.setOnAction(event -> {
			cellsGrid.getChildren().clear();
			cells=(Integer.parseInt(cellsNumberField.getText()));
			iterations=(Integer.parseInt(iterationField.getText()));
			draw(cellsGrid);
		});

		runButton.setOnAction((event) ->{
			cells=(Integer.parseInt(cellsNumberField.getText()));
			iterations=(Integer.parseInt(iterationField.getText()));
			rule = Integer.parseInt(ruleChoice.getValue());
			cellsMatrix = new Cell[iterations][cells];

			for(int i=0;i<iterations;i++){
				for(int j=0;j<cells;j++)
				{
					cellsMatrix[i][j]=Cell.DEAD;
				}
			}
			simulation(rule,cellsGrid);
		});

		menuGrid.addColumn(0,iterationLabel,cellsNumberLabel,ruleChoiceLabel,setSizeButton,gridButton);
		menuGrid.addColumn(1,iterationField,cellsNumberField,ruleChoice,runButton);
		stageGrid.add(menuGrid,0,0);


		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(cellsGrid);
		cellsGrid.setAlignment(Pos.CENTER);
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
		cellsGrid.getChildren().clear();
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
		System.out.println(this.cellsMatrix[0][cells/2].getFlag());

		for(int y=0;y<this.iterations-1;y++) {
			for (int x = 1; x < this.cells-1; x++) {
				ActiveCells activeCells = new ActiveCells(this.cellsMatrix[y][x - 1], this.cellsMatrix[y][x], this.cellsMatrix[y][x + 1]);
				if (activeCells.left.getFlag() && activeCells.center.getFlag() && activeCells.right.getFlag())next(binaryRule, x, y, 0);
				else if ((activeCells.left.getFlag() && activeCells.center.getFlag() && !activeCells.right.getFlag())) next(binaryRule,x,y,1);
				else if ((activeCells.left.getFlag() && !activeCells.center.getFlag() && activeCells.right.getFlag())) next(binaryRule,x,y,2);
				else if ((activeCells.left.getFlag() && !activeCells.center.getFlag() && !activeCells.right.getFlag())) next(binaryRule,x,y,3);
				else if ((!activeCells.left.getFlag() && activeCells.center.getFlag() && activeCells.right.getFlag())) next(binaryRule,x,y,4);
				else if ((!activeCells.left.getFlag() && activeCells.center.getFlag() && !activeCells.right.getFlag())) next(binaryRule,x,y,5);
				else if ((!activeCells.left.getFlag() && !activeCells.center.getFlag() && activeCells.right.getFlag())) next(binaryRule,x,y,6);
				else if ((!activeCells.left.getFlag() && !activeCells.center.getFlag() && !activeCells.right.getFlag())) next(binaryRule,x,y,7);
			}
		}

	}
	Cell ruleConverter(char sign){
		if(sign=='0') return Cell.DEAD;
		else return Cell.ALIVE;

	}
	void next(String binaryRule,int x, int y, int position){
		this.cellsMatrix[y+1][x]=ruleConverter(binaryRule.charAt(position));
		cellsGrid.add(new Rectangle(this.border / this.cells,this.border / this.cells,
				this.cellsMatrix[y][x].getColor()),x,y+1);
	}


	public static Controller getInstance() {
		return INSTANCE;
	}
}
