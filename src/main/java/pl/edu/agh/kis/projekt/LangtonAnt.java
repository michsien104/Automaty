package pl.edu.agh.kis.projekt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author Micha³ Sieñczak
 */

public class LangtonAnt extends Automaton2Dim{
	private int x;
	private int y;
	private LangtonCell cell;

	public LangtonAnt(int x, int y, AntState as) {
		super();
		this.x = x;
		this.y = y;
		this.cell = new LangtonCell(BinaryState.ALIVE, as);
	}
	
	public LangtonAnt(Map<CellCoordinates, CellState> mapOfCells, CellStateFactory factory, 
			CellNeighbourhood neighbourhoodStrategy, int x, int y, AntState as, int width, int height) {
		super(mapOfCells, neighbourhoodStrategy, factory, width, height);
		this.x = x;
		this.y = y;
		this.cell = new LangtonCell(BinaryState.ALIVE, as);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public LangtonCell getCell() {
		return cell;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setCell(LangtonCell cell) {
		this.cell = cell;
	}

	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighbourhood neighbourhoodStrategy) {	
		
		Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();
		
		for (int i = 1; i <= super.getWidth(); i++) {
			for (int j = 1; j <= super.getHeight(); j++) {
				mapOfCells.put(new Coords2D(i, j), factory.initialState(new Coords2D(i,j)));
			}
		}
		
		return new LangtonAnt(mapOfCells, factory, neighbourhoodStrategy, y, x, AntState.NONE, super.getWidth(), super.getHeight());
	}

	public CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {

		CellState currentCellState = currentCell.getCellState();
		LangtonCell langtonCell = new LangtonCell();
		
		// Je¿eli komórka jest ¿ywa
		if (currentCellState.equals(BinaryState.ALIVE)) {
			langtonCell.setCellState(BinaryState.DEAD);
			if (currentCellState.equals(AntState.NORTH)) {
				langtonCell.setAntState(AntState.EAST);
				y += 1;
			} else if (currentCellState.equals(AntState.EAST)) {
				langtonCell.setAntState(AntState.SOUTH);
				x += 1;
			} else if (currentCellState.equals(AntState.SOUTH)) {
				langtonCell.setAntState(AntState.WEST);
				y -= 1;
			} else if (currentCellState.equals(AntState.WEST)) {
				langtonCell.setAntState(AntState.NORTH);
				x -= 1;
			}

		} else  {
			langtonCell.setCellState(BinaryState.ALIVE);

			if (currentCellState.equals(AntState.NORTH)) {
				langtonCell.setAntState(AntState.WEST);
				x -= 1;
			} else if (currentCellState.equals(AntState.EAST)) {
				langtonCell.setAntState(AntState.NORTH);
				y += 1;
			} else if (currentCellState.equals(AntState.SOUTH)) {
				langtonCell.setAntState(AntState.EAST);
				x += 1;
			} else if (currentCellState.equals(AntState.WEST)) {
				langtonCell.setAntState(AntState.SOUTH);
				y -= 1;
			}
		}
		
		if(x < 1){
			x = x + super.getWidth();
		} else if(x > super.getWidth()){
			x = x - super.getWidth();
		}
		if(y < 1){
			y = y + super.getHeight();
		} else if(y > super.getHeight()){
			y = y - super.getHeight();
		}

		return null;
	}
}
