package sample.oneDimensionalSimulation;

class ActiveCells {
	Cell right;
	Cell center;
	Cell left;

	ActiveCells(Cell left, Cell center, Cell right){
		this.right=right;
		this.center=center;
		this.left=left;
	}
}
