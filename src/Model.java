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
//Ŭ���� �ϳ� ����� ��� 1. shape 2. �̸�  3. �Ӽ� 