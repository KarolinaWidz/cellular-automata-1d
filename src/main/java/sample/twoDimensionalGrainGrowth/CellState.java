package sample.twoDimensionalGrainGrowth;

import lombok.Getter;

@Getter
public enum CellState {
	DEAD(false),
	ALIVE(true);

	private final Boolean flag;

	CellState(Boolean flag){
		this.flag = flag;
	}




}