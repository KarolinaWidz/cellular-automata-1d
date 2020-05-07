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
	private Color color;
	private int energy;

	Cell(CellState state, int x, int y, int size) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.size= size;
		this.color = Color.WHITE;
		this.energy = 8;
		this.rectangle = new Rectangle(size,size,this.color);
		this.rectangle.setOnMouseClicked(event -> {
			if(this.state==CellState.DEAD) setAlive();
			else setDead();
		});
	}

	public Cell(Cell copy) {
		this.state = copy.state;
		this.x = copy.x;
		this.y = copy.y;
		this.size= copy.size;
		this.color = copy.color;
		this.energy = copy.energy;
		this.rectangle = new Rectangle(this.size,this.size,this.color);
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

	void copyState(Cell cell){
		this.state = cell.state;
		this.color = cell.color;
		this.rectangle.setFill(this.color);
	}

	public void setDead() {
		this.state = CellState.DEAD;
		this.rectangle.setFill(Color.WHITE);
	}
}
