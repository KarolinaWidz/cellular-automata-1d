package sample.twoDimensionalGrainGrowth.initialStates;

import javafx.scene.control.Alert;
import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.regex.Pattern;

public interface InitialStructure {
	default int checkValues(int condition,String text){
		if(Pattern.matches("^\\d*$",text) &&(Integer.parseInt(text))>0 && (Integer.parseInt(text))<= condition)
		{
			return Integer.parseInt(text);
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
				return 1;
			}
		}
	}
	void draw(Cell[][] cellsMatrix, Board board);
	void changeFields(Board board);
}
