package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import sample.twoDimensionalGrainGrowth.Controller2d;

import java.util.regex.Pattern;

public interface InitialStructure {
	default int checkValues(int condition,String text){
		if(!text.equals("")){
			if(Pattern.matches("^\\d*$",text) &&(Integer.parseInt(text))>0 && (Integer.parseInt(text))<= condition)
				return Integer.parseInt(text);
			else{
				Controller2d.createErrorAlert();
				return 1;
			}
		}
		else {
			Controller2d.createErrorAlert();
			return 1;
		}
	}
	void draw(Cell[][] cellsMatrix, Board board);
	void changeFields(Board board);
}
