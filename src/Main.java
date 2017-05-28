import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
