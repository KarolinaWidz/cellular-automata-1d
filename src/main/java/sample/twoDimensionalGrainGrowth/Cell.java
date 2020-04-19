package sample.twoDimensionalGrainGrowth;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
	private int size;
	private CellState state;
	private Rectangle rectangle;
	private int x;
	private int y;
	private int id;
	private Color color;

	Cell(CellState state, int x, int y, int size, int id) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.size= size;
		this.id = id;
		this.color = CellState.DEAD.getColor();
		this.rectangle = new Rectangle(size,size,state.getColor());
		this.rectangle.setOnMouseClicked(event -> {
			if(this.state==CellState.DEAD) setAlive();
			else setDead();
		});
	}

	Cell (Cell copy) {
		this.state = copy.state;
		this.x = copy.x;
		this.y = copy.y;
		this.size= copy.size;
		this.id = copy.id;
		this.rectangle = new Rectangle(this.size,this.size,this.state.getColor());
		this.rectangle.setOnMouseClicked(event -> {
			if(this.state==CellState.DEAD) setAlive();
			else setDead();
		});

	}

	public void setAlive(){
		this.state = CellState.ALIVE;
		this.color = Color.color(Math.random(), Math.random(), Math.random());
		this.rectangle.setFill(this.color);
	}

	public void setDead() {
		this.state = CellState.DEAD;
		this.rectangle.setFill(CellState.DEAD.getColor());
	}

	public void copyState(Cell cell) {
		this.state = cell.state;
		this.rectangle.setFill(cell.getColor());
	}
}
