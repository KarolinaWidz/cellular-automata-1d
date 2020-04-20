package sample.twoDimensionalGrainGrowth.neighbourTypes;

import sample.twoDimensionalGrainGrowth.Cell;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeighbourChooser {
	private static final Map<String, Neighbour> neighbourMap = new HashMap<>();
	static{
		neighbourMap.put("Von Neumann",new VonNeumann());
		neighbourMap.put("Moore", new Moore());
		neighbourMap.put("Hexagonal", new Hexagonal());
		neighbourMap.put("Pentagonal", new Pentagonal());
//		neighbourMap.put("With Radius", new OwnStructure());
	}

	private String type;
	private String boundaryCondition;

	public NeighbourChooser(String type, String boundaryCondition) {
		this.type = type;
		this.boundaryCondition = boundaryCondition;
	}

	public List<Cell> addNeighbours(int x, int y,Cell[][] cellsGrid){
		return neighbourMap.get(this.type).addNeighbours(x,y,cellsGrid,this.boundaryCondition);
	}
}
