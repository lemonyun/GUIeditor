import java.awt.Shape;
import java.util.ArrayList;

public class Model {
	
	private static ArrayList<Shape> shapes = new ArrayList<Shape>();
	public void addRect(Shape r){
		shapes.add(r);
	}
	public static ArrayList<Shape> getShapes() {
		return shapes;
	}
}
