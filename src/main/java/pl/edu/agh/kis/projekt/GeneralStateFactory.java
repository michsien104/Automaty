package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;

/*
 * Klasa przyjmuj�ca map� kom�rek i zwracaj�ca stan kom�rki o danych koordynatach
 * 
 * 
 */

public class GeneralStateFactory implements CellStateFactory {

	private Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();

	public GeneralStateFactory(Map<CellCoordinates, CellState> stateMap) {
		states.putAll(stateMap);
	}

	@Override
	public CellState initialState(CellCoordinates coords) {
		return states.get(coords);
	}
}
