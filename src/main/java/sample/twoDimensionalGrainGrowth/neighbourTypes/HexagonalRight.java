package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.List;

public class HexagonalRight extends VonNeumann {
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid, Board board) {
		List <Cell> cellList = super.addNeighbours(x,y,cellsGrid, board);
		if(board.getBoundaryConditionComboBox().getValue().equals("Periodic")) {
			cellList.add(cellsGrid[periodicBoundaryConverter(y - 1,cellsGrid.length)]
					[periodicBoundaryConverter(x + 1,cellsGrid[0].length)]);
			cellList.add(cellsGrid[periodicBoundaryConverter(y + 1,cellsGrid.length)]
					[periodicBoundaryConverter(x - 1,cellsGrid[0].length)]);
		}
		else{
			cellList.add(cellsGrid[y - 1][x + 1]);
			cellList.add(cellsGrid[y + 1][x - 1]);
		}
		return cellList;
	}
}
