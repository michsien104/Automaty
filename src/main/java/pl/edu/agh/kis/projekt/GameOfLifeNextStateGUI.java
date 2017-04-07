package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

public class GameOfLifeNextStateGUI {

	public GameOfLifeNextStateGUI(ActionEvent e, Automaton automat, Map<CellCoordinates, CellState> mapOfCells,
			String neighbourhoodStrategy, boolean wrapping, int radious, JButton[][] JBCells, int width, int height,
			String rule) {

		Automaton aut1 = nextState(width, height, neighbourhoodStrategy, mapOfCells, rule, radious, wrapping, automat);
		paintCells(aut1, JBCells);
	}

	private void paintCells(Automaton aut1, JButton[][] JBCells) {
		for (Entry<CellCoordinates, CellState> it : aut1.getCells().entrySet()) {
			Coords2D coords = (Coords2D) it.getKey();
			if (it.getValue().equals(BinaryState.ALIVE)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.BLACK);
			} else {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.WHITE);
			}
		}
	}
	
	private Automaton nextState(int width, int height, String neighbourhoodStrategy, Map<CellCoordinates, CellState> mapOfCells, String rule, int radious, boolean wrapping, Automaton automat){
		CellNeighbourhood neighbourhood;
		if (neighbourhoodStrategy == "MoorNeighbourhood") {
			neighbourhood = new MoorNeighbourhood(height, width, wrapping, radious);
		} else
			neighbourhood = new VonNeumannNeighbourhood(height, width, wrapping, radious);

		automat = new GameOfLife(mapOfCells, neighbourhood, new GeneralStateFactory(mapOfCells), rule, width, height);
		automat = automat.nextState();
		mapOfCells.putAll(automat.getCells());
		return automat;
	}
}
