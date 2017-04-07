package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.Timer;

public class Automaton1DSetCellsGUI {

	public Automaton1DSetCellsGUI(ActionEvent e, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells,
			int width, JButton JBStart1D, JButton JBStop1D, Timer timer1D, int rule1d, int radious) {

		insertStructure(width, mapOfCells, JBCells, e);
		checkTimers(timer1D, JBStart1D, JBStop1D, e);
		paintCells(width, mapOfCells, JBCells, e);
	}

	private void insertStructure(int width, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells,
			ActionEvent e) {
		for (int i = 1; i <= width; i++) {
				if (e.getSource() == JBCells[i][1]) {
					Coords1D coords = new Coords1D(i);
					if (mapOfCells.get(coords).equals(BinaryState.DEAD)) {
						mapOfCells.put(coords, BinaryState.ALIVE);
					} else {
						mapOfCells.put(coords, BinaryState.DEAD);
					}
			}
		}

	}

	private void paintCells(int width, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells, ActionEvent e) {
		for (Entry<CellCoordinates, CellState> it : mapOfCells.entrySet()) {
			Coords1D coords = (Coords1D) it.getKey();
			if (it.getValue().equals(BinaryState.ALIVE)) {
				JBCells[coords.getXCoord()][1].setBackground(Color.BLACK);
			} else {
				JBCells[coords.getXCoord()][1].setBackground(Color.WHITE);
			}
		}

	}

	private void checkTimers(Timer timer1D, JButton JBStart1D, JButton JBStop1D, ActionEvent e) {
			if (e.getSource() == JBStop1D) {
				timer1D.stop();
			} else if (e.getSource() == JBStart1D) {
				timer1D.start();
			}
	}
}