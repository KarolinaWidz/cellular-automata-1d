package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

public class HomogeneousStructure implements InitialStructure{

	@Override
	public void draw(Cell[][] cellsMatrix, Board board) {
		int column = checkValues(cellsMatrix[0].length,board.getChangeableFirstField().getText());
		int row = checkValues(cellsMatrix.length,board.getChangeableSecondField().getText());
		int yStep = cellsMatrix.length/row;
		int xStep = cellsMatrix[0].length/column;
		for(int y = yStep/2; y<cellsMatrix.length-(yStep/2); y+=yStep){
			for(int x = xStep/2; x<cellsMatrix[0].length-(xStep/2); x+=xStep){
				cellsMatrix[y][x].setAlive();
			}
		}
	}

	@Override
	public void changeFields(Board board) {
		board.getChangeableFirstLabel().setText("~Amount in column: ");
		board.getChangeableSecondLabel().setText("~Amount in row: ");
		board.getChangeableFirstField().setVisible(true);
		board.getChangeableSecondField().setVisible(true);
	}

}
