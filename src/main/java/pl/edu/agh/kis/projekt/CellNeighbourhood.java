package pl.edu.agh.kis.projekt;

import java.util.Set;

/** 
 * Interfejs zwracajacy s¹siadów komórki
 * 
 * @author Micha³ Sieñczak
 */

public interface CellNeighbourhood {

	public Set<CellCoordinates> cellNeighbours(CellCoordinates coords);

}