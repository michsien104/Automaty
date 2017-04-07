package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class AutomatonTest {

	@Test
	public void automatonTest() {
		String rule = "23/3";
		// mapa poczatkowa
		Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();
		mapOfCells.put(new Coords2D(1, 1), BinaryState.DEAD);
		mapOfCells.put(new Coords2D(1, 2), BinaryState.ALIVE);
		mapOfCells.put(new Coords2D(1, 3), BinaryState.DEAD);
		mapOfCells.put(new Coords2D(2, 1), BinaryState.DEAD);
		mapOfCells.put(new Coords2D(2, 2), BinaryState.ALIVE);
		mapOfCells.put(new Coords2D(2, 3), BinaryState.DEAD);
		mapOfCells.put(new Coords2D(3, 1), BinaryState.DEAD);
		mapOfCells.put(new Coords2D(3, 2), BinaryState.ALIVE);
		mapOfCells.put(new Coords2D(3, 3), BinaryState.DEAD);
		Automaton aut = new GameOfLife(mapOfCells, new MoorNeighbourhood(3, 3, false, 1),
				new GeneralStateFactory(mapOfCells), rule, 3, 3);

		aut = aut.nextState();
		//System.out.println(aut.getCells().size());
		
		// mapa po zaaktualizowaniu komorek
		Map<CellCoordinates, CellState> mapOfCells2 = new HashMap<CellCoordinates, CellState>();
		mapOfCells2.put(new Coords2D(1, 1), BinaryState.DEAD);
		mapOfCells2.put(new Coords2D(1, 2), BinaryState.DEAD);
		mapOfCells2.put(new Coords2D(1, 3), BinaryState.DEAD);
		mapOfCells2.put(new Coords2D(2, 1), BinaryState.ALIVE);
		mapOfCells2.put(new Coords2D(2, 2), BinaryState.ALIVE);
		mapOfCells2.put(new Coords2D(2, 3), BinaryState.ALIVE);
		mapOfCells2.put(new Coords2D(3, 1), BinaryState.DEAD);
		mapOfCells2.put(new Coords2D(3, 2), BinaryState.DEAD);
		mapOfCells2.put(new Coords2D(3, 3), BinaryState.DEAD);

		Assert.assertEquals("same object", mapOfCells2, aut.getCells() );
	}
}
