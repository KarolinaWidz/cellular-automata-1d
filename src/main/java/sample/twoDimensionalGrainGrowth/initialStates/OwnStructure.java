package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

public class OwnStructure implements InitialStructure {

	@Override
	public void draw(Cell[][] cellsMatrix, Board board) {
		for (Cell[] matrix : cellsMatrix) {
			for (int x = 0; x < cellsMatrix[0].length; x++) {
				matrix[x].setDead();
			}
		}
	}

	@Override
	public void changeFields(Board board) {
		board.getChangeableFirstLabel().setText("");
		board.getChangeableSecondLabel().setText("");
		board.getChangeableFirstField().setVisible(false);
		board.getChangeableSecondField().setVisible(false);
	}
}
