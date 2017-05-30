import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JDialog;

public class Controller {
	private Model model;
	private View view;
	private String mode = "draw";

	Point selectPoint;
	ComponentObject temp;

	private ComponentObject currentObj = null;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	private void updateAttribute(ComponentObject obj) {
		view.getTextField().setText(obj.getName());
		view.getTextField_1().setText(Integer.toString(obj.getstartX()));
		view.getTextField_2().setText(Integer.toString(obj.getstartY()));
		view.getTextField_3().setText(Integer.toString(obj.getWidth()));
		view.getTextField_4().setText(Integer.toString(obj.getHeight()));

	}

	private boolean ComponentObjectColiderCheck(ArrayList<ComponentObject> list, Shape _s) {
		for (ComponentObject o : list) {
			Shape s = o.getShape();
			if (s.getBounds2D().intersects(_s.getBounds2D()))
				return false;
		}
		return true;
	}

	public void control() {
		view.getSelectModeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = "select";
				view.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		view.getDrawModeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = "draw";
				view.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});

		view.getApplyBtn().addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 System.out.println(Float.parseFloat(view.getTextField_1().getText()));
				 
				 Shape r = new Rectangle2D.Float(
						 Float.parseFloat(view.getTextField_1().getText()),
						 Float.parseFloat(view.getTextField_2().getText()), 
						 Float.parseFloat(view.getTextField_3().getText()), 
						 Float.parseFloat(view.getTextField_4().getText()));
				 if( ComponentObjectColiderCheck(model.getObjs(), r) ) // 변수이름 사용 자기자신 예외
				 {
					currentObj.setShape(r); 
					view.getEditorPane().repaint();
				 }
				 else
					 System.out.println("is overlapped");
			 }	 
		});
		

		view.getEditorPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (mode) {
				case "draw":
					((View.PaintSurface) view.getEditorPane()).startDrag = new Point(e.getX(), e.getY());
					((View.PaintSurface) view.getEditorPane()).endDrag = ((View.PaintSurface) view
							.getEditorPane()).startDrag;
					view.getEditorPane().repaint();
					break;

				case "select" :
          selectPoint = new Point(e.getX(), e.getY());
          temp = model.findComponentByPos(e.getX(), e.getY());
					ComponentObject o = model.findComponentByPos(e.getX(), e.getY());
					if(o != null)
					{
						currentObj = o;
						updateAttribute(currentObj);
					}
					else
					{

						System.out.println("no match object");
					}
					break;
				}

			}

			public void mouseReleased(MouseEvent e) {
				Point p1 = new Point(((View.PaintSurface) view.getEditorPane()).startDrag.x, ((View.PaintSurface) view.getEditorPane()).startDrag.y);
				Point p2 = new Point(((View.PaintSurface) view.getEditorPane()).endDrag.x, ((View.PaintSurface) view.getEditorPane()).endDrag.y);
				switch (mode) {
				
				case "draw":
					Shape r = ((View.PaintSurface) view.getEditorPane()).makeRectangle(
							((View.PaintSurface) view.getEditorPane()).startDrag.x,
							((View.PaintSurface) view.getEditorPane()).startDrag.y, e.getX(), e.getY());

					if (ComponentObjectColiderCheck(model.getObjs(), r)
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.x != e.getX())
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.y != e.getY())) {
						temp = new ComponentObject(r, null, null);
						updateAttribute(temp);
						model.addObj(temp);
						currentObj = temp;
					}
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
					view.getEditorPane().repaint();
					break;
				case "select":
					temp.changeR(p1 ,p2);
					if(ComponentObjectColiderCheck(model.getObjs(), temp.getShape())){
						
						updateAttribute(temp);
						model.addObj(temp);
						
					}
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
					view.getEditorPane().repaint();
					break;

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}
		});

		view.getEditorPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				switch (mode) {
				case "draw":
					((View.PaintSurface) view.getEditorPane()).endDrag = new Point(e.getX(), e.getY());
					view.getEditorPane().repaint();
					break;
				case "select":
					temp = model.findComponentByPos(selectPoint.x, selectPoint.y);

					((View.PaintSurface) view.getEditorPane()).startDrag = new Point(
							(e.getX() - (selectPoint.x - temp.getstartX())),
							(e.getY() - (selectPoint.y - temp.getstartY())));

					((View.PaintSurface) view.getEditorPane()).endDrag = new Point(
							(e.getX() - (selectPoint.x - temp.getstartX())) + temp.getWidth(),
							(e.getY() - (selectPoint.y - temp.getstartY())) + temp.getHeight());
					view.getEditorPane().repaint();
					break;
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

}
