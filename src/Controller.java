import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	private void updateAttribute(ComponentObject obj){
		view.getTextField().setText(obj.name);
		view.getTextField_1().setText(Float.toString(((Rectangle2D.Float)(obj.r)).x));
		view.getTextField_2().setText(Float.toString(((Rectangle2D.Float)(obj.r)).y));
		view.getTextField_3().setText(Float.toString(((Rectangle2D.Float)(obj.r)).width));
		view.getTextField_4().setText(Float.toString(((Rectangle2D.Float)(obj.r)).height));
		
	}
	private boolean ComponentObjectColiderCheck(ArrayList<ComponentObject> list, Shape _s)
	{
		for( ComponentObject o : list)
		{
			Shape s = o.r;
		    if(s.getBounds2D().intersects(_s.getBounds2D()))
		    	return false;
		}
		return true;
	}
	
	public void control() {

		view.getEditorPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				((View.PaintSurface) view.getEditorPane()).startDrag = new Point(e.getX(), e.getY());
				((View.PaintSurface) view.getEditorPane()).endDrag = ((View.PaintSurface) view
						.getEditorPane()).startDrag;
				view.getEditorPane().repaint();
			}

			public void mouseReleased(MouseEvent e) {
				int x, y;
				x = e.getX();
				y = e.getY();
			
				if(x > view.getEditorPane().getWidth())
					x = view.getEditorPane().getWidth();
				if(y > view.getEditorPane().getHeight())
					y = view.getEditorPane().getHeight();
				if(x < 0)
					x = 0;
				if(y < 0)
					y = 0;
					
				Shape r = ((View.PaintSurface) view.getEditorPane()).makeRectangle(
						((View.PaintSurface) view.getEditorPane()).startDrag.x,
						((View.PaintSurface) view.getEditorPane()).startDrag.y, e.getX(), e.getY());
				
				if(ComponentObjectColiderCheck(model.getObjs(), r))
				{
					ComponentObject temp = new ComponentObject(r, null, null);
					updateAttribute(temp);
					model.addObj(temp);
				}

				((View.PaintSurface) view.getEditorPane()).startDrag = null;
				((View.PaintSurface) view.getEditorPane()).endDrag = null;
				view.getEditorPane().repaint();
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}
		});

		view.getEditorPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				((View.PaintSurface) view.getEditorPane()).endDrag = new Point(e.getX(), e.getY());
				view.getEditorPane().repaint();
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

}
