package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa zwracaj�ca s�siedztwo Moora dla danej kom�rki
 * 
 * @author Micha� Sie�czak
 */

public class MoorNeighbourhood implements CellNeighbourhood {
	private int radious;
	private boolean wrapping;
	private int width;
	private int height;

	public MoorNeighbourhood(int height, int width, boolean wrap, int rad) {
		this.radious = rad;
		this.wrapping = wrap;
		this.height = height;
		this.width = width;
	}

	/**
	 * Metoda zwracaj�ca s�siedztwo
	 * 
	 */
	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates coords) {

		Set<CellCoordinates> setOfNeighbours = new HashSet<CellCoordinates>();
		Coords2D centralCell = (Coords2D) coords;

		for (int i = centralCell.getXCoord() - radious; i <= centralCell.getXCoord() + radious; i++) {
			for (int j = centralCell.getYCoord() - radious; j <= centralCell.getYCoord() + radious; j++) {
				int cellXPos = i;
				int cellYPos = j;
				// warunki na znalezienie s�siedztwa
				// warunek zeby nie przypisa� kom�rki od kt�rej szukamy
				// s�siedztwo
				if ((cellXPos != centralCell.getXCoord()) || (cellYPos != centralCell.getYCoord())) {
				// z zawijaniem
				if (wrapping) {

					// je�eli kom�rka wyjdzie poza szeroko��
					if (cellXPos < 1) {
						cellXPos = cellXPos + width;
					} else if (cellXPos > width) {
						cellXPos = cellXPos - width;
					}

					// jezeli kom�rka wyjdzie poza d�ugo��
					if (cellYPos < 1) {
						cellYPos = cellYPos + height;
					} else if (cellYPos > height) {
						cellYPos = cellYPos - height;
					}

					setOfNeighbours.add(new Coords2D(cellXPos, cellYPos));

					// bez zawijania
				} else {
					if ((cellXPos > 0) && (cellXPos <= width) && (cellYPos > 0) && (cellYPos <= height))
						setOfNeighbours.add(new Coords2D(cellXPos, cellYPos));
				}

			}
		}}

		return setOfNeighbours;
	}

}
