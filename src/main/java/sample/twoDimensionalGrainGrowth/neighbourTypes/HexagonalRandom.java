package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.List;
import java.util.Random;

public class HexagonalRandom extends VonNeumann{

	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid, Board board) {
		List <Cell> cellList = super.addNeighbours(x,y,cellsGrid, board);
		int firstY = 1;
		int firstX = 1;
		int secondY = -1;
		int secondX = -1;
		if(new Random().nextInt(2)==1){
			firstY=-1;
			secondY=1;
			secondX=-1;
		}
		if(board.getBoundaryConditionComboBox().getValue().equals("Periodic")) {
			cellList.add(cellsGrid[periodicBoundaryConverter(y + firstY,cellsGrid.length)]
					[periodicBoundaryConverter(x + firstX,cellsGrid[0].length)]);
			cellList.add(cellsGrid[periodicBoundaryConverter(y + secondY,cellsGrid.length)]
					[periodicBoundaryConverter(x + secondX,cellsGrid[0].length)]);
		}
		else{
			cellList.add(cellsGrid[y + firstY][x + firstX]);
			cellList.add(cellsGrid[y + secondY][x + secondX]);
		}
		return cellList;
	}
}
