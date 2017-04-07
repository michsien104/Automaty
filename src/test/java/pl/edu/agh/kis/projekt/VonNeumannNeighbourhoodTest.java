package pl.edu.agh.kis.projekt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class VonNeumannNeighbourhoodTest {

	@Test
	public void moorNeighbourhoodWthoutWrappingWithBiggerRadious() {
		VonNeumannNeighbourhood vonNeumannNeighbourhood = new VonNeumannNeighbourhood(50, 50, false, 2);
		Coords2D centralCoords = new Coords2D(10, 10);
		Set<CellCoordinates> resultSetOfNeighbours = vonNeumannNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		trueSetOfNeighbours.add(new Coords2D(10, 9));
		trueSetOfNeighbours.add(new Coords2D(10, 11));
		trueSetOfNeighbours.add(new Coords2D(11, 10));
		trueSetOfNeighbours.add(new Coords2D(9, 10));
		trueSetOfNeighbours.add(new Coords2D(10, 8));
		trueSetOfNeighbours.add(new Coords2D(10, 12));
		trueSetOfNeighbours.add(new Coords2D(12, 10));
		trueSetOfNeighbours.add(new Coords2D(8, 10));

		Assert.assertTrue(trueSetOfNeighbours.containsAll(resultSetOfNeighbours));
	}
	
	@Test
	public void moorNeighbourhoodTestWithWrappingWithBiggerRadious() {
		VonNeumannNeighbourhood vonNeumannNeighbourhood = new VonNeumannNeighbourhood(50, 50, true, 2);
		Coords2D centralCoords = new Coords2D(1, 1);
		Set<CellCoordinates> resultSetOfNeighbours = vonNeumannNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		trueSetOfNeighbours.add(new Coords2D(1, 2));
		trueSetOfNeighbours.add(new Coords2D(1, 3));
		trueSetOfNeighbours.add(new Coords2D(1, 49));
		trueSetOfNeighbours.add(new Coords2D(1, 50));
		trueSetOfNeighbours.add(new Coords2D(2, 1));
		trueSetOfNeighbours.add(new Coords2D(3, 1));
		trueSetOfNeighbours.add(new Coords2D(50, 1));
		trueSetOfNeighbours.add(new Coords2D(49, 1));

		Assert.assertTrue(trueSetOfNeighbours.containsAll(resultSetOfNeighbours));
	}
	

	@Test
	public void moorNeighbourhoodTestWithWrapping() {
		VonNeumannNeighbourhood vonNeumannNeighbourhood = new VonNeumannNeighbourhood(50, 50, true, 1);
		Coords2D centralCoords = new Coords2D(2, 2);
		Set<CellCoordinates> resultSetOfNeighbours = vonNeumannNeighbourhood.cellNeighbours(centralCoords);
		Set<CellCoordinates> trueSetOfNeighbours = new HashSet<CellCoordinates>();

		trueSetOfNeighbours.add(new Coords2D(1, 2));
		trueSetOfNeighbours.add(new Coords2D(2, 3));
		trueSetOfNeighbours.add(new Coords2D(3, 2));
		trueSetOfNeighbours.add(new Coords2D(2, 1));

		Assert.assertEquals(trueSetOfNeighbours,resultSetOfNeighbours);
	}
}
