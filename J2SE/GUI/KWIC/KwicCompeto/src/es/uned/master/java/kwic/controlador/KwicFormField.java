package es.uned.master.java.kwic.controlador;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class KwicFormField extends JTextField {
	private JTextField displayField;

	public KwicFormField(JTextField display) {
		this.displayField = display;
	}
	
	// Recogemos el valor del display
	public String getText() {
		return displayField.getText();
	}

	// Escribimos la salida en el display
	public void setDisplayValue(String val) {
		displayField.setText(val);
	}
}
