package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zwracaj¹ca strukturê typu Glider
 * 
 * @author Micha³ Sieñczak
 */
public class GliderStructure {
	private Map<CellCoordinates, CellState> mapOfStructure = new HashMap<CellCoordinates, CellState>();
	
	public GliderStructure(int x, int y, int height, int width){
		if (x + 2 <= width && y + 2 <= height) {
			mapOfStructure.put(new Coords2D(x, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 1, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 2, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x, y + 1), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 1, y + 2), BinaryState.ALIVE);
		}
	}

	public Map<CellCoordinates, CellState> getMapOfStructure() {
		return mapOfStructure;
	}
}
