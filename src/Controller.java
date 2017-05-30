import java.awt.Cursor;
import java.awt.Point;
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
		view.getSelectModeBtn().addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 mode = "select";
				    view.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 }
		});
		view.getDrawModeBtn().addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){  mode = "draw";
			    view.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));}
		});
		
		view.getEditorPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch(mode)
				{
				case "draw" : 
					((View.PaintSurface) view.getEditorPane()).startDrag = new Point(e.getX(), e.getY());
					((View.PaintSurface) view.getEditorPane()).endDrag = ((View.PaintSurface) view
							.getEditorPane()).startDrag;
					view.getEditorPane().repaint();
					break;
				case "select" :
					ComponentObject o = model.findComponentByPos(e.getX(), e.getY());
					if(o != null)
					{
						updateAttribute(o);
					}
					else
					{
						System.out.println("no match object");
					}
					break;
				}
				
			}

			public void mouseReleased(MouseEvent e) {
				switch(mode)
				{
				case "draw" : 
					Shape r = ((View.PaintSurface) view.getEditorPane()).makeRectangle(
							((View.PaintSurface) view.getEditorPane()).startDrag.x,
							((View.PaintSurface) view.getEditorPane()).startDrag.y, e.getX(), e.getY());
					
					if(ComponentObjectColiderCheck(model.getObjs(), r)
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.x != e.getX())
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.y != e.getY()))
					{
						ComponentObject temp = new ComponentObject(r, null, null);
						updateAttribute(temp);
						model.addObj(temp);
					}
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
					view.getEditorPane().repaint();
					break;
				case "select" : break;
				}
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
