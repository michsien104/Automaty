package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

public class Neighbourhood1D implements CellNeighbourhood {
	private boolean wrapping;
	private int radious;
	private int width;

	public Neighbourhood1D(boolean wrapping, int radious, int width) {
		super();
		this.wrapping = wrapping;
		this.radious = radious;
		this.width = width;
	}

	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates coords) {
		Coords1D givenCoordinates = (Coords1D) coords;
		Set<CellCoordinates> setOfCellCoordinates = new HashSet<CellCoordinates>();
		
		for (int i = givenCoordinates.getXCoord() - radious; i < givenCoordinates.getXCoord() + radious; i++) {
			// warunek na sprawdzanie czy nie jest to podana komórka
			if (i != givenCoordinates.getXCoord()) {
				int cellPos = i;
				// z zawijaniem
				if (wrapping == true) {
					if (cellPos < 1) {
						cellPos += width;
					} else if (cellPos > width) {
						cellPos -= width;
					}
					setOfCellCoordinates.add(new Coords1D(cellPos));
				} else {
					if ((cellPos > 0) && (cellPos <= width)) {
						setOfCellCoordinates.add(new Coords1D(cellPos));
					}
				}
			}
		}
		return setOfCellCoordinates;
	}

}
