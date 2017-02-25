package de.mwvb.base;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Clipboard implements ClipboardOwner {
	// http://www.javapractices.com/topic/TopicAction.do?Id=82

	public void setText(String text) {
		StringSelection stringSelection = new StringSelection(text);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, this);
	}

	public String getText() {
		String result = "";
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException ex) {
				ex.printStackTrace();
				return "";
			}
		}
		return result;
	}
	
	@Override
	public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, Transferable transferable) { //
	}
}
