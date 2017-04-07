package pl.edu.agh.kis.projekt;

/**
* Interfejs implementujacy klasy zwracajace stany komórek
* 
*/

public interface CellStateFactory {
	public CellState initialState(CellCoordinates coords);
}
