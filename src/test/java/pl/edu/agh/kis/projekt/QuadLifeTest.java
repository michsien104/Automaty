package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class QuadLifeTest {

	@Test
	public void quadLifeNextCellStateTest() {
		QuadLife game = new QuadLife();
		// komorka o koordynatach 2,2
		Coords2D newCoords = new Coords2D(2, 2);
		CellState cellState = QuadState.RED;
		Set<Cell> setOfCells = new HashSet<Cell>();
		Cell newCell = new Cell(cellState, newCoords);

		setOfCells.add(new Cell(QuadState.DEAD, new Coords2D(1, 1)));
		setOfCells.add(new Cell(QuadState.DEAD, new Coords2D(1, 2)));
		setOfCells.add(new Cell(QuadState.DEAD, new Coords2D(1, 3)));
		setOfCells.add(new Cell(QuadState.DEAD, new Coords2D(2, 1)));
		setOfCells.add(new Cell(QuadState.DEAD, new Coords2D(2, 3)));
		setOfCells.add(new Cell(QuadState.YELLOW, new Coords2D(3, 1)));
		setOfCells.add(new Cell(QuadState.YELLOW, new Coords2D(3, 2)));
		setOfCells.add(new Cell(QuadState.YELLOW, new Coords2D(3, 3)));

		CellState resultCellState = game.nextCellState(newCell, setOfCells);

		Assert.assertEquals("same object", QuadState.YELLOW, resultCellState);
	}
}
