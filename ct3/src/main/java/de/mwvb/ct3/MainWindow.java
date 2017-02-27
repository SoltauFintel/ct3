package de.mwvb.ct3;

import de.mwvb.gui.Window;
import javafx.stage.Stage;

public class MainWindow extends Window<MainWindowController> {
	public static String buttonText = "Transform";
	public static String title = null;
	
	@Override
	protected void initWindow(Stage stage) {
		stage.setX(270);
		stage.setY(70);
		stage.setWidth(275);
		stage.setHeight(100);
		if (title != null) {
			stage.setTitle(title + " - " + stage.getTitle());
		}
		controller.setButtonText(buttonText);
	}
	
	@Override
	protected MainWindowController createController() {
		return new MainWindowController();
	}
}
