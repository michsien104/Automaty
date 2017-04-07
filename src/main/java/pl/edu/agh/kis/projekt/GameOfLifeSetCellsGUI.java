package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.Timer;

/**
 * Klasa wstawiaj¹ca komórki do gry
 * 
 * @author Micha³ Sieñczak
 */

public class GameOfLifeSetCellsGUI {

	public GameOfLifeSetCellsGUI(ActionEvent e, Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells,
			int width, int height, JButton JBStartGOF, JButton JBStopGOF, int structureID, javax.swing.Timer timerGOF) {

		insertStructure(width, height, structureID, mapOfCells, JBCells, e);
		checkTimers(JBStopGOF, JBStartGOF, timerGOF, e);
		paintCells(mapOfCells, JBCells);
	}

	private void paintCells(Map<CellCoordinates, CellState> mapOfCells, JButton[][] JBCells) {
		for (Entry<CellCoordinates, CellState> it : mapOfCells.entrySet()) {
			Coords2D coords = (Coords2D) it.getKey();
			if (it.getValue().equals(BinaryState.ALIVE)) {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.BLACK);
			} else {
				JBCells[coords.getXCoord()][coords.getYCoord()].setBackground(Color.WHITE);
			}
		}
	}

	private void checkTimers(JButton JBStopGOF, JButton JBStartGOF, Timer timerGOF, ActionEvent e) {
		if (e.getSource() == JBStopGOF) {
			timerGOF.stop();
		} else if (e.getSource() == JBStartGOF) {
			timerGOF.start();
		}
	}

	private void insertStructure(int width, int height, int structureID, Map<CellCoordinates, CellState> mapOfCells,
			JButton[][] JBCells, ActionEvent e) {
		if (structureID == 0) {
			for (int i = 1; i <= width; i++) {
				for (int j = 1; j <= height; j++) {
					if (e.getSource() == JBCells[i][j]) {
						Coords2D coords = new Coords2D(i, j);
						if (mapOfCells.get(coords).equals(BinaryState.DEAD)) {
							mapOfCells.put(coords, BinaryState.ALIVE);
						} else {
							mapOfCells.put(coords, BinaryState.DEAD);
						}
					}
				}
			}
		} else if (structureID == 1) {
			for (int i = 1; i <= width; i++) {
				for (int j = 1; j <= height; j++) {
					if (e.getSource() == JBCells[i][j]) {
						GliderStructure glider = new GliderStructure(i, j, height, width);
						mapOfCells.putAll(glider.getMapOfStructure());
					}
				}
			}

		} else if (structureID == 2) {
			for (int i = 1; i <= width; i++) {
				for (int j = 1; j <= height; j++) {
					if (e.getSource() == JBCells[i][j]) {
						GunStructure gun = new GunStructure(i, j, height, width);
						mapOfCells.putAll(gun.getMapOfStructure());
					}
				}
			}
		} else if (structureID == 3) {
			for (int i = 1; i <= width; i++) {
				for (int j = 1; j <= height; j++) {
					if (e.getSource() == JBCells[i][j]) {
						PenthadecathlonStructure pentha = new PenthadecathlonStructure(i, j, width);
						mapOfCells.putAll(pentha.getMapOfStructure());
					}
				}
			}
		}
	}
}
