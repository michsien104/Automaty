package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* Klasa obs³ugujaca gre w automaty jednowymiarowe
* 
*/

public class Automaton1D extends Automaton1Dim {
	private int rule;

	public Automaton1D(int width, int rule) {
		super(width);
		this.rule = rule;
	}

	public Automaton1D(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy,
			Map<CellCoordinates, CellState> mapOfCells, int rule, int width) {
		super(factory, neighbourhoodStrategy, mapOfCells, width);
		this.rule = rule;
	}
	/**
	* Implementacja abstrakcyjnej metody z klasy automaton,
	* zwraca nowa instancje gry z zainicjalizowanymi komórkami
	* 
	*/
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy) {
		Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();
		Set<CellCoordinates> setOfCoords = new HashSet<CellCoordinates>();
		for (int i = 1; i < super.getWidth(); i++ ){
			setOfCoords.add(new Coords1D(i));
		}
		for(CellCoordinates it : setOfCoords){
			mapOfCells.put(it, factory.initialState(it));
		}
			return new Automaton1D(factory, neighbourhoodStrategy, mapOfCells, super.getWidth(), rule);
	}

	/**
	 * 
	* Implementacja abstrakcyjnej metody z klasy automaton,
	* wylicza nastepny stan komórki wzglêdem s¹siadów
	* 
	*/
	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {
		int aliveNeighbours = countAliveNeighbours(neighbours);
		Coords1D currentCellCoords = (Coords1D) currentCell.getCellCoordinates();
		BinaryState currentCellState = (BinaryState) currentCell.getCellState();
		BinaryState left = BinaryState.DEAD;
		BinaryState right = BinaryState.DEAD;

		for (Cell it : neighbours) {
			Coords1D coords = (Coords1D) it.getCellCoordinates();
			if (coords.getXCoord() == currentCellCoords.getXCoord() - 1) {
				left = (BinaryState) it.getCellState();
			} else if (coords.getXCoord() == currentCellCoords.getXCoord() + 1) {
				right = (BinaryState) it.getCellState();
			}

			if (aliveNeighbours == 2) {
				if (currentCellState.equals(BinaryState.ALIVE)) {
					return cellStateFromBinaryNumber(0);
				} else {
					return cellStateFromBinaryNumber(2);
				}
			} else if (aliveNeighbours == 1) {
				if ((left == BinaryState.ALIVE) && (currentCellState == BinaryState.ALIVE)) {
					return cellStateFromBinaryNumber(1);
				} else if ((left == BinaryState.ALIVE) && (currentCellState == BinaryState.DEAD)) {
					return cellStateFromBinaryNumber(3);
				} else if ((right == BinaryState.ALIVE) && (currentCellState == BinaryState.ALIVE)) {
					return cellStateFromBinaryNumber(4);
				} else if ((right == BinaryState.ALIVE) && (currentCellState == BinaryState.DEAD)) {
					return cellStateFromBinaryNumber(6);
				}
			} else if (aliveNeighbours == 0) {
				if (currentCellState == BinaryState.DEAD) {
					return cellStateFromBinaryNumber(7);
				} else if (currentCellState == BinaryState.ALIVE) {
					return cellStateFromBinaryNumber(5);
				}
			}
		}
		return BinaryState.ALIVE;
	}

	/**
	* metoda zamieniaj¹ca regu³e gry na liczbe binarna
	* 
	*/
	private String ruleToBinaryNumber() {
		String binary = Integer.toString(rule);
		for (int i = 8 - binary.length(); i > 0; i--) {
			binary = "0" + binary;
		}
		return binary;

	}
	/**
	* licz¹ca s¹siedztwo
	* 
	*/
	private int countAliveNeighbours(Set<Cell> neighbours) {
		int aliveNeighbours = 0;
		for (Cell it : neighbours) {
			if ((it.getCellState()).equals(BinaryState.ALIVE))
				aliveNeighbours++;
		}
		return aliveNeighbours;
	}

	/**
	* metoda zwracaj¹ca stan komórki zewzglêdu na liczbe binarna
	* 
	*/
	private BinaryState cellStateFromBinaryNumber(int index) {

		if (ruleToBinaryNumber().substring(index, 1 + index).equals("1"))
			return BinaryState.ALIVE;
		else
			return BinaryState.DEAD;
	}

}
