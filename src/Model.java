import java.awt.Shape;
import java.util.ArrayList;

public class Model {
	
	private static ArrayList<ComponentObject> objs = new ArrayList<ComponentObject>();
	
	
	public void addObj(ComponentObject obj){
		objs.add(obj);
	}
	public void delObj(ComponentObject obj){
		objs.remove(obj);
	}
	public static ArrayList<ComponentObject> getObjs() {
		return objs;
	}
	public ComponentObject findComponentByPos(final int x, final int y)
	{
		for(ComponentObject o : objs)
		{
			if( o.getShape().intersects(x, y, 1, 1))
			{
				return o;
			}
		}
		return null;
	}
	
	
}
//Ŭ���� �ϳ� ����� ��� 1. shape 2. �̸�  3. �Ӽ� 