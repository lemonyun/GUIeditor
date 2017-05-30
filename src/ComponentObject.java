import java.awt.Shape;

public class ComponentObject {
	Shape r;
	String name;
	String type;
	public ComponentObject(Shape r, String name, String type) {
		this.r = r;
		this.name = name;
		this.type = type;
	}
	public Shape getShape() {return r;}
	
}
