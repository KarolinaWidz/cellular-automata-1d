package sample.twoDimensionalGrainGrowth;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import sample.twoDimensionalGrainGrowth.initialStates.StructureChooser;
import sample.twoDimensionalGrainGrowth.neighbourTypes.NeighbourChooser;

import java.util.*;
import java.util.regex.Pattern;

@Getter
public class Controller2d {
	private static Board board;
	private GridPane stageGrid;
	private int xSize;
	private int ySize;
	private Cell [][] cellsMatrix;
	private StructureChooser structureChooser;


	public Controller2d(){
		board = Board.getInstance();
		this.stageGrid = board.getStageGrid();
		this.cellsMatrix = new Cell[this.ySize][this.xSize];
		this.structureChooser = new StructureChooser();
		setSize();
		board.getSetInitialsButton().setOnAction(event -> setInitialCells(board.getNucleationComboBox().getValue()));
		board.getNucleationComboBox().setOnAction(event -> setFields(board.getNucleationComboBox().getValue()));
		board.getOneStepButton().setOnAction(event-> simulation());
	}

	private void setSize() {
		this.xSize = checkValues(board.getXSizeField().getText());
		this.ySize = checkValues(board.getYSizeField().getText());
		this.cellsMatrix = new Cell[this.ySize][this.xSize];
		board.getCellsGrid().getChildren().clear();
		for(int y = 0; y<this.ySize ; y++){
			for(int x = 0; x< this.xSize; x++){
				this.cellsMatrix[y][x]= new Cell(CellState.DEAD,x,y,board.getBOARD_SIZE()/this.xSize,0);
				board.getCellsGrid().add(this.cellsMatrix[y][x].getRectangle(),x,y);
			}
		}
		board.getCellsGrid().setGridLinesVisible(false);
		board.getCellsGrid().setGridLinesVisible(true);
	}

	private void clearCells(){
		for(int y = 0; y<this.ySize ; y++){
			for(int x = 0; x< this.xSize; x++){
				this.cellsMatrix[y][x].setDead();
			}
		}
	}
	private void setInitialCells(String state){
		clearCells();
		setSize();
		if(!state.equals("Own")){
			structureChooser.drawStructure(state,this.cellsMatrix,board);
		}
	}
	private void setFields(String state){
		clearCells();
		structureChooser.changeFields(state,board);
	}

	private void simulation(){
		Cell [][] newCellsMatrix = new Cell[this.ySize][this.xSize];
		for(int i=0; i<this.ySize; i++)
			for(int j=0; j<this.xSize; j++)
				newCellsMatrix[i][j]=new Cell(this.cellsMatrix[i][j]);

		NeighbourChooser neighbourChooser = new NeighbourChooser(board.getNeighbourComboBox().getValue(),
				board.getBoundaryConditionComboBox().getValue());

		List<Cell> neighbours;
		for(int y=0;y<this.ySize;y++){
			for(int x=0;x<this.xSize;x++) {
				if(!this.cellsMatrix[y][x].getState().getFlag()){
					neighbours = neighbourChooser.addNeighbours(x,y,this.cellsMatrix);
					Map<Cell,Integer> statesMap = new HashMap<>();
					neighbours.stream().filter(cell -> cell.getState().getFlag()).forEach(cell -> statesMap
							.put(cell,statesMap.getOrDefault(cell,0)+1));
					if(statesMap.containsValue(1)){
						newCellsMatrix[y][x].copyState(Collections.max(statesMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
					}
					}
				}
		}

		for(int i=0; i<this.ySize; i++)
			for(int j=0; j<this.xSize; j++)
				this.cellsMatrix[i][j].copyState(newCellsMatrix[i][j]);
	}

	private int checkValues(String size){
		if(Pattern.matches("^\\d*$",size) && (Integer.parseInt(size))>0){
			return (Integer.parseInt(size));
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
				return 1;
			}
		}
	}
}
