import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
		attribute.setBackground(Color.LIGHT_GRAY);
		contentPane.add(attribute, BorderLayout.WEST);
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
		
		JPanel editor = new JPanel();
		editor.setBackground(Color.DARK_GRAY);
		contentPane.add(editor, BorderLayout.CENTER);
		editor.setLayout(null);
	}
}
