package es.uned.master.java.kwic.controlador;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public final class KwicFormArea extends JTextArea {
	private JTextArea displayArea;

	public KwicFormArea(JTextArea display) {
		this.displayArea = display;
	}
	
	// Recogemos el valor del display
	public String getText() {
		return displayArea.getText();
	}

	// Escribimos la salida en el display
	public void setDisplayValue(String val) {
		displayArea.setText(val);
	}
}
