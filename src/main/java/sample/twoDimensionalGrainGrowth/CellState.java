package sample.twoDimensionalGrainGrowth;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum CellState {
	DEAD(false, Color.WHITE),
	ALIVE(true,Color.ROYALBLUE);

	private final Color color;
	private final Boolean flag;

	CellState(Boolean flag, Color color){
		this.flag = flag;
		this.color = color;
	}




}