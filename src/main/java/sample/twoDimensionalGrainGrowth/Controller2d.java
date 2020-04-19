package sample.twoDimensionalGrainGrowth;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Controller2d {
	private static Board board;
	private GridPane stageGrid;
	private int xSize;
	private int ySize;
	private Cell [][] cellsMatrix;


	public Controller2d(){
		board = Board.getInstance();
		this.stageGrid = board.getStageGrid();
		this.cellsMatrix = new Cell[this.ySize][this.xSize];
		setSize();
		board.getSetSizeButton().setOnAction(event -> setSize());
	}

	private void setSize() {
		checkValues(board.getXSizeField().getText(),board.getYSizeField().getText());
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



	private void checkValues(String xSize, String ySize){
		if(Pattern.matches("^\\d*$",xSize) && (Integer.parseInt(xSize))>=4
		&& Pattern.matches("^\\d*$",ySize) && (Integer.parseInt(ySize))>=4 ){
			this.xSize = (Integer.parseInt(xSize));
			this.ySize = (Integer.parseInt(ySize));
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
			}
		}
	}
}
