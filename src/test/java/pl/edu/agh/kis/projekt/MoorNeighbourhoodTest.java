package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MoorNeighbourhoodTest {

	@Test
	public void moorNeighbourhoodTestWithoutWrapping() {
		MoorNeighbourhood moorNeighbourhood = new MoorNeighbourhood(50, 50, false, 1);
		Coords2D centralCoords = new Coords2D(10, 10);
		Set<CellCoordinates> resultSetOfNeighbours = moorNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		trueSetOfNeighbours.add(new Coords2D(10, 9));
		trueSetOfNeighbours.add(new Coords2D(10, 11));
		trueSetOfNeighbours.add(new Coords2D(9, 10));
		trueSetOfNeighbours.add(new Coords2D(9, 11));
		trueSetOfNeighbours.add(new Coords2D(9, 9));
		trueSetOfNeighbours.add(new Coords2D(11, 10));
		trueSetOfNeighbours.add(new Coords2D(11, 9));
		trueSetOfNeighbours.add(new Coords2D(11, 11));

		Assert.assertTrue(resultSetOfNeighbours.containsAll(trueSetOfNeighbours));

	}

	@Test
	public void moorNeighbourhoodTestWrapping() {
		MoorNeighbourhood moorNeighbourhood = new MoorNeighbourhood(50, 50, true, 1);
		Coords2D centralCoords = new Coords2D(1, 1);
		Set<CellCoordinates> resultSetOfNeighbours = moorNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		trueSetOfNeighbours.add(new Coords2D(1, 2));
		trueSetOfNeighbours.add(new Coords2D(2, 2));
		trueSetOfNeighbours.add(new Coords2D(2, 1));
		trueSetOfNeighbours.add(new Coords2D(50, 50));
		trueSetOfNeighbours.add(new Coords2D(1, 50));
		trueSetOfNeighbours.add(new Coords2D(50, 1));
		trueSetOfNeighbours.add(new Coords2D(50, 2));
		trueSetOfNeighbours.add(new Coords2D(2, 50));

		Assert.assertTrue(resultSetOfNeighbours.containsAll(trueSetOfNeighbours));
		}

	@Test
	public void moorNeighbourhoodTestWithBiggerRadious() {
		MoorNeighbourhood moorNeighbourhood = new MoorNeighbourhood(50, 50, false, 2);
		Coords2D centralCoords = new Coords2D(10, 10);
		Set<CellCoordinates> resultSetOfNeighbours = moorNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		for (int i = 8; i < 13; i++) {
			for (int j = 8; j < 13; j++) {
				if ((i != 10) || (j != 10)) {
					trueSetOfNeighbours.add(new Coords2D(i, j));
				}
			}
		}

		Assert.assertTrue(resultSetOfNeighbours.containsAll(trueSetOfNeighbours));
	}

}
