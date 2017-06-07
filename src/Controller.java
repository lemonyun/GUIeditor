import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.RepaintManager;

public class Controller {
	private Model model;
	private View view;
	private FileProcessManager fpMgr;
	private String mode = "draw";
	private boolean isNewProject = false;
	private Point selectPoint;

	private static ComponentObject currentObj = null;

	private boolean sizeControlFlag = false;
	private int selectedSizeControl = 0;

	public static ComponentObject getCurrentObj() {
		return currentObj;
	}

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
		view.getTextField_5().setText(obj.getText());

		if (obj.getType() != null) {
			view.getComboBox().getEditor().setItem(obj.getType());
		}
	}
	private void setNull(){
		view.getTextField().setText(null);
		view.getTextField_1().setText(null);
		view.getTextField_2().setText(null);
		view.getTextField_3().setText(null);
		view.getTextField_4().setText(null);
		view.getTextField_5().setText(null);
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
				view.getEditorPane().repaint();
			}
		});
		view.getMnOpen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpMgr.open(model.getObjs());
				view.getEditorPane().repaint();

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
				fpMgr.save(model.getObjs(), true);
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
				view.getEditorPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				view.getFrame().setCursor(Cursor.getDefaultCursor());
			}
		});
		view.getDrawModeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = "draw";
				view.getEditorPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				view.getFrame().setCursor(Cursor.getDefaultCursor());
			}
		});

		view.getApplyBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(Float.parseFloat(view.getTextField_1().getText()));
				if(currentObj == null)
					return;
				Rectangle2D.Float r = new Rectangle2D.Float(Float.parseFloat(view.getTextField_1().getText()),
						Float.parseFloat(view.getTextField_2().getText()),
						Float.parseFloat(view.getTextField_3().getText()),
						Float.parseFloat(view.getTextField_4().getText()));
				int tempX, tempY, tempHeight, tempWidth;
				tempX = currentObj.getstartX();
				tempY = currentObj.getstartY();
				tempHeight = currentObj.getHeight();
				tempWidth = currentObj.getWidth();
				model.delObj(currentObj);

				currentObj.setName(view.getTextField().getText());
				currentObj.setType(view.getComboBox().getSelectedItem().toString());
				currentObj.setText(view.getTextField_5().getText());
				currentObj.setShape(r);
				if (ComponentObjectColiderCheck(model.getObjs(), currentObj)) {
					model.addObj(currentObj);
				} else {
					currentObj.setShape(new Rectangle2D.Float(tempX, tempY, tempWidth, tempHeight));
					model.addObj(currentObj);
				}

				view.getEditorPane().repaint();
			}
		});
		view.getDeleteBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getObjs().remove(currentObj);
				currentObj = null;
				setNull();
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
					if (currentObj != null) {
						updateAttribute(currentObj);
						if (e.getX() < currentObj.getstartX() + 15 && e.getY() < currentObj.getstartY() + 15) {
							selectedSizeControl = 1;
							sizeControlFlag = true;
							((View.PaintSurface) view.getEditorPane()).startDrag = currentObj.getstartPoint();
							((View.PaintSurface) view.getEditorPane()).endDrag = currentObj.getEndPoint();
							view.getEditorPane().repaint();

						} else if (e.getX() > currentObj.getstartX() + currentObj.getWidth() - 15
								&& e.getY() < currentObj.getstartY() + 15) {
							selectedSizeControl = 2;
							sizeControlFlag = true;
							((View.PaintSurface) view.getEditorPane()).startDrag = currentObj.getstartPoint();
							((View.PaintSurface) view.getEditorPane()).endDrag = currentObj.getEndPoint();
							view.getEditorPane().repaint();

						} else if (e.getX() < currentObj.getstartX() + 15
								&& e.getY() > currentObj.getstartY() + currentObj.getHeight() - 15) {
							selectedSizeControl = 3;
							sizeControlFlag = true;
							((View.PaintSurface) view.getEditorPane()).startDrag = currentObj.getstartPoint();
							((View.PaintSurface) view.getEditorPane()).endDrag = currentObj.getEndPoint();
							view.getEditorPane().repaint();

						} else if (e.getX() > currentObj.getstartX() + currentObj.getWidth() - 15
								&& e.getY() > currentObj.getstartY() + currentObj.getHeight() - 15) {
							selectedSizeControl = 4;
							sizeControlFlag = true;
							((View.PaintSurface) view.getEditorPane()).startDrag = currentObj.getstartPoint();
							((View.PaintSurface) view.getEditorPane()).endDrag = currentObj.getEndPoint();
							view.getEditorPane().repaint();

						}

					} else {

						setNull();
						currentObj = null;
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
					currentObj = new ComponentObject(r, null, null, null);
					if (ComponentObjectColiderCheck(model.getObjs(), currentObj)
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.x != e.getX())
							&& (((View.PaintSurface) view.getEditorPane()).startDrag.y != e.getY())) {
						currentObj = new ComponentObject(r,
								"unnamedObject" + Integer.toString(model.getObjs().size() + 1), null, "default");
						updateAttribute(currentObj);
						model.addObj(currentObj);
					}
					((View.PaintSurface) view.getEditorPane()).startDrag = null;
					((View.PaintSurface) view.getEditorPane()).endDrag = null;
					view.getEditorPane().repaint();
					break;
				case "select":
					if (((View.PaintSurface) view.getEditorPane()).startDrag == null) {
						currentObj = model.findComponentByPos(selectPoint.x, selectPoint.y);
						view.getEditorPane().repaint();
						break;
					}

					currentObj = model.findComponentByPos(selectPoint.x, selectPoint.y);

					Point p1 = new Point(((View.PaintSurface) view.getEditorPane()).startDrag.x,
							((View.PaintSurface) view.getEditorPane()).startDrag.y);
					Point p2 = new Point(((View.PaintSurface) view.getEditorPane()).endDrag.x,
							((View.PaintSurface) view.getEditorPane()).endDrag.y);

					if (ComponentObjectColiderCheck(model.getObjs(), currentObj.changeR(p1, p2))) {
						model.delObj(currentObj);
						updateAttribute(currentObj.changeR(p1, p2));
						model.addObj(currentObj.changeR(p1, p2));
						if (sizeControlFlag) {
							switch (selectedSizeControl) {
							case 1:
								currentObj = model.findComponentByPos(e.getX() + 1, e.getY() + 1);
								break;
							case 2:
								currentObj = model.findComponentByPos(e.getX() - 1, e.getY() + 1);
								break;
							case 3:
								currentObj = model.findComponentByPos(e.getX() + 1, e.getY() - 1);
								break;
							case 4:
								currentObj = model.findComponentByPos(e.getX() - 1, e.getY() - 1);
								break;
							}

						} else
							currentObj = model.findComponentByPos(e.getX(), e.getY());

					}
					if (sizeControlFlag) {
						selectedSizeControl = 0;
						sizeControlFlag = false;
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
					if (currentObj == null)
						break;

					currentObj = model.findComponentByPos(selectPoint.x, selectPoint.y);
					if (sizeControlFlag) {

						switch (selectedSizeControl) {
						case 1:
							((View.PaintSurface) view.getEditorPane()).startDrag = new Point(e.getX(), e.getY());
							view.getEditorPane().repaint();
							break;
						case 2:
							((View.PaintSurface) view.getEditorPane()).startDrag = new Point(currentObj.getstartX(),
									e.getY());
							((View.PaintSurface) view.getEditorPane()).endDrag = new Point(e.getX(),
									currentObj.getstartY() + currentObj.getHeight());
							view.getEditorPane().repaint();
							break;
						case 3:
							((View.PaintSurface) view.getEditorPane()).startDrag = new Point(e.getX(),
									currentObj.getstartY());
							((View.PaintSurface) view.getEditorPane()).endDrag = new Point(
									currentObj.getstartX() + currentObj.getWidth(), e.getY());
							view.getEditorPane().repaint();
							break;
						case 4:
							((View.PaintSurface) view.getEditorPane()).endDrag = new Point(e.getX(), e.getY());
							view.getEditorPane().repaint();
							break;
						}
						break;
					} else {
						((View.PaintSurface) view.getEditorPane()).startDrag = new Point(
								(e.getX() - (selectPoint.x - currentObj.getstartX())),
								(e.getY() - (selectPoint.y - currentObj.getstartY())));

						((View.PaintSurface) view.getEditorPane()).endDrag = new Point(
								(e.getX() - (selectPoint.x - currentObj.getstartX())) + currentObj.getWidth(),
								(e.getY() - (selectPoint.y - currentObj.getstartY())) + currentObj.getHeight());
						view.getEditorPane().repaint();
					}
					break;
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

}
