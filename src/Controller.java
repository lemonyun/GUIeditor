import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	private boolean ComponentObjectColiderCheck(ArrayList<ComponentObject> list, Shape _s)
	{
		for( ComponentObject o : list)
		{
			Shape s = o.getShape();
		    if(s.getBounds2D().intersects(_s.getBounds2D()))
		    	return true;
		}
		return false;
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
				Shape r = ((View.PaintSurface) view.getEditorPane()).makeRectangle(
						((View.PaintSurface) view.getEditorPane()).startDrag.x,
						((View.PaintSurface) view.getEditorPane()).startDrag.y, e.getX(), e.getY());
				if(ComponentColiderCheck(model.getShapes(), r))
				{
					model.addRect(r);
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
				}
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
