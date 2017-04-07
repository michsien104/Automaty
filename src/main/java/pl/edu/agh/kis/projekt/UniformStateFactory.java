package pl.edu.agh.kis.projekt;

/*
 * Klasa przyjmuj�ca stan kom�rki i zwracj�ca go 
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
