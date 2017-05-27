import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String componentTypeList[] = 
		{
				"button",
				"label",
				"333",
				"444"
		};
	/**
	 * Launch the application.
	 */

	public View() {
		
		frame = new JFrame("View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1000,600);
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
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JPanel attribute = new JPanel();
		//attribute.setBackground(Color.BLUE);
		contentPane.add(attribute, BorderLayout.WEST);
		contentPane.add(new PaintSurface(), BorderLayout.CENTER);
		
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
		
		/*JPanel editor = new JPanel();
		editor.setBackground(Color.DARK_GRAY);
		contentPane.add(editor, BorderLayout.CENTER);
		editor.setLayout(null);*/
	}
	private class PaintSurface extends JComponent {
	    ArrayList<Shape> shapes = new ArrayList<Shape>();

	    Point startDrag, endDrag;

	    public PaintSurface() {
	      this.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	          startDrag = new Point(e.getX(), e.getY());
	          endDrag = startDrag;
	          repaint();
	        }

	        public void mouseReleased(MouseEvent e) {
	          Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
	          shapes.add(r);
	          startDrag = null;
	          endDrag = null;
	          repaint();
	        }
	      });

	      this.addMouseMotionListener(new MouseMotionAdapter() {
	        public void mouseDragged(MouseEvent e) {
	          endDrag = new Point(e.getX(), e.getY());
	          repaint();
	        }
	      });
	    }
	    private void paintBackground(Graphics2D g2){
	      g2.setPaint(Color.BLACK);
	      for (int i = 0; i < getSize().width; i += 10) {
	        Shape line = new Line2D.Float(i, 0, i, getSize().height);
	        g2.draw(line);
	      }

	      for (int i = 0; i < getSize().height; i += 10) {
	        Shape line = new Line2D.Float(0, i, getSize().width, i);
	        g2.draw(line);
	      }

	      
	    }
	    public void paint(Graphics g) {
	      Graphics2D g2 = (Graphics2D) g;
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	      paintBackground(g2);
	      Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
	      int colorIndex = 0;

	      g2.setStroke(new BasicStroke(2));
	      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

	      for (Shape s : shapes) {
	        g2.setPaint(Color.BLACK);
	        g2.draw(s);
	        g2.setPaint(colors[(colorIndex++) % 6]);
	        g2.fill(s);
	      }

	      if (startDrag != null && endDrag != null) {
	        g2.setPaint(Color.LIGHT_GRAY);
	        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
	        g2.draw(r);
	      }
	    }

	    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
	      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	    }
	  }
}
