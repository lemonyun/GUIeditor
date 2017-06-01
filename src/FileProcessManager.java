import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class FileProcessManager {
	private Gson gson;

	public FileProcessManager() {
		gson = new Gson();
	}

	public void makeJsonSaveFile(ArrayList<ComponentObject> list) {
		String objListJsonStr = gson.toJson(list);
		System.out.println(objListJsonStr);

		try {
			FileWriter writer = new FileWriter("/data/saveFile.json");
			writer.write(objListJsonStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
