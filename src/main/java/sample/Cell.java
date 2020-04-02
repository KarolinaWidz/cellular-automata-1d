package sample;

import javafx.scene.paint.Color;

public enum Cell {
	DEAD(false,Color.PALEGREEN),
	ALIVE(true,Color.DARKMAGENTA);

	private final Color color;
	private final Boolean flag;

	Cell(Boolean flag,Color color) {
		this.flag = flag;
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public Boolean getFlag() {
		return this.flag;
	}
}
