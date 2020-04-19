package sample.twoDimensionalGrainGrowth.neighbourTypes;

import javafx.util.Pair;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.ArrayList;
import java.util.List;

public interface Neighbour {
	List<Cell> cellList = new ArrayList<>();
	List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid);
}
