package pl.edu.agh.kis.projekt;

/** 
 * Klasa przechowujaca stan komórki
 * @author Micha³ Sieñczak
 *
 */

public class Cell {
	private final CellState state;
	private final CellCoordinates coords;
	
	public Cell(CellState state, CellCoordinates coords){
		this.state = state;
		this.coords = coords;
	}
	
	public CellState getCellState(){
		return this.state;
	}
	public CellCoordinates getCellCoordinates(){
		return this.coords;
	}
}
