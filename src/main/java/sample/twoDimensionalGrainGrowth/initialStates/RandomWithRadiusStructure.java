package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import java.util.Random;

public class RandomWithRadiusStructure implements InitialStructure {
	@Override
	public void draw(Cell[][] cellsMatrix, Board board) {
		int amount = checkValues(cellsMatrix.length*cellsMatrix[0].length,board.getChangeableFirstField().getText());
		int radius = checkValues(board.getBOARD_SIZE(),board.getChangeableSecondField().getText());
		Random generator = new Random();
		int generatedX;
		int generatedY;
		boolean flag = false;
		for(int i=0;i<amount;i++){
			generatedX=generator.nextInt(cellsMatrix[0].length);
			generatedY=generator.nextInt(cellsMatrix.length);
			for(int y = startChecker(generatedY,radius); y<=endChecker(generatedY,radius,cellsMatrix.length); y++){
				for(int x = startChecker(generatedX,radius); x<=endChecker(generatedX,radius,cellsMatrix[0].length); x++) {
					if(cellsMatrix[y][x].getState().getFlag()) {
						flag = true;
						break;
					}
				}
			}
			if(!flag) cellsMatrix[generatedY][generatedX].setAlive();
			flag=false;
		}
	}

	private int startChecker(int value, int radius){
		return (value-radius)<0 ? 0:value-radius;
	}

	private int endChecker(int value, int radius,int boundary){
		return (value+radius)<boundary ? value+radius : boundary-1;
	}
	@Override
	public void changeFields(Board board) {
		board.getChangeableFirstLabel().setText("Amount: ");
		board.getChangeableSecondLabel().setText("Radius: ");
		board.getChangeableFirstField().setVisible(true);
		board.getChangeableSecondField().setVisible(true);
	}
}
