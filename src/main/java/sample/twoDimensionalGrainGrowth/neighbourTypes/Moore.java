package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import java.util.List;

public class Moore implements Neighbour {

	@Override
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid, Board board) {
		cellList.clear();
		for(int i=-1; i<2;i++){
			for(int j=-1;j<2;j++)
				if(!(i==0 && j==0))
					if(board.getBoundaryConditionComboBox().getValue().equals("Periodic"))
						cellList.add(cellsGrid[periodicBoundaryConverter(y+i,cellsGrid.length)]
								[periodicBoundaryConverter(x+j,cellsGrid[0].length)]);
					else
						cellList.add(cellsGrid[y+i][x+j]);
		}
		return cellList;
	}
}
