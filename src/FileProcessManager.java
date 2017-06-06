import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class FileProcessManager {
	private Gson gson;
	JFileChooser fc;
	private View view;
	private String currentFilePath;

	public FileProcessManager(View view) {
		gson = new Gson();
		fc = new JFileChooser();
		this.view = view;
	}

	public void save(final ArrayList<ComponentObject> list, boolean makeNewFile) {
		String objListJsonStr = gson.toJson(list);
		if (makeNewFile) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("json files", "json");
			fc.setFileFilter(filter);
			int returnVal = fc.showSaveDialog(view.getFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION)
				currentFilePath = fc.getSelectedFile().getAbsolutePath() + ".json";
			else
				return;
		}
		try {
			FileWriter writer = new FileWriter(currentFilePath);
			writer.write(objListJsonStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void open(ArrayList<ComponentObject> list) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("json files", "json");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(view.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentFilePath = fc.getSelectedFile().getAbsolutePath();
			if (currentFilePath != null) {
				try {
					JsonReader reader = new JsonReader(new FileReader(currentFilePath));
					Type myDataType = new TypeToken<Collection<ComponentObject>>() {
					}.getType();
					ArrayList<ComponentObject> jsonToList = gson.fromJson(reader, myDataType);

					list = jsonToList;
					for (ComponentObject o : list)
						System.out.println(o.getShape());
					view.getEditorPane().setVisible(true);
					// view.getEditorPane().repaint();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else
			return;
	}

	public void makeJavaFile(final ArrayList<ComponentObject> list) {
		String result = null; 
		int returnVal = fc.showSaveDialog(view.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			currentFilePath = fc.getSelectedFile().getAbsolutePath() + ".java";
			result = makeResultString(list);
		}
		else
			return;

		try {
			FileWriter writer = new FileWriter(currentFilePath);
			writer.write(result);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String makeResultString(final ArrayList<ComponentObject> list) {
		String result = "";
		try {
			FileReader reader = new FileReader(System.getProperty("user.dir") + "/data/defaultText.txt");
			int ch;
			while ((ch = reader.read()) != -1) { // 하나하나씩 받아오고 출력시킨다!
				result += (char)ch;
			}
			reader.close(); // 스트림을 다 썼으면 닫아주어야 한다.
		} catch (IOException e) {
			e.printStackTrace();
		}
		result += fc.getSelectedFile().getName() + " {\n";
		result += "public static void main(String[] args) {\n"
				+ "JFrame frame = new JFrame(\"" + fc.getSelectedFile().getName() + "\");\n"
				+ "frame.setVisible(true);\n";

		result += "}\n}";
		System.out.println(result);
		return result;
	}
}
