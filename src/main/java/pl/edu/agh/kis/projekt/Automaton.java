package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* G³ówna klasa automatów
* 
* @author Micha³ Sieñczak
*/
public abstract class Automaton {
	private Map<CellCoordinates, CellState> mapOfCells;
	private CellNeighbourhood neighbourhoodStrategy;
	private CellStateFactory cellStateFactory;

	// konstruktory
	public Automaton() {
	}

	public Automaton(Map<CellCoordinates, CellState> cells, CellNeighbourhood neighbourhoodStrategy,
			CellStateFactory cellStateFactory) {
		this.mapOfCells = cells;
		this.neighbourhoodStrategy = neighbourhoodStrategy;
		this.cellStateFactory = cellStateFactory;
	}

	// getery
	public Map<CellCoordinates, CellState> getCells() {
		return this.mapOfCells;
	}

	public CellNeighbourhood getNeighbourhoodStrategy() {
		return neighbourhoodStrategy;
	}

	public CellStateFactory getCellStateFactory() {
		return cellStateFactory;
	}

	public void setCellStateFactory(CellStateFactory cellStateFactory) {
		this.cellStateFactory = cellStateFactory;
	}

	// klasy abstrakcyjne
	protected abstract Automaton newInstance(CellStateFactory factory, CellNeighbourhood neighborhoodStrategy);

	protected abstract boolean hasNextCoordinates(CellCoordinates coord);

	protected abstract CellCoordinates initialCoordinates();

	protected abstract CellCoordinates nextCoordinates(CellCoordinates coord);

	public abstract CellState nextCellState(Cell currentCell, Set<Cell> neighbours);

	/**
	* Metoda przyjmuj¹ca Set koordynatów i zamienij¹ca go na set komórek
	* 
	*/
	private Set<Cell> mapCoordinates(Set<CellCoordinates> setOfCellCoordinates) {
		Set<Cell> setOfCells = new HashSet<Cell>();
		for (CellCoordinates it : setOfCellCoordinates) {
			setOfCells.add(new Cell(this.mapOfCells.get(it), it));
		}
		return setOfCells;
	}

	/**
	 * wstawianie do pola struktury 
	 * @param structure
	 */
	public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
		mapOfCells.putAll(structure);
	}

	public Map<CellCoordinates, CellState> getMapOfCells() {
		return mapOfCells;
	}
	
	/**
	* metoda obliczaj¹ca nastepny stan automatu
	* 
	*/
	public Automaton nextState() {
		Automaton newAutomaton = this.newInstance(this.cellStateFactory, this.neighbourhoodStrategy);
		CellIterator newAutomatonIt = newAutomaton.cellIterator();
		CellIterator thisIterator = this.cellIterator();
		//System.out.println(this.getCells().size());
		while(thisIterator.hasNext()){
			Cell cell = thisIterator.next();
			//System.out.println("number of j :" +" - "+ ((Coords2D)thisIterator.currentCoordinates).getXCoord()+" "+((Coords2D)thisIterator.currentCoordinates).getYCoord() );
			Set<CellCoordinates> setOfNeighboursCellCoordinates = this.neighbourhoodStrategy
					.cellNeighbours(cell.getCellCoordinates());
			//System.out.println(setOfNeighboursCellCoordinates.size());
			Set<Cell> setOfNeighboursCells = this.mapCoordinates(setOfNeighboursCellCoordinates);
			CellState newCellState = nextCellState(new Cell(cell.getCellState(), cell.getCellCoordinates()), setOfNeighboursCells);
			newAutomatonIt.next();
			//System.out.println("number of j :" +" - "+ ((Coords2D)newAutomatonIt.currentCoordinates).getXCoord()+" "+((Coords2D)newAutomatonIt.currentCoordinates).getYCoord() );
			//if(newCellState.equals(BinaryState.ALIVE)) System.out.println("alive");
			//else System.out.println("dead");
			newAutomatonIt.setState(newCellState);
		}
		return newAutomaton;
	}

	public void setNeighbourhoodStrategy(CellNeighbourhood neighbourhoodStrategy) {
		this.neighbourhoodStrategy = neighbourhoodStrategy;
	}

	/**
	* Metoda tworz¹ca iterator do automatonu
	* 
	*/
	private CellIterator cellIterator() {
		return new CellIterator();
	}

	/**
	* Klasa iterator
	* 
	*/
	public class CellIterator {
		private CellCoordinates currentCoordinates = initialCoordinates();

		public CellIterator() {
		}

		public CellIterator(CellCoordinates currentCoordinates) {
			this.currentCoordinates = currentCoordinates;
		}

		public void setCurrentCoordinates(CellCoordinates currentState) {
			this.currentCoordinates = currentState;
		}

		public boolean hasNext() {
			return hasNextCoordinates(this.currentCoordinates);
		}

		public Cell next() {
			currentCoordinates = nextCoordinates(currentCoordinates);
			return new Cell(mapOfCells.get(currentCoordinates), currentCoordinates);
		}

		public void setState(CellState stateToSet) {
			Automaton.this.mapOfCells.put(currentCoordinates, stateToSet);
		}
	}

}
