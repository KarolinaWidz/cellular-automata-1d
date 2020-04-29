package sample.twoDimensionalGrainGrowth;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;
import sample.twoDimensionalGrainGrowth.initialStates.StructureChooser;
import sample.twoDimensionalGrainGrowth.neighbourTypes.NeighbourChooser;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		this.structureChooser = new StructureChooser();
		setSize();
		board.getSetInitialsButton().setOnAction(event -> setInitialCells(board.getNucleationComboBox().getValue()));
		board.getNucleationComboBox().setOnAction(event -> setFields(board.getNucleationComboBox().getValue()));
		board.getNeighbourComboBox().setOnAction(event -> {clearCells(); setInitialCells(board.getNucleationComboBox().getValue());});
		board.getOneStepButton().setOnAction(event-> simulationChooser());
		board.getFiveStepsButton().setOnAction(event -> {for(int i=0;i<5;i++) simulationChooser();});
		board.getMonteCarloSimulationButton().setOnAction(event->mcChooser());
	}

	private void setSize() {
		this.xSize = checkInt(board.getXSizeField().getText(),1);
		this.ySize = checkInt(board.getYSizeField().getText(),1);
		this.cellsMatrix = new Cell[this.ySize][this.xSize];
		board.getCellsGrid().getChildren().clear();
		for(int y = 0; y<this.ySize ; y++){
			for(int x = 0; x< this.xSize; x++){
				this.cellsMatrix[y][x]= new Cell(CellState.DEAD,x,y,board.getBOARD_SIZE()/this.xSize);
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
	private Cell[][] createBiggerCellsMatrix(){
		Cell [][] biggerCellsMatrix = new Cell[this.ySize+2][this.xSize+2];
		for(int i=0; i<this.ySize+2; i++)
			for(int j=0; j<this.xSize+2; j++)
				if(i==0 || j ==0 || i==this.ySize+1 || j==this.xSize+1)
					biggerCellsMatrix[i][j]=new Cell(CellState.DEAD,0,0,board.getBOARD_SIZE()/this.xSize);
				else
					biggerCellsMatrix[i][j]=new Cell(this.cellsMatrix[i-1][j-1]);
		return biggerCellsMatrix;
	}

	private void simulationChooser(){
		Cell [][] newCellsMatrix = new Cell[this.ySize][this.xSize];
		for(int i=0; i<this.ySize; i++)
			for(int j=0; j<this.xSize; j++)
				newCellsMatrix[i][j]=new Cell(this.cellsMatrix[i][j]);

		if(board.getBoundaryConditionComboBox().getValue().equals("Periodic"))
			simulation(newCellsMatrix, this.cellsMatrix, 0);
		else
			simulation(newCellsMatrix,createBiggerCellsMatrix(),1);


		for(int i=0; i<this.ySize; i++)
			for(int j=0; j<this.xSize; j++)
				this.cellsMatrix[i][j].copyState(newCellsMatrix[i][j]);
	}

	private void simulation(Cell [][] newCellsMatrix, Cell [][] cellsMatrix, int shift){
		NeighbourChooser neighbourChooser = new NeighbourChooser();
		List<Cell> neighbours;
		for (int y = shift; y < cellsMatrix.length-shift; y++) {
			for (int x = shift; x < cellsMatrix[0].length-shift; x++) {
				if (!cellsMatrix[y][x].getState().getFlag()) {
					neighbours = neighbourChooser.addNeighbours(x, y,cellsMatrix);
					Map<Cell, Integer> statesMap = new HashMap<>();
					neighbours.stream().filter(cell -> cell.getState().getFlag()).forEach(cell -> statesMap
							.put(cell, statesMap.getOrDefault(cell, 0) + 1));
					if (statesMap.entrySet().stream().anyMatch(i -> i.getValue() > 0)) {
						newCellsMatrix[y-shift][x-shift].copyState(Collections.max(statesMap.entrySet(),
								Comparator.comparingInt(Map.Entry::getValue)).getKey());
					}
				}
			}
		}
	}


	private void mcChooser(){
		for(int i=0;i<Math.max(this.xSize,this.ySize);i++)
			simulationChooser();
		for(int i=0;i< checkInt(board.getMcIterationsField().getText(),0)+1;i++){
			if(board.getBoundaryConditionComboBox().getValue().equals("Periodic"))
				mcSimulation(checkDouble(board.getKtField().getText()),i,this.cellsMatrix,0);
			else
				mcSimulation(checkDouble(board.getKtField().getText()),i,createBiggerCellsMatrix(),1);
		}
	}

	private void mcSimulation(double kt, int i, Cell [][] newCellMatrix,int shift){
		Random random = new Random();
		NeighbourChooser neighbourChooser = new NeighbourChooser();
		List <Cell> neighbours;
		List <Cell> mixedCells = Arrays.stream(this.cellsMatrix)
				.flatMap(Arrays::stream)
				.collect(Collectors.toList());
		for(int j=0;j<(this.xSize*this.ySize);j++){
			Collections.shuffle(mixedCells);
			neighbours = neighbourChooser.addNeighbours(mixedCells.get(0).getX()+shift, mixedCells.get(0).getY()+shift,newCellMatrix)
					.stream().filter(cell -> cell.getState().getFlag()).collect(Collectors.toList());
			Collections.shuffle(neighbours);
			Cell actual;
			if(neighbours.size()==0) break;
			if(i==0) actual = mixedCells.get(0);
			else actual = new Cell(neighbours.get(0));
			int counter = (int) neighbours.stream().filter(cell -> cell.getColor() != actual.getColor()).count();
			double energyDifference = counter - actual.getEnergy();
			if(energyDifference>0 && random.nextDouble()<=Math.exp((-1)*energyDifference/kt)){
				actual.setEnergy(counter);
				if(i!=0) mixedCells.get(0).copyState(actual);
			}
			if(energyDifference<=0){
				actual.setEnergy(counter);
				if(i!=0) mixedCells.get(0).copyState(actual);
			}
			mixedCells.remove(0);
		}
	}

	private int checkInt(String size, int value){
		if(Pattern.matches("^\\d*$",size) && (Integer.parseInt(size))>0){
			return (Integer.parseInt(size));
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
				return value;
			}
		}
	}

	private double checkDouble(String size){
		if(NumberUtils.isParsable(size.replace(",",".")) && (Double.parseDouble(size))>=0.1 && (Double.parseDouble(size))<=6){
			return (Double.parseDouble(size));
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
				return 0.1;
			}
		}
	}
}
