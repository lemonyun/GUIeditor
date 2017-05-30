import java.awt.Shape;
import java.util.ArrayList;

public class Model {
	
	private static ArrayList<ComponentObject> objs = new ArrayList<ComponentObject>();
	
	
	public void addObj(ComponentObject obj){
		objs.add(obj);
	}



	public static ArrayList<ComponentObject> getObjs() {
		return objs;
	}
	
	
	
}
//클래스 하나 만들고 멤버 1. shape 2. 이름  3. 속성 