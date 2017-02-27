package de.mwvb.gui;

import java.io.IOException;
import java.nio.charset.Charset;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class Window<CTR> {
	public static String APP_NAME;
	public static String APP_VERSION;
	protected CTR controller;

	public void show(Stage stage) {
		Scene scene = new Scene(root());
		stage.setScene(scene);
		stage.setTitle(APP_NAME + " " + APP_VERSION);
		initWindow(stage);
		stage.show();
	}
	
	protected void initWindow(Stage stage) {
		// Template method
	}

	protected Parent root() {
		try {
			FXMLLoader loader = new FXMLLoader(Charset.forName("windows-1252"));
			loader.setLocation(getClass().getResource(getClass().getSimpleName() + ".fxml"));
			controller = createController();
			loader.setController(controller);
			return loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract CTR createController();

	public static void alert(String errorMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(getNameVersion() + " error message");
		alert.setHeaderText("");
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
	
	public static void alert(Exception e) {
		String msg = e.getClass().getName() + ": " + e.getMessage();
		for (int i = 0; i <= 4 && i < e.getStackTrace().length; i++) {
			msg += "\n   at " + e.getStackTrace()[i].toString();
		}
		alert(msg);
	}
	
	public static String getNameVersion() {
		return APP_NAME + " " + APP_VERSION;
	}
}
