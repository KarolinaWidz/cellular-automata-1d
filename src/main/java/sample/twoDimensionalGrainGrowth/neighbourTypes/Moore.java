package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Cell;
import java.util.List;

public class Moore implements Neighbour {
	@Override
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid) {
		cellList.clear();
		for(int i=0; i<3;i++){
			for(int j=0;j<3;j++)
				if(!(i==1 && j==1))
					cellList.add(cellsGrid[i][j]);
		}
		return cellList;
	}
}
