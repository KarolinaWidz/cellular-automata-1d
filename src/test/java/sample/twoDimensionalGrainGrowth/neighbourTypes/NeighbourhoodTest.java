package sample.twoDimensionalGrainGrowth.neighbourTypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.TestFXBase;
import sample.twoDimensionalGrainGrowth.Board;
import sample.twoDimensionalGrainGrowth.Cell;

import java.util.List;

class NeighbourhoodTest extends TestFXBase {

	private Neighbourhood neighbourhood;
	private  List<Cell>  cellList;

	@BeforeEach
	void setUp() {
		clickOn("#tab2D");
		clickOn("#neighbourhoodComboBox");
	}

	@Test
	void vonNeumannListSizeTest() {

		this.neighbourhood = new VonNeumann();
		clickOn("Von Neumann");
		this.cellList = this.neighbourhood.addNeighbours(2, 2, new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(4, cellList.size());
	}

	@Test
	void mooreListSizeTest() {
		this.neighbourhood = new Moore();
		clickOn("Moore");
		this.cellList=this.neighbourhood.addNeighbours(2,2,new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(8,cellList.size());
	}

	@Test
	void hexagonalRandomListSizeTest() {
		this.neighbourhood = new HexagonalRandom();
		clickOn("Hexagonal - Random");
		this.cellList=this.neighbourhood.addNeighbours(2,2,new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(6,cellList.size());
	}

	@Test
	void hexagonalLeftListSizeTest() {
		this.neighbourhood = new HexagonalRandom();
		clickOn("Hexagonal - Left");
		this.cellList=this.neighbourhood.addNeighbours(2,2,new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(6,cellList.size());
	}

	@Test
	void hexagonalRightListSizeTest() {
		this.neighbourhood = new HexagonalRandom();
		clickOn("Hexagonal - Right");
		this.cellList=this.neighbourhood.addNeighbours(2,2,new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(6,cellList.size());
	}

	@Test
	void pentagonalListSizeTest() {
		this.neighbourhood = new Pentagonal();
		clickOn("Pentagonal");
		this.cellList=this.neighbourhood.addNeighbours(2,2,new Cell[5][5], Board.getInstance());
		Assertions.assertEquals(5,cellList.size());
	}
}