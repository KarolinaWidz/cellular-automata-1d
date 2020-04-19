package sample.twoDimensionalGrainGrowth;

import javafx.scene.shape.Rectangle;
import lombok.Getter;

@Getter
class Cell {
	private int size;
	private CellState state;
	private Rectangle rectangle;
	private int x;
	private int y;

	Cell(CellState state, int x, int y, int size) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.size= size;
		this.rectangle = new Rectangle(size,size,state.getColor());
		this.rectangle.setOnMouseClicked(event -> {
			if(this.state==CellState.DEAD) setState(CellState.ALIVE);
			else setState(CellState.DEAD);
		});
	}

	Cell (Cell copy) {
		this.state = copy.state;
		this.x = copy.x;
		this.y = copy.y;
		this.size= copy.size;
		this.rectangle = new Rectangle(this.size,this.size,this.state.getColor());
		this.rectangle.setOnMouseClicked(event -> {
			if(this.state==CellState.DEAD) setState(CellState.ALIVE);
			else setState(CellState.DEAD);
		});

	}

	private void setState(CellState state) {
		this.state = state;
		this.rectangle.setFill(state.getColor());
	}
}
