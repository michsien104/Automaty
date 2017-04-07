package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class GameOfLifeTest {

	@Test
	public void nextCellStateTestA() {
		//test umierania komórki
		String rule = "23/3";
		GameOfLife game = new GameOfLife(rule,20,20);
		//komorka o koordynatach 5,10
		Coords2D newCoords = new Coords2D(5,10);
		CellState cellState = BinaryState.ALIVE;
		Set<Cell> setOfCells = new HashSet<Cell>();
		Cell newCell = new Cell(cellState, newCoords);
		
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(5,11)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(5,9)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(6,10)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,10)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(6,11)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,11)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,9)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(6,9)));
		
		CellState resultCellState = game.nextCellState(newCell, setOfCells);
		
		Assert.assertEquals("same object",BinaryState.ALIVE, resultCellState);
	}
	
	@Test
	public void nextCellStateTestB() {
		//test o¿ywania komóki
		String rule = "52/2";
		GameOfLife game = new GameOfLife(rule,20,20);
		//komorka o koordynatach 5,10
		Coords2D newCoords = new Coords2D(5,10);
		CellState cellState = BinaryState.ALIVE;
		Set<Cell> setOfCells = new HashSet<Cell>();
		Cell newCell = new Cell(cellState, newCoords);

		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(5,11)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(5,9)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(6,10)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(6,9)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(6,11)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,11)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,9)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(4,10)));
		
		CellState resultCellState = game.nextCellState(newCell, setOfCells);
		
		Assert.assertEquals("same object",BinaryState.ALIVE, resultCellState);
	}
	@Test
	public void nextCellStateTest3x3() {
		//test o¿ywania komóki
		String rule = "23/3";
		GameOfLife game = new GameOfLife(rule,3,3);
		//komorka o koordynatach 2,1
		Coords2D newCoords = new Coords2D(1,3);
		CellState cellState = BinaryState.DEAD;
		Set<Cell> setOfCells = new HashSet<Cell>();
		Cell newCell = new Cell(cellState, newCoords);

		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(1,1)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(1,2)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(1,3)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(2,1)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(2,2)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(2,3)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(3,1)));
		setOfCells.add(new Cell(BinaryState.ALIVE,new Coords2D(3,2)));
		setOfCells.add(new Cell(BinaryState.DEAD,new Coords2D(3,3)));
		
		CellState resultCellState = game.nextCellState(newCell, setOfCells);
		
		Assert.assertEquals("same object",BinaryState.ALIVE, resultCellState);
	}

}
