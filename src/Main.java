import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					Model model = new Model();
					Controller controller = new Controller(model, frame);
					controller.control();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
