import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class View {

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public void setTextField_4(JTextField textField_4) {
		this.textField_4 = textField_4;
	}

	public JButton getDrawModeBtn() {
		return drawModeBtn;
	}

	public JButton getSelectModeBtn() {
		return selectModeBtn;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getApplyBtn() {
		return applyBtn;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JMenuItem getMnSave() {
		return mnSave;
	}

	public JMenuItem getMnOpen() {
		return mnOpen;
	}

	public JMenuItem getMnSaveAs() {
		return mnSaveAs;
	}

	public JMenuItem getMnMakejava() {
		return mnMakejava;
	}

	public JMenuItem getMnNew() {
		return mnNew;
	}

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String componentTypeList[] = { "Select", "JButton", "JLabel" };
	private JComponent editorPane;
	private JComponent attributePane;
	private JButton selectModeBtn;
	private JButton drawModeBtn;
	private JButton applyBtn;
	private JButton deleteBtn;
	
	private JButton newBtn;
	private JButton openBtn;
	private JButton saveBtn;
	private JButton saveAsBtn;
	private JButton makeJavaBtn;
	
	private JComboBox comboBox;

	private JMenuItem mnNew;
	private JMenuItem mnOpen;
	private JMenuItem mnSave;
	private JMenuItem mnSaveAs;
	private JMenuItem mnMakejava;

	public String[] getComponentTypeList() {
		return componentTypeList;
	}

	public JComponent getAttributePane() {
		return attributePane;
	}

	public JButton getNewBtn() {
		return newBtn;
	}

	public JButton getOpenBtn() {
		return openBtn;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public JButton getSaveAsBtn() {
		return saveAsBtn;
	}

	public JButton getMakeJavaBtn() {
		return makeJavaBtn;
	}

	/**
	 * Launch the application.
	 */

	public View() {

		frame = new JFrame("View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1000, 600);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		frame.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mnNew = new JMenuItem("New");
		mnFile.add(mnNew);

		mnOpen = new JMenuItem("Open");
		mnFile.add(mnOpen);

		mnSave = new JMenuItem("Save");
		mnFile.add(mnSave);

		mnSaveAs = new JMenuItem("Save As");
		mnFile.add(mnSaveAs);

		mnMakejava = new JMenuItem("make .java");
		mnFile.add(mnMakejava);

		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		drawModeBtn = new JButton("drawMode");
		selectModeBtn = new JButton("selectMode");
		newBtn = new JButton("new");
		openBtn = new JButton("open");
		saveBtn = new JButton("save");
		saveAsBtn = new JButton("save as");
		makeJavaBtn = new JButton("make java");
		toolBar.add(drawModeBtn);
		toolBar.add(selectModeBtn);
		toolBar.add(newBtn);
		toolBar.add(openBtn);
		toolBar.add(saveBtn);
		toolBar.add(saveAsBtn);
		toolBar.add(makeJavaBtn);
		contentPane.add(toolBar, BorderLayout.NORTH);

		attributePane = new JPanel();
		editorPane = new PaintSurface();
		// attribute.setBackground(Color.BLUE);

		editorPane.setVisible(false);
		contentPane.add(attributePane, BorderLayout.WEST);
		contentPane.add(editorPane, BorderLayout.CENTER);

		attributePane.setLayout(new BoxLayout(attributePane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		attributePane.add(panel);

		JLabel lblNewLabel = new JLabel("name");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_6 = new JPanel();
		attributePane.add(panel_6);

		JLabel lblNewLabel_1 = new JLabel("x");
		panel_6.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_7 = new JPanel();
		attributePane.add(panel_7);

		JLabel lblNewLabel_2 = new JLabel("y");
		panel_7.add(lblNewLabel_2);

		textField_2 = new JTextField();
		panel_7.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_1 = new JPanel();
		attributePane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		attributePane.add(panel_3);

		JLabel lblWidth = new JLabel("width");
		panel_3.add(lblWidth);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_3.add(textField_3);

		JPanel panel_4 = new JPanel();
		attributePane.add(panel_4);

		JLabel lblHeight = new JLabel("height");
		panel_4.add(lblHeight);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_4.add(textField_4);

		JPanel panel_8 = new JPanel();
		attributePane.add(panel_8);

		JLabel lblText = new JLabel("text");
		panel_8.add(lblText);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_8.add(textField_5);

		JPanel panel_5 = new JPanel();
		attributePane.add(panel_5);

		JLabel lblType = new JLabel("type");
		panel_5.add(lblType);

		comboBox = new JComboBox(componentTypeList);
		comboBox.setEditable(true);
		panel_5.add(comboBox);

		applyBtn = new JButton("apply");
		attributePane.add(applyBtn);

		deleteBtn = new JButton("delete");
		attributePane.add(deleteBtn);

		/*
		 * JPanel editor = new JPanel(); editor.setBackground(Color.DARK_GRAY);
		 * contentPane.add(editor, BorderLayout.CENTER); editor.setLayout(null);
		 */
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTextField_5() {
		return textField_5;
	}

	public JComponent getEditorPane() {
		return editorPane;
	}

	public static class PaintSurface extends JComponent {
		Point startDrag, endDrag;

		private void paintBackground(Graphics2D g2) {
			g2.setPaint(Color.LIGHT_GRAY);
			for (int i = 0; i < getSize().width; i += 10) {
				Shape line = new Line2D.Float(i, 0, i, getSize().height);
				g2.draw(line);
			}

			for (int i = 0; i < getSize().height; i += 10) {
				Shape line = new Line2D.Float(0, i, getSize().width, i);
				g2.draw(line);
			}
			//
		}

		public void paint(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			paintBackground(g2);
			// Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN ,
			// Color.RED, Color.BLUE, Color.PINK};

			g2.setStroke(new BasicStroke(2));

			for (ComponentObject obj : Model.getObjs()) {

				Font font = new Font("Serif", Font.PLAIN, obj.getWidth() / 10);
				g2.setFont(font);

				if (obj.getType() == null) {
					g2.setPaint(Color.GRAY);
					g2.fill(obj.getShape());
					g2.setPaint(Color.BLACK);
					g2.draw(obj.getShape());
					if (obj.getText() != null)
						g2.drawString(obj.getText(), (2 * obj.getstartX() + obj.getWidth()) / 2,
								(2 * obj.getstartY() + obj.getHeight()) / 2);
				} else {
					switch (obj.getType()) {
					case "JButton":
						g2.setPaint(Color.yellow);
						g2.fill(obj.getShape());
						break;
					case "JLabel":
						g2.setPaint(Color.MAGENTA);
						g2.fill(obj.getShape());
						break;
					}
					g2.setPaint(Color.BLACK);
					g2.draw(obj.getShape());
					g2.drawString(obj.getText(), (2 * obj.getstartX() + obj.getWidth()) / 2,
							(2 * obj.getstartY() + obj.getHeight()) / 2);
				}
				if (obj == Controller.getCurrentObj()) {
					g2.setPaint(Color.GREEN);
					g2.fill(obj.getShape());

					g2.setPaint(Color.BLACK);
					g2.draw(obj.getShape());
					if (obj.getText() != null)
						g2.drawString(obj.getText(), (2 * obj.getstartX() + obj.getWidth()) / 2,
								(2 * obj.getstartY() + obj.getHeight()) / 2);

					Shape r = makeRectangle(obj.getstartX(), obj.getstartY(), obj.getstartX() + 15,
							obj.getstartY() + 15);
					g2.setPaint(Color.LIGHT_GRAY);
					g2.fill(r);
					g2.draw(r);
					r = makeRectangle(obj.getstartX() + obj.getWidth() - 15, obj.getstartY(),
							obj.getstartX() + obj.getWidth(), obj.getstartY() + 15);
					g2.fill(r);
					g2.draw(r);

					r = makeRectangle(obj.getstartX(), obj.getstartY() + obj.getHeight() - 15, obj.getstartX() + 15,
							obj.getstartY() + obj.getHeight());
					g2.fill(r);
					g2.draw(r);
					r = makeRectangle(obj.getstartX() + obj.getWidth() - 15, obj.getstartY() + obj.getHeight() - 15,
							obj.getstartX() + obj.getWidth(), obj.getstartY() + obj.getHeight());
					g2.fill(r);
					g2.draw(r);

				}

			}

			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				g2.draw(r);
			}

		}

		public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
	}
}

//