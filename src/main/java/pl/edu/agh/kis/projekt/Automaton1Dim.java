package pl.edu.agh.kis.projekt;

import java.util.Map;

/**
* Klasa obs�uguj�ca automaty jednowymiarowe
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
	* Metoda sprawdzaj�ca czy automat ma kolejn� kom�rke
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
	* Metoda zwracajaca koordynaty przed pierwsza kom�rka
	* 
	*/
	
	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords1D(0);
	}

	/**
	* Metoda zwracaj�ca nastepn� kom�rke
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
