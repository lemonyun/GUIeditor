import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

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
				ComponentObject temp = new ComponentObject(r, null, null);
				model.addObj(temp);
				updateAttribute(temp);
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
