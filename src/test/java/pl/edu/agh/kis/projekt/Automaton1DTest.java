package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class Automaton1DTest {

	@Test
	public void nextCellStateTestA() {
		//test umierania komórki
		Automaton1D game = new Automaton1D(3, 30);
		//komorka o koordynatach 5,10
		Coords1D newCoords = new Coords1D(2);
		CellState cellState = BinaryState.ALIVE;
		Set<Cell> setOfCells = new HashSet<Cell>();
		Cell newCell = new Cell(cellState, newCoords);
		
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords1D(1)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords1D(3)));
		
		CellState resultCellState = game.nextCellState(newCell, setOfCells);
		
		Assert.assertEquals("same object",BinaryState.DEAD, resultCellState);
	}
}
