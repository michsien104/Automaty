package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

public class Automaton1DNextStateGUI {

	public Automaton1DNextStateGUI(ActionEvent e, Automaton automat, Map<CellCoordinates, CellState> mapOfCells,
			boolean wrapping, int radious, JButton[][] JBCells, int width, int height, int rule1d) {
		Automaton aut1 = nextState(width, mapOfCells, rule1d, radious, wrapping, automat);
		paintCells(aut1, JBCells);
	}

	private void paintCells(Automaton aut1, JButton[][] JBCells) {
		for (Entry<CellCoordinates, CellState> it : aut1.getCells().entrySet()) {
			Coords1D coords = (Coords1D) it.getKey();
			if (it.getValue().equals(BinaryState.ALIVE)) {
				JBCells[coords.getXCoord()][1].setBackground(Color.BLACK);
			} else {
				JBCells[coords.getXCoord()][1].setBackground(Color.WHITE);
			}
		}
	}

	private Automaton nextState(int width, Map<CellCoordinates, CellState> mapOfCells, int rule1d, int radious,
			boolean wrapping, Automaton automat) {
		automat = new Automaton1D(new GeneralStateFactory(mapOfCells), new Neighbourhood1D(wrapping, radious, width),
				mapOfCells, rule1d, width);
		automat = automat.nextState();
		mapOfCells.putAll(automat.getCells());
		return automat;
	}
}
