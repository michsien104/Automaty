package pl.edu.agh.kis.projekt;

/**
* Interfejs implementujacy klasy zwracajace stany kom�rek
* 
*/

public interface CellStateFactory {
	public CellState initialState(CellCoordinates coords);
}
