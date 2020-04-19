package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import java.util.Random;

public class RandomStructure implements InitialStructure {

	@Override
	public void draw(Cell[][] cellsMatrix, Board board ) {
		int amount = checkValues(cellsMatrix.length*cellsMatrix[0].length,board.getChangeableFirstField().getText());
		Random generator = new Random();
		for(int i=0;i<amount;i++){
			cellsMatrix[generator.nextInt(cellsMatrix.length)][generator.nextInt(cellsMatrix[0].length)].setAlive();
		}
	}

	@Override
	public void changeFields(Board board) {
		board.getChangeableFirstLabel().setText("Amount: ");
		board.getChangeableSecondLabel().setText("");
		board.getChangeableFirstField().setVisible(true);
		board.getChangeableSecondField().setVisible(false);
	}
}
