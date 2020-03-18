package sample;

import javafx.scene.paint.Color;

public enum Cell {
	DEAD(Color.WHITE,false),
	ALIVE(Color.PALEGREEN,true);

	private final Color color;
	private final Boolean flag;

	Cell(Color color, Boolean flag) {
		this.color = color;
		this.flag = flag;
	}

	public Color getColor() {
		return color;
	}

	public Boolean getFlag() {
		return flag;
	}
}
