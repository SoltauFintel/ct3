package de.mwvb.ct3;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainWindowController {
	private final Transformator transformator = new Transformator();
	private boolean lightgreen = true;
	@FXML
	private BorderPane ground;
	
	@FXML
	public void onTransform() {
		try {
			boolean success = transformator.transform();
			setBackgroundColor(success);
		} catch (Exception e) {
			MainWindow.alert(e.getMessage());
		}
	}

	private void setBackgroundColor(boolean success) {
		String color;
		if (success) {
			color = lightgreen ? "090" : "6f6";
			lightgreen = !lightgreen;
		} else {
			color = "f33";
		}
		ground.setStyle("-fx-background-color: #" + color + ";");
	}
}
