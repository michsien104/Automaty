package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.Timer;

public class QuadLifeSetCellsGUI {

	public QuadLifeSetCellsGUI(ActionEvent e, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells,
			int width, int height, JButton JBStartQL, JButton JBStopQL, javax.swing.Timer timerQL) {

		checkTimers(timerQL, JBStartQL, JBStopQL, e);
		paintCells(width, height, mapOfCells, JBCells, e);

	}
	
	private void paintCells(int width, int height, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells, ActionEvent e){
		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (e.getSource() == JBCells[i][j]) {
					Coords2D coords = new Coords2D(i, j);
					if (mapOfCells.get(coords).equals(QuadState.DEAD)) {
						mapOfCells.put(coords, QuadState.RED);
						JBCells[i][j].setBackground(Color.RED);
					} else if (mapOfCells.get(coords).equals(QuadState.RED)) {
						mapOfCells.put(coords, QuadState.YELLOW);
						JBCells[i][j].setBackground(Color.YELLOW);
					} else if (mapOfCells.get(coords).equals(QuadState.YELLOW)) {
						mapOfCells.put(coords, QuadState.BLUE);
						JBCells[i][j].setBackground(Color.BLUE);
					} else if (mapOfCells.get(coords).equals(QuadState.BLUE)) {
						mapOfCells.put(coords, QuadState.GREEN);
						JBCells[i][j].setBackground(Color.GREEN);
					} else if (mapOfCells.get(coords).equals(QuadState.GREEN)) {
						mapOfCells.put(coords, QuadState.DEAD);
						JBCells[i][j].setBackground(Color.WHITE);
					}
				}
			}
		}
	}
	private void checkTimers(Timer timerQL, JButton JBStartQL, JButton JBStopQL, ActionEvent e){
		if (e.getSource() == JBStopQL) {
			timerQL.stop();
		} else if (e.getSource() == JBStartQL) {
			timerQL.start();
		}
	}

}
