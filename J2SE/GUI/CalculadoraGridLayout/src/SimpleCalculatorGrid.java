import javax.swing.*;
import java.awt.GridLayout;

public class SimpleCalculatorGrid {
	
	public static void main(String[] args){
		//Crear un panel
		JPanel windowContent= new JPanel();
		
		//Definir un administrador de diseño para este panel
		//Cuadricula de cuatro filas y dos columnas
		GridLayout g1 = new GridLayout(4,2);
		windowContent.setLayout(g1);
		
		//Crear controles en memoria
		JLabel label1 = new JLabel("Number 1:");
		JTextField field1 = new JTextField(10);
		
		JLabel label2 = new JLabel("Number 2:");
		JTextField field2 = new JTextField(10);
		
		JLabel label3 = new JLabel ("Sum");
		JTextField result = new JTextField(10);
		JButton go = new JButton("Add");
		
		//Añadir controles al panel
		windowContent.add(label1);
		windowContent.add(field1);
		
		windowContent.add(label2);
		windowContent.add(field2);
		
		windowContent.add(label3);
		windowContent.add(result);
		
		windowContent.add(go);
		
		//Crear el marco y añadirle el panel 
		JFrame frame = new JFrame("Mi calculadora con CridLayout");
		
		//Añadir el panel al contenedor de nivel superior
		frame.setContentPane(windowContent);
		
		//definir el tamaño y mostrar la ventana
		frame.setSize(400,400);
		frame.setVisible(true);
	}


}
