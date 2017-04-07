package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Klasa tworz¹ca grê quad life i sprawdzaj¹ca warunki ¿ycia komórki
 * 
 * @author Micha³ Sieñczak
 */

public class QuadLife extends Automaton2Dim {

	public QuadLife() {

	}

	public QuadLife(Map<CellCoordinates, CellState> mapOfCells, CellNeighbourhood neighbourhoodStrategy,
			CellStateFactory factory, int width, int height) {
		super(mapOfCells, neighbourhoodStrategy, factory, width, height);
	}

	@Override
	public Automaton newInstance(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy) {
		Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();

		for (int i = 1; i <= super.getWidth(); i++) {
			for (int j = 1; j <= super.getHeight(); j++) {
				mapOfCells.put(new Coords2D(i, j), factory.initialState(new Coords2D(i,j)));
			}
		}

		return new QuadLife(mapOfCells, neighbourhoodStrategy, factory, super.getWidth(), super.getHeight());
	}

	public CellState nextCellState(Cell currentCell, Set<Cell> setOfNeighbours) {
	
		CellState nextCellState = QuadState.DEAD;
		int aliveNeighbours = 0;
		int yellow = 0;
		int green = 0;
		int blue = 0;
		int red = 0;

		for (Cell it : setOfNeighbours) {
			if (it.getCellState().equals(QuadState.BLUE))
				blue++;
			else if (it.getCellState().equals(QuadState.RED))
				red++;
			else if (it.getCellState().equals(QuadState.YELLOW))
				yellow++;
			else if (it.getCellState().equals(QuadState.GREEN))
				green++;
		}
		aliveNeighbours = yellow + green + red + blue;

		// Je¿eli ma 3 s¹siadów
		if (aliveNeighbours == 3) {

			if (yellow >= 2)
				nextCellState = QuadState.YELLOW;
			else if (green >= 2)
				nextCellState = QuadState.GREEN;
			else if (blue >= 2)
				nextCellState = QuadState.BLUE;
			else if (red >= 2)
				nextCellState = QuadState.RED;
			else if (yellow == 0)
				nextCellState = QuadState.YELLOW;
			else if (green == 0)
				nextCellState = QuadState.GREEN;
			else if (blue == 0)
				nextCellState = QuadState.BLUE;
			else if (red == 0)
				nextCellState = QuadState.RED;

			return nextCellState;

		} else
			return nextCellState;
	}
}
