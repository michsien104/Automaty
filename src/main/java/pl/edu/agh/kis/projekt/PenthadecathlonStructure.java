package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zwracaj¹ca strukturê typu penthadecathlon
 * 
 * @author Micha³ Sieñczak
 */
public class PenthadecathlonStructure {
private Map<CellCoordinates, CellState> mapOfStructure = new HashMap<CellCoordinates, CellState>();
	
	public PenthadecathlonStructure(int x, int y, int width){
		if (x + 10 <= width ) {
			mapOfStructure.put(new Coords2D(x, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 1, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 2, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 3, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 4, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 5, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 6, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 7, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 8, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 9, y), BinaryState.ALIVE);
		}
	}

	public Map<CellCoordinates, CellState> getMapOfStructure() {
		return mapOfStructure;
	}
}
