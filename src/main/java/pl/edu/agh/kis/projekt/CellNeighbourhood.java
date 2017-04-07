package pl.edu.agh.kis.projekt;

import java.util.Set;

/** 
 * Interfejs zwracajacy s�siad�w kom�rki
 * 
 * @author Micha� Sie�czak
 */

public interface CellNeighbourhood {

	public Set<CellCoordinates> cellNeighbours(CellCoordinates coords);

}