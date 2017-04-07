package pl.edu.agh.kis.projekt;

import java.util.Map;

/**
* Klasa obs³uguj¹ca automaty jednowymiarowe
* 
*/
public abstract class Automaton1Dim extends Automaton {
	private int width;

	public Automaton1Dim(int width) {
		super();
		this.width = width;
	}

	public Automaton1Dim(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy,
			Map<CellCoordinates, CellState> mapOfCells, int width) {
		super(mapOfCells, neighbourhoodStrategy, factory);
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	/**
	* Metoda sprawdzaj¹ca czy automat ma kolejn¹ komórke
	* 
	*/
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coord) {
		Coords1D parseCoord = (Coords1D) coord;

		if ((parseCoord.getXCoord() + 1) <= this.width) {
			return true;
		} else
			return false;
	}

	/**
	* Metoda zwracajaca koordynaty przed pierwsza komórka
	* 
	*/
	
	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords1D(0);
	}

	/**
	* Metoda zwracaj¹ca nastepn¹ komórke
	* 
	*/
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coord) {
		Coords1D parseCoord = (Coords1D) coord;
		if (hasNextCoordinates(coord)) {
			Coords1D next = new Coords1D(parseCoord.getXCoord() + 1);
			return next;
		} else
			return null;
	}
}
