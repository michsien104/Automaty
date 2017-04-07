package pl.edu.agh.kis.projekt;

/** 
 * Klasa przechowuj¹ca koordynaty komórek 2D
 * 
 * @author Micha³ Sieñczak
 */

public class Coords1D implements CellCoordinates{
	private final int x;
	
	public Coords1D (int a){
		this.x = a;
	}
	
	public int getXCoord(){
		return this.x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
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
		Coords1D other = (Coords1D) obj;
		if (x != other.x)
			return false;
		return true;
	}
	
}
