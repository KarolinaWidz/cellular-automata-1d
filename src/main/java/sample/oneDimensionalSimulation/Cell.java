package sample.oneDimensionalSimulation;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Cell {
	DEAD(false,Color.PALEGREEN),
	ALIVE(true,Color.DARKMAGENTA);

	private final Color color;
	private final Boolean flag;

	Cell(Boolean flag,Color color) {
		this.flag = flag;
		this.color = color;
	}
}
