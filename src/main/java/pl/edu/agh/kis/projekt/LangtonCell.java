package pl.edu.agh.kis.projekt;

public class LangtonCell implements CellState{
	private BinaryState cellState;
	private AntState antState;
	private int antID;
	
	public LangtonCell(BinaryState cellState, AntState antState) {
		this.cellState = cellState;
		this.antState = antState;
	}

	public LangtonCell() {
		super();
	}

	public BinaryState getCellState() {
		return cellState;
	}

	public void setCellState(BinaryState cellState) {
		this.cellState = cellState;
	}

	public AntState getAntState() {
		return antState;
	}

	public void setAntState(AntState antState) {
		this.antState = antState;
	}

	public int getAntID() {
		return antID;
	}

	public void setAntID(int antID) {
		this.antID = antID;
	}

	
}
