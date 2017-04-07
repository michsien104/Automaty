package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

public class QuadLifeNextStateGUI {

	public QuadLifeNextStateGUI(ActionEvent e, Automaton automat, Map<CellCoordinates, CellState> mapOfCells,
			String neighbourhoodStrategy, boolean wrapping, int radious, JButton[][] JBCells, int width,
			int height) {
		
		Automaton aut1 = nextState(width, height, wrapping, radious, mapOfCells, automat, neighbourhoodStrategy);
		paintCells(JBCells, aut1);

	}
	
	private void paintCells(JButton[][] JBCells, Automaton automat){
		for (Entry<CellCoordinates, CellState> it : automat.getCells().entrySet()) {
			Coords2D coords = (Coords2D) it.getKey();
			if (it.getValue().equals(QuadState.RED)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.RED);
			} else if (it.getValue().equals(QuadState.GREEN)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.GREEN);
			} else if (it.getValue().equals(QuadState.BLUE)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.BLUE);
			} else if (it.getValue().equals(QuadState.YELLOW)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.YELLOW);
			} else 
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.WHITE);
		}
	}
	
	private Automaton nextState(int width, int height, boolean wrapping, int radious, Map<CellCoordinates, CellState> mapOfCells, Automaton automat, String neighbourhoodStrategy){
		CellNeighbourhood neighbourhood;
		if (neighbourhoodStrategy == "MoorNeighbourhood") {
			neighbourhood = new MoorNeighbourhood(height, width, wrapping, radious);
		} else
			neighbourhood = new VonNeumannNeighbourhood(height, width, wrapping, radious);
		
		automat = new QuadLife(mapOfCells, neighbourhood, new GeneralStateFactory(mapOfCells), width, height);
		automat = automat.nextState();
		mapOfCells.putAll(automat.getCells());
		return automat;
	}
}
