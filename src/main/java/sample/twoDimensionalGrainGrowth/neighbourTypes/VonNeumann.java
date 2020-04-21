package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import java.util.List;

public class VonNeumann implements Neighbour {

	@Override
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid, Board board) {
		cellList.clear();
		int prevX = x-1;
		int prevY = y-1;
		int nextX = x+1;
		int nextY = y+1;

		if(board.getBoundaryConditionComboBox().getValue().equals("Periodic")){
			prevX = periodicBoundaryConverter(x-1,cellsGrid[0].length);
			prevY = periodicBoundaryConverter(y-1,cellsGrid.length);
			nextX = periodicBoundaryConverter(x+1,cellsGrid[0].length);
			nextY = periodicBoundaryConverter(y+1,cellsGrid.length);
		}
		cellList.add(cellsGrid[prevY][x]);
		cellList.add(cellsGrid[nextY][x]);
		cellList.add(cellsGrid[y][nextX]);
		cellList.add(cellsGrid[y][prevX]);
		return cellList;
	}
}
