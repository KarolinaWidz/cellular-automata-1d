package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Cell;
import java.util.List;

public class VonNeumann implements Neighbour {

	@Override
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid) {
		cellList.clear();
		cellList.add(cellsGrid[y - 1][x]);
		cellList.add(cellsGrid[y + 1][x]);
		cellList.add(cellsGrid[y][x + 1]);
		cellList.add(cellsGrid[y][x - 1]);
		return cellList;
	}
}
