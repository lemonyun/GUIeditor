import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String componentTypeList[] = { "button", "label", "333", "444" };
	private JComponent editorPane;
	/**
	 * Launch the application.
	 */

	public View() {

		frame = new JFrame("View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1000, 600);
		frame.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);

		JMenu mnSave = new JMenu("Save");
		mnFile.add(mnSave);

		JMenu mnSaveAs = new JMenu("Save As");
		mnFile.add(mnSaveAs);

		JMenu mnMakejava = new JMenu("make .java");
		mnFile.add(mnMakejava);

		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		JButton drawModeBtn = new JButton("drawMode");
		JButton moveModeBtn = new JButton("moveMode");
		toolBar.add(drawModeBtn);
		toolBar.add(moveModeBtn);
		contentPane.add(toolBar, BorderLayout.NORTH);

		JPanel attribute = new JPanel();
		editorPane = new PaintSurface();
		// attribute.setBackground(Color.BLUE);
		contentPane.add(attribute, BorderLayout.WEST); // 속성페인
		contentPane.add(editorPane, BorderLayout.CENTER); // 에디터페인

		attribute.setLayout(new BoxLayout(attribute, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		attribute.add(panel);

		JLabel lblNewLabel = new JLabel("name");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_6 = new JPanel();
		attribute.add(panel_6);

		JLabel lblNewLabel_1 = new JLabel("x");
		panel_6.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_7 = new JPanel();
		attribute.add(panel_7);

		JLabel lblNewLabel_2 = new JLabel("y");
		panel_7.add(lblNewLabel_2);

		textField_2 = new JTextField();
		panel_7.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_1 = new JPanel();
		attribute.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		attribute.add(panel_3);

		JLabel lblWidth = new JLabel("width");
		panel_3.add(lblWidth);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_3.add(textField_3);

		JPanel panel_4 = new JPanel();
		attribute.add(panel_4);

		JLabel lblHeight = new JLabel("height");
		panel_4.add(lblHeight);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_4.add(textField_4);

		JPanel panel_5 = new JPanel();
		attribute.add(panel_5);

		JLabel lblType = new JLabel("type");
		panel_5.add(lblType);

		JComboBox comboBox = new JComboBox(componentTypeList);
		panel_5.add(comboBox);

		/*
		 * JPanel editor = new JPanel(); editor.setBackground(Color.DARK_GRAY);
		 * contentPane.add(editor, BorderLayout.CENTER); editor.setLayout(null);
		 */
	}
	
	public JComponent getEditorPane() {
		return editorPane;
	}

	public static class PaintSurface extends JComponent {
		ArrayList<Shape> shapes = new ArrayList<Shape>();

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
			// repaint와 getSize함수는 JComponent의 것 상속

		}

		public void paint(Graphics g) { // repaint시 호출됨, 오버라이딩 된거
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 안티얼리어싱
																										// 적용
			paintBackground(g2);
			// Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN ,
			// Color.RED, Color.BLUE, Color.PINK};

			g2.setStroke(new BasicStroke(2)); // 사각형 테두리 굵기
			// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			// 0.50f)); // 투명도 0.5

			for (Shape s : Model.getShapes()) {
				g2.setPaint(Color.BLACK);
				g2.draw(s); // 테두리 그리기
				g2.setPaint(Color.GRAY);
				g2.fill(s); // 내부 채우기
			} // arraylist에 저장된 shape 모두 다시 그리기

			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				g2.draw(r);
			} // 마우스 드래그중 그려지는 회색 사각형

		}

		public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
	}
}

//