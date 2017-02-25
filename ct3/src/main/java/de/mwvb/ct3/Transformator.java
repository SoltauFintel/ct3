package de.mwvb.ct3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.mwvb.base.Clipboard;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class Transformator {

	public boolean transform() {
		try {
			Clipboard clipboard = new Clipboard();
			String text = clipboard.getText();
			if (text == null || text.trim().isEmpty()) {
				MainWindow.alert("Please copy text into the clipboard!");
				return false;
			}
			String result = transform(text);
			if (result != null) {
				clipboard.setText(result);
			}
			return result != null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String transform(String text) throws IOException {
		System.out.println("IN :  " + text);
		Binding binding = new Binding();
		binding.setVariable("text", text);
		GroovyShell shell = new GroovyShell(binding);
		Object result = shell.evaluate(new String(Files.readAllBytes(Paths.get("script.groovy"))));
		String str;
		if (result instanceof org.codehaus.groovy.runtime.GStringImpl) {
			str = ((org.codehaus.groovy.runtime.GStringImpl) result).toString();
		} else {
			str = (String) result;
		}
		System.out.println("OUT:  " + str);
		return str;
	}
}
