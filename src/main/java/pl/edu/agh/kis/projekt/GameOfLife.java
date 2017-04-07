package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa sprawdzaj�ca warunki �ycia kom�rki i tworz�ca gre
 * 
 * @author Micha� Sie�czak
 */

public class GameOfLife extends Automaton2Dim {
	private Set<Integer> setOfStillAliveTerm = new HashSet<Integer>();
	private Set<Integer> setOfMakesAliveTerm = new HashSet<Integer>();
	private String rule;

	// konstruktory
	protected GameOfLife(Map<CellCoordinates, CellState> mapOfCells, CellNeighbourhood neighbourhoodStrategy,
			CellStateFactory factory, String rules, int width, int height) {

		super(mapOfCells, neighbourhoodStrategy, factory, width, height);
		this.rule = rules;
		parseStringRule();
	}

	public GameOfLife(String rule, int width, int height) {
		super(width, height);
		this.rule = rule;
		parseStringRule();
	}

	// getery i settery
	public Set<Integer> getSetOfStillAliveTerm() {
		return setOfStillAliveTerm;
	}

	public Set<Integer> getSetOfMakesAliveTerm() {
		return setOfMakesAliveTerm;
	}

	public void setSetOfStillAliveTerm(Set<Integer> setOfStillAliveTerm) {
		this.setOfStillAliveTerm = setOfStillAliveTerm;
	}

	public void setSetOfMakesAliveTerm(Set<Integer> setOfMakesAliveTerm) {
		this.setOfMakesAliveTerm = setOfMakesAliveTerm;
	}

	/**
	 * Implementacja abstrakcyjnej metody z klasy automaton, zwraca nowa
	 * instancje gry z zainicjalizowanymi kom�rkami
	 * 
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy) {

		Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();

		for (int i = 1; i <= super.getWidth(); i++) {
			for (int j = 1; j <= super.getHeight(); j++) {
				mapOfCells.put(new Coords2D(i, j), factory.initialState(new Coords2D(i, j)));
			}
		}

		return new GameOfLife(mapOfCells, neighbourhoodStrategy, factory, this.rule, super.getWidth(),
				super.getHeight());
	}

	/**
	 * Implementacja abstrakcyjnej metody z klasy automaton, wylicza nastepny
	 * stan kom�rki wzgl�dem s�siad�w
	 * 
	 */
	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {
		CellState currentCellState = currentCell.getCellState();
		Integer aliveNeighbours = 0;
		CellState nextCellState = BinaryState.DEAD;

		aliveNeighbours = countAliveNeighbours(neighbours);
		// warunki zmiany stanu na �yw� lub pozostania przy zyciu w przeciwnym
		// przypadku umiera
		if (setOfMakesAliveTerm.contains(aliveNeighbours))
			nextCellState = BinaryState.ALIVE;
		else if ((currentCellState.equals(BinaryState.ALIVE)) && (setOfStillAliveTerm.contains(aliveNeighbours))) {
			nextCellState = BinaryState.ALIVE;
		}
		return nextCellState;
	}

	/**
	* licz�ca s�siedztwo
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
	* metoda parsuj�ca metode gry ze Stringa do Kontener�w
	* 
	*/
	private void parseStringRule() {
		Pattern pattern = Pattern.compile("(\\d+)\\/(\\d+)");
		Matcher matcher = pattern.matcher(rule);
		matcher.find();
		Integer valueSA = Integer.valueOf(matcher.group(1));
		Integer valueMA = Integer.valueOf(matcher.group(2));

		if (valueSA == 0)
			setOfStillAliveTerm.add(0);
		if (valueMA == 0)
			setOfMakesAliveTerm.add(0);

		while (valueSA > 0) {
			setOfStillAliveTerm.add(valueSA % 10);
			valueSA = valueSA / 10;
		}
		while (valueMA > 0) {
			setOfMakesAliveTerm.add(valueMA % 10);
			valueMA = valueMA / 10;
		}

	}
}
