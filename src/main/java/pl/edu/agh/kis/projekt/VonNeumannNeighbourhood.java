package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa zwracaj¹ca s¹siedztwo Von Neumanna dla danej komórki
 * 
 * @author Micha³ Sieñczak
 */

public class VonNeumannNeighbourhood implements CellNeighbourhood {
	private int radious;
	private boolean wrapping;
	private int width;
	private int height;

	public VonNeumannNeighbourhood(int height, int width, boolean wrap, int rad) {
		this.radious = rad;
		this.wrapping = wrap;
		this.height = height;
		this.width = width;
	}


	/**
	 * Metoda zwracaj¹ca s¹siedztwo
	 * 
	 */
	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates coords) {
		Set<CellCoordinates> setOfNeighbours = new HashSet<CellCoordinates>();
		Coords2D centralCell = (Coords2D) coords;

		// warunki na znalezienie s¹siedztwa
		//pêtla dla komórek o tej samej wspolrzednej X co centralna
		for (int i = centralCell.getXCoord() - radious; i <= centralCell.getXCoord() + radious; i++) {
			int cellXPos = i;
			int cellYPos = centralCell.getYCoord();
			
			// warunek zeby nie przypisaæ komórki od której szukamy s¹siedztwa
			if ((cellXPos != centralCell.getXCoord()) || (cellYPos != centralCell.getYCoord())) {
				
				// z zawijaniem
				if (wrapping == true) {
					
					// je¿eli komórka wyjdzie poza szerokoœæ
					if (cellXPos < 1) {
						cellXPos = cellXPos + width;
					} else if (cellXPos > width) {
						cellXPos = cellXPos - width;
					}
					
					setOfNeighbours.add(new Coords2D(cellXPos, cellYPos));
					
				// bez zawijania
				}else {
					if ((cellXPos > 0) && (cellXPos <= width) && (cellYPos > 0) && (cellYPos <= height))
						setOfNeighbours.add(new Coords2D(cellXPos, cellYPos));
				}
			}
			
			//pêtla dla komórek o tej samej wspolrzednej Y co centralna
			for (int j = centralCell.getYCoord() - radious; j <= centralCell.getYCoord() + radious; j++) {
				int cellXPos2 = centralCell.getXCoord();
				int cellYPos2 = j;
				// warunki na znalezienie s¹siedztwa
				// warunek zeby nie przypisaæ komórki od której szukamy s¹siedztwa
				if ((cellXPos2 != centralCell.getXCoord()) || (cellYPos2 != centralCell.getYCoord())) {

					// z zawijaniem
					if (wrapping == true) {

						// jezeli komórka wyjdzie poza d³ugoœæ
						if (cellYPos2 < 1) {
							cellYPos2 = cellYPos2 + height;
						} else if (cellYPos2 > height) {
							cellYPos2 = cellYPos2 - height;
						}

						setOfNeighbours.add(new Coords2D(cellXPos2, cellYPos2));

						// bez zawijania
					} else {
						if ((cellXPos2 > 0) && (cellXPos2 <= width) && (cellYPos2 > 0) && (cellYPos2 <= height))
							setOfNeighbours.add(new Coords2D(cellXPos2, cellYPos2));
					}

				}
			}
		}

		return setOfNeighbours;
	}

}