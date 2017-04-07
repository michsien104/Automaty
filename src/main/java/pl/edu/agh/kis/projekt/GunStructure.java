package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;


/**
 * Klasa zwracaj¹ca strukturê typu Gun
 * 
 * @author Micha³ Sieñczak
 */

public class GunStructure {
private Map<CellCoordinates, CellState> mapOfStructure = new HashMap<CellCoordinates, CellState>();
	
	public GunStructure(int x, int y, int height, int width){
		if (x + 38 <= width && y + 8 < height) {
			mapOfStructure.put(new Coords2D(x, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 1, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x+1, y + 3), BinaryState.ALIVE);
			
			mapOfStructure.put(new Coords2D(x + 10, y + 2), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 10, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 10, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 11, y + 1), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 11, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 12, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 13, y), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 12, y + 6), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 13, y + 6), BinaryState.ALIVE);
			
			mapOfStructure.put(new Coords2D(x + 14, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 15, y + 1), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 15, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 16, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 16, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 16, y + 2), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 17, y + 3), BinaryState.ALIVE);
			
			mapOfStructure.put(new Coords2D(x + 20, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 20, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 20, y + 6), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 21, y + 4), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 21, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 21, y + 6), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 22, y + 7), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 22, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 24, y + 3), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 24, y + 2), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 24, y + 7), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 24, y + 8), BinaryState.ALIVE);
			
			mapOfStructure.put(new Coords2D(x + 34, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 34, y + 6), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 35, y + 5), BinaryState.ALIVE);
			mapOfStructure.put(new Coords2D(x + 35, y + 6), BinaryState.ALIVE);
			
		
			
		}
	}

	public Map<CellCoordinates, CellState> getMapOfStructure() {
		return mapOfStructure;
	}
}
