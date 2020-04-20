package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Cell;
import java.util.ArrayList;
import java.util.List;

public interface Neighbour {
	default int periodicBoundaryConverter(int x, int boundary){
		return x<0?boundary-1 : x%boundary;
	}
	List<Cell> cellList = new ArrayList<>();
	List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid,String boundaryCondition);
}
