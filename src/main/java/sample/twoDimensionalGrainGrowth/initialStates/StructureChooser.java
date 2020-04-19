package sample.twoDimensionalGrainGrowth.initialStates;

import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.HashMap;
import java.util.Map;

public class StructureChooser {

	private static final Map<String,InitialStructure> structureMap = new HashMap<>();
	static{
		structureMap.put("Homogeneous",new HomogeneousStructure());
		structureMap.put("With Radius", new RandomWithRadiusStructure());
		structureMap.put("Random", new RandomStructure());
		structureMap.put("Own", new OwnStructure());
	}
	public void changeFields(String structure, Board board){
		structureMap.get(structure).changeFields(board);
	}
	public void drawStructure(String structure, Cell[][] cellsMatrix, Board board){
		structureMap.get(structure).draw(cellsMatrix,board);
	}
}
