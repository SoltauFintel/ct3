package de.mwvb.ct3;

import de.mwvb.gui.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class Ct3App extends Application {

	public static void main(String[] args) {
		Window.APP_NAME = "ct3";
		Window.APP_VERSION = "2.01";
		// v2.01: bugfix "Location not set"
		// v2.00: successor of jxls_ct 1.x Tool: Generic Clipboard text transformation tool ("ct3")
		
		System.out.println(Window.getNameVersion());
		launch(Ct3App.class, new String[] {});
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			new MainWindow().show(stage);
		} catch (Exception e) {
			e.printStackTrace();
			Window.alert(e);
		}
	}
}
