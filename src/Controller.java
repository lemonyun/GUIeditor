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
	private FileProcessManager fpMgr; 
	private String mode = "draw";
	private boolean isNewProject = false;
	private Point selectPoint;

	private ComponentObject currentObj = null;
	
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		fpMgr = new FileProcessManager(view);
	}

	private void updateAttribute(ComponentObject obj) {
		view.getTextField().setText(obj.getName());
		view.getTextField_1().setText(Integer.toString(obj.getstartX()));
		view.getTextField_2().setText(Integer.toString(obj.getstartY()));
		view.getTextField_3().setText(Integer.toString(obj.getWidth()));
		view.getTextField_4().setText(Integer.toString(obj.getHeight()));

	}

	private boolean ComponentObjectColiderCheck(ArrayList<ComponentObject> list, ComponentObject _o) {
		for (ComponentObject o : list) {
			if (o.getName() == _o.getName())
				continue;
			Shape s = o.getShape();
			if (s.getBounds2D().intersects(_o.getShape().getBounds2D()))
				return false;
		}
		return true;
	}

	public void control() {
		view.getMnNew().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getEditorPane().setVisible(true);
				isNewProject = true;
				model.getObjs().clear();
			}
		});
		view.getMnOpen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpMgr.open(model.getObjs());
				
			}
		});
		view.getMnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpMgr.save(model.getObjs(), isNewProject);
				isNewProject = false;
			}
		});
		view.getMnSaveAs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpMgr.save(model.getObjs(),true);
			}
		});
		view.getMnMakejava().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpMgr.makeJavaFile(model.getObjs());
			}
		});
		
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

		view.getApplyBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(Float.parseFloat(view.getTextField_1().getText()));
				
				Rectangle2D.Float r = new Rectangle2D.Float(Float.parseFloat(view.getTextField_1().getText()),
						Float.parseFloat(view.getTextField_2().getText()),
						Float.parseFloat(view.getTextField_3().getText()),
						Float.parseFloat(view.getTextField_4().getText()));

				currentObj.setName(view.getTextField().getText());
				currentObj.setType(view.getComboBox().getSelectedItem().toString());
				if (ComponentObjectColiderCheck(model.getObjs(), currentObj)) {
					currentObj.setShape(r);
				} else
					System.out.println("is overlapped");
				view.getEditorPane().repaint();
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

				case "select":
					selectPoint = new Point(e.getX(), e.getY());
					currentObj = model.findComponentByPos(e.getX(), e.getY());
					ComponentObject o = model.findComponentByPos(e.getX(), e.getY());
					if (o != null) {
						currentObj = o;
						updateAttribute(currentObj);
					} else {

						System.out.println("no match object");
					}
					break;
				}

			}

			public void mouseReleased(MouseEvent e) {

				switch (mode) {

				case "draw":
					Rectangle2D.Float r = ((View.PaintSurface) view.getEditorPane()).makeRectangle(
							((View.PaintSurface) view.getEditorPane()).startDrag.x,
							((View.PaintSurface) view.getEditorPane()).startDrag.y, e.getX(), e.getY());
					currentObj = new ComponentObject(r, null, null);
					if (ComponentObjectColiderCheck(model.getObjs(), currentObj)
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.x != e.getX())
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.y != e.getY())) {
						// currentObj = new ComponentObject(r, null, null);
						updateAttribute(currentObj);
						model.addObj(currentObj);
					}
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
					view.getEditorPane().repaint();
					break;
				case "select":
					if(((View.PaintSurface) view.getEditorPane()).startDrag == null)
						break;
					Point p1 = new Point(((View.PaintSurface) view.getEditorPane()).startDrag.x,
							((View.PaintSurface) view.getEditorPane()).startDrag.y);
					Point p2 = new Point(((View.PaintSurface) view.getEditorPane()).endDrag.x,
							((View.PaintSurface) view.getEditorPane()).endDrag.y);
					if (ComponentObjectColiderCheck(model.getObjs(), currentObj.changeR(p1, p2))) {
						model.delObj(currentObj);
						updateAttribute(currentObj.changeR(p1, p2));
						model.addObj(currentObj.changeR(p1, p2));

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
					currentObj = model.findComponentByPos(selectPoint.x, selectPoint.y);

					((View.PaintSurface) view.getEditorPane()).startDrag = new Point(
							(e.getX() - (selectPoint.x - currentObj.getstartX())),
							(e.getY() - (selectPoint.y - currentObj.getstartY())));

					((View.PaintSurface) view.getEditorPane()).endDrag = new Point(
							(e.getX() - (selectPoint.x - currentObj.getstartX())) + currentObj.getWidth(),
							(e.getY() - (selectPoint.y - currentObj.getstartY())) + currentObj.getHeight());
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
