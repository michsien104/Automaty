package pl.edu.agh.kis.projekt;

/*
 * Klasa przyjmuj¹ca stan komórki i zwracj¹ca go 
 * 
 * 
 */

public class UniformStateFactory implements CellStateFactory{
	
	private CellState cellState;
	

	public UniformStateFactory(CellState cellState) {
		super();
		this.cellState = cellState;
	}
	
	@Override
	public CellState initialState(CellCoordinates coords){
		return cellState;
	}
}
