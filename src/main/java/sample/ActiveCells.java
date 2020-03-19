package sample;

public class ActiveCells {
	Cell right;
	Cell center;
	Cell left;
	Cell nextCell;

	ActiveCells(Cell right, Cell center, Cell left){
		this.right=right;
		this.center=center;
		this.left=left;
	}
}
