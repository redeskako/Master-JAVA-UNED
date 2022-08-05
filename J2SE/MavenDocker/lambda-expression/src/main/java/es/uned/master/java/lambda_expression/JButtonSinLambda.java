package es.uned.master.java.lambda_expression;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * @author: Carlos Luis Sánchez Bocanegra
 * Este ejemplo básico enseña como funciona las expresiones lambda.
 * Para ello usamos un ejemplo básico como es el pulsar un botón dentro de una ventana
 * Para ello lo vamos a unsar con un Interfaz existente ActionListener
 * Es un interfaz funcional (con sólo un método) y que puede llevar a implementarlo
 */
public class JButtonSinLambda {

	public JButtonSinLambda() {
	}

	/*
	 * Este método se ejecuta sin llevar a cabo la Lambda
	 * Así vemos como se instancia con una clase anónima que implementa el interfaz ActionListener
	 */
	public void sinLambda() {
		JFrame frame=new JFrame("Prueba de ActionListener en JAVA 8");

		JButton b=new JButton("Púlsame");
		b.setBounds(50,100,80,50);

		// Para atender a eventos, uno de ellos es el de botón está asociado al actionListener
		// Para ello asocio al botón b el evento actionPerformed
		// Esta clase anónima que creamos en esta sentencia lleva a cabo la implementación del interfaz
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Cuando pulse el botón 'b' escribiré en consola el texto Has pulsado el botón!
				System.out.println("Has pulsado el botón!");
			}
		});

		// Configuración de adaptación a la ventana Swing.
		frame.add(b);
		frame.setSize(200,200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * En este ejemplo repetimos el mismo trabajo pero con Lambda expression
	 * Para ello usamos la notación usando '->' como indicación del parámetro
	 * () -> {};
	 * El paréntesis contemplará los paramétros (pueden obviarse si solo hay uno
	 * En la derecha se expresa la serie de instrucciones que ejecutaríamos sobre el interfaz
	 */
	public void conLambda() {
		JFrame frame=new JFrame("Listener con JAVA 8 y Lambda");
		JButton b=new JButton("Púlsame");
		b.setBounds(50,100,80,50);
		b.addActionListener(e -> System.out.println("Has pulsado botón con lambda!"));

		// Configuración de adaptación a la ventana Swing
		frame.add(b);
		frame.setSize(200,200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void sinparametrosLambda() {
		MiInterfazFuncional1 msg = () -> {
			return "Hola desde Lambda sin expresión";
		};
		System.out.println("Ejemplo Lambda sin parámetros :" + msg.diHola());
	}
	
	public void unparametroLambda() {
		MiInterfazFuncional2 f = (num) -> num+5;
		System.out.println("Ejemplo Lambda con 1 parámetros: " + f.incrementa5(5));
	}
	public static void main(String[] args) {
		JButtonSinLambda prueba = new JButtonSinLambda();

//		System.out.println("Ejemplo de Lambda con un interfaz propio");

		// Otra prueba de lambda sin parámetros
		// Para ello uso el interfaz que he creado MiInterfazFunciona1
		prueba.sinparametrosLambda();
		// Otra prueba de lambda sin parámetros
		// Para ello uso el interfaz que he creado MiInterfazFunciona2
		prueba.unparametroLambda();

		System.out.println("Ejemplo de Lambda con un interfaz ActionListener");
		// Ejecuto una ventana con un botón que manda mensaje a consola sin Lambda
//		prueba.sinLambda();
		// Ejecuto una ventana con un botón que manda mensaje a consola con Lambda
//		prueba.conLambda();
	}
}

//@FunctionalInterface
interface MiInterfazFuncional1{
	// Sin parámetros.
	public String diHola();
}

interface MiInterfazFuncional2{
	// Un parámetro.
	public int incrementa5(int a);
}