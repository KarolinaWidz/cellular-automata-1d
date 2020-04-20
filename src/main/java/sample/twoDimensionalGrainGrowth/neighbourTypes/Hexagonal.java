package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Cell;
import java.util.List;
import java.util.Random;

public class Hexagonal extends VonNeumann{
	public List<Cell> addNeighbours(int x, int y, Cell[][] cellsGrid,String boundaryCondition) {
		List <Cell> cellList = super.addNeighbours(x,y,cellsGrid, boundaryCondition);
		int generatedX = valueGenerator();
		int generatedY = valueGenerator();
		if(boundaryCondition.equals("Periodic")) {
			cellList.add(cellsGrid[periodicBoundaryConverter(y + generatedY,cellsGrid.length)]
					[periodicBoundaryConverter(x + generatedX,cellsGrid[0].length)]);
			cellList.add(cellsGrid[periodicBoundaryConverter(y - generatedY,cellsGrid.length)]
					[periodicBoundaryConverter(x - generatedX,cellsGrid[0].length)]);
		}
		else{
			cellList.add(cellsGrid[y + generatedY][x + generatedX]);
			cellList.add(cellsGrid[y - generatedY][x - generatedX]);
		}
		return cellList;
	}

	private int valueGenerator(){
		Random generator = new Random();
		return generator.nextInt(2)==1 ? 1 : -1;
	}
}
