import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class ComponentObject {
	private Rectangle2D.Float r;
	private String name;
	private String type;
	public ComponentObject(Rectangle2D.Float r, String name, String type) {
		this.r = r;
		this.name = name;
		this.type = type;
	}
	public Rectangle2D.Float getShape() {return r;}

	
	public int getstartX(){
		return Math.round(((Rectangle2D.Float)(this.r)).x);
	}
	public int getstartY(){
		return Math.round(((Rectangle2D.Float)(this.r)).y);
	}
	public int getHeight(){
		return Math.round(((Rectangle2D.Float)(this.r)).height);
	}
	public int getWidth(){
		return Math.round(((Rectangle2D.Float)(this.r)).width);
	}
	public Point getstartPoint(){
		return new Point(this.getstartX(), this.getstartY());
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public ComponentObject changeR(Point p1, Point p2){
		
		Rectangle2D.Float r = new Rectangle2D.Float(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		return new ComponentObject(r, name, type);
	}
	

	public void setShape(Rectangle2D.Float r)
	{
		this.r = r ;
	}

}
