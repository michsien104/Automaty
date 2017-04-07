package pl.edu.agh.kis.projekt;

import java.util.Map;

/**
 * Klasa obs³uguj¹ca automaty dwuwymiarowe
 * 
 */

public abstract class Automaton2Dim extends Automaton {
	private int width;
	private int height;

	public Automaton2Dim() {

	}

	public Automaton2Dim(int x, int y) {
		super();
		this.width = x;
		this.height = y;
	}

	public Automaton2Dim(Map<CellCoordinates, CellState> mapOfCells, CellNeighbourhood neighbourhoodStrategy,
			CellStateFactory factory, int x, int y) {
		super(mapOfCells, neighbourhoodStrategy, factory);
		this.width = x;
		this.height = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int x) {
		this.width = x;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int y) {
		this.height = y;
	}

	/**
	 * Metoda sprawdzaj¹ca czy automat ma kolejn¹ komórke
	 * 
	 */
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coord) {
		Coords2D parseCoord = (Coords2D) coord;

		if (parseCoord.getXCoord() < this.width) {
			return true;
		} else if ((parseCoord.getXCoord() == this.width) && (parseCoord.getYCoord() + 1 <= this.height))
			return true;
		else
			return false;
	}

	/**
	 * Metoda zwracajaca koordynaty przed pierwsza komórka
	 * 
	 */

	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords2D(0, 1);
	}

	/**
	 * Metoda zwracaj¹ca nastepn¹ komórke
	 * 
	 */
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coord) {
		Coords2D parseCoord = (Coords2D) coord;

		if (parseCoord.getXCoord() < width) {
			return new Coords2D(parseCoord.getXCoord() + 1, parseCoord.getYCoord());
		} else {
			return new Coords2D(1, parseCoord.getYCoord() + 1);
		}

	}
}
