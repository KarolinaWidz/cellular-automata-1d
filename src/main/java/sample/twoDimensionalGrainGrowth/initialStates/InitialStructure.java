package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;
import sample.twoDimensionalGrainGrowth.Controller2d;

import java.util.regex.Pattern;

public interface InitialStructure {
	default int checkValues(int condition, String size){
		boolean flag=false;
		if(!size.equals(""))
			if(Pattern.matches("^\\d*$",size) &&(Integer.parseInt(size))>0 && (Integer.parseInt(size))<= condition)
				flag=true;
		return Controller2d.parseValue(flag,size,1);
	}
	void draw(Cell[][] cellsMatrix, Board board);
	void changeFields(Board board);
}
