package pl.edu.agh.kis.projekt;

/** 
 * Klasa przechowuj¹ca koordynaty komórek 2D
 * 
 * @author Micha³ Sieñczak
 */

public class Coords2D implements CellCoordinates{
	private final int x;
	private final int y;
	
	public Coords2D (int a, int b){
		this.x = a;
		this.y = b;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coords2D other = (Coords2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getXCoord(){
		return this.x;
	}
	public int getYCoord(){
		return this.y;
	}
}
