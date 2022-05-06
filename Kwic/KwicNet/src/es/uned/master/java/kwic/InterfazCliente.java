package es.uned.master.java.kwic;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.uned.master.java.kwic.red.cliente.Cliente;
import es.uned.master.java.kwic.red.cliente.GuardarArchivo;

public class InterfazCliente extends JPanel implements ActionListener{
	
	private JLabel labelNoClaves;
	private JTextArea areaNoClaves;
	private JLabel labelFrases;
	private JTextArea areaFrases;
	private JLabel labelKwic;
	private JTextArea areaKwic;
	private JButton botonGuardar;
	private JTextField datosArchivoCargar;
	private JButton botonConectar;
	private JButton botonDesconectar;
	private JTextField estadoConexion;
	private Cliente cliente;


public void setDatosArchivoCargar(String aux){
	datosArchivoCargar.setText(aux);
}
public void setAreaNoClaves(String aux){
	areaNoClaves.setText(aux);
}
public String getAreaNoClaves(){
	return areaNoClaves.getText();
}
public void setAreaFrases(String aux){
	areaFrases.setText(aux);
}
public String getAreaFrases(){
	return areaFrases.getText();
}
public void setAreaKwic(String aux){
	areaKwic.setText(aux);
}
public String getAreaKwic(){
	return areaKwic.getText();
}
public void setBotonGuardar(Boolean aux){
	botonGuardar.setEnabled(aux);
}
public void setBotonConectar(Boolean aux){
	botonConectar.setEnabled(aux);
}
public void setBotonDesconectar(Boolean aux){
	botonDesconectar.setEnabled(aux);
}

public void setEstadoConexion(Boolean aux){
	//Cambiamos el color del boton y el texto del mismo
	if(aux){//Si es true ponemos el fondo verde y el nombre "Conectado"
		estadoConexion.setBackground(Color.GREEN);
		estadoConexion.setText("Conectado");
	}
	else{
		estadoConexion.setBackground(Color.red);
		estadoConexion.setText("Desconectado");
	}
}

public void crearInterfaz() {
		//Creamos un diseno de tipo GridBagLayout pare el panel
		GridBagLayout gl = new GridBagLayout();
		// Asignamos el diseno al panel
		this.setLayout(gl);
		//Creamos una variable de tipo GridBagConstraints para definir los parametros de cada celda
		GridBagConstraints constraints = new GridBagConstraints();


		labelNoClaves = new JLabel("Datos No claves");
		constraints.gridx = 0;// El elemento esta en la columna 0
		constraints.gridy = 0;// El elemento esta en la fila 0
		constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints.gridheight = 1;// Ocupa 1 celda verticalmente
		// Aparecece pegado a la parte izquierda
		constraints.anchor = GridBagConstraints.WEST;
		/* Hacemos un padding en todos los componentes (al no resetearlo se
	 	   creaian todos los elementos con este padding)*/
		constraints.insets = new Insets(5, 5, 5, 5);
		this.add(labelNoClaves, constraints);
		// constraints.weightx = 0.0;
		// Posicion por defecto
		constraints.anchor = GridBagConstraints.CENTER;
														

		areaNoClaves = new JTextArea();
		areaNoClaves.setEditable(false);
		//Barra de scroll
		JScrollPane scrollIntroArea = new JScrollPane(areaNoClaves);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints.weightx = 1.0;
		// Para que rellene todas las celdas que ocupa
		constraints.fill = GridBagConstraints.BOTH;
		//añadimos al panel la barra de scroll
		this.add(scrollIntroArea, constraints);
		// Reseteamos los valores para que no afecten a las demas celdas
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		

		labelFrases = new JLabel("Frases");
		constraints.gridx = 0;// El elemento esta en la columna 0
		constraints.gridy = 2;// El elemento esta en la fila 0
		constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints.gridheight = 1;// Ocupa 1 celda verticalmente
		// Aparecece pegado a la parte izquierda
		constraints.anchor = GridBagConstraints.WEST;
		this.add(labelFrases, constraints);
		// constraints.weightx = 0.0;
		// Posicion por defecto
		constraints.anchor = GridBagConstraints.CENTER;
		
		areaFrases = new JTextArea();
		areaFrases.setEditable(false);
		//Barra de scroll
		JScrollPane scrollAreaFrases = new JScrollPane(areaFrases);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints.weightx = 1.0;
		// Para que rellene todas las celdas que ocupa
		constraints.fill = GridBagConstraints.BOTH;
		//añadimos al panel la barra de scroll
		this.add(scrollAreaFrases, constraints);
		// Reseteamos los valores para que no afecten a las demas celdas
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		labelKwic = new JLabel("Kwic");
		constraints.gridx = 0;// El elemento esta en la columna 0
		constraints.gridy = 4;// El elemento esta en la fila 0
		constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints.gridheight = 1;// Ocupa 1 celda verticalmente
		// Aparecece pegado a la parte izquierda
		constraints.anchor = GridBagConstraints.WEST;
		this.add(labelKwic, constraints);
		// constraints.weightx = 0.0;
		// Posicion por defecto
		constraints.anchor = GridBagConstraints.CENTER;
		
		areaKwic = new JTextArea();
		areaKwic.setEditable(false);
		//Barra de scroll
		JScrollPane scrollAreaKwic = new JScrollPane(areaKwic);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints.weightx = 1.0;
		// Para que rellene todas las celdas que ocupa
		constraints.fill = GridBagConstraints.BOTH;
		//añadimos al panel la barra de scroll
		this.add(scrollAreaKwic, constraints);
		// Reseteamos los valores para que no afecten a las demas celdas
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		
		
		datosArchivoCargar = new JTextField();
		datosArchivoCargar.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(datosArchivoCargar, constraints);
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;
		

		botonGuardar = new JButton("Guardar");
		botonGuardar.setName("guardar");
		//Deshabilitamos el boton para que no se pueda guardar un archivo si no se han introducido datos
		botonGuardar.setEnabled(false);
		constraints.gridx = 3;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(botonGuardar, constraints);
		//constraints.weightx = 0.0;
		
			

		botonConectar = new JButton("Conectar");
		botonConectar.setName("conectar");
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		//constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(botonConectar, constraints);
		//constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		

		botonDesconectar = new JButton("Desconectar");
		botonDesconectar.setName("desconectar");
		botonDesconectar.setEnabled(false);
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(botonDesconectar, constraints);
		//constraints.weightx = 0.0;
		
		estadoConexion = new JTextField("Desconectado");
		estadoConexion.setEditable(false);
		estadoConexion.setBackground(Color.RED);
		constraints.gridx = 3;
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(estadoConexion, constraints);
		//constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;

		JFrame frame = new JFrame("Cliente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(this);
		frame.setSize(500, 600);
		frame.setVisible(true);
		
		/************** Instancia controlador y Eventos *******************/		

		
		//botonGuardar.setAction(Cliente(this));
		botonGuardar.addActionListener(this);
		botonConectar.addActionListener(this);
		botonDesconectar.addActionListener(this);
}

		public void actionPerformed(ActionEvent ev) {
			System.out.println("Entra en el actionPerformed");
			Object eventoLlamado = ev.getSource();
			String botonPulsado = ((JButton) eventoLlamado).getName();

		System.out.println("Evento getName= "+botonPulsado);

        //if (botonPulsado == "guardar") {}
        if(botonPulsado=="conectar"){
        	System.out.println("Se ha pulsado el boton conectar");
        	setBotonConectar(false);
        	setBotonDesconectar(true);
            (cliente = new Cliente(this)).execute();
        }
        else if (botonPulsado=="desconectar"){
        	System.out.println("Se ha pulsado el boton desconectar");
        	setBotonConectar(true);
        	setBotonDesconectar(false);
            cliente.cancel(true);
            cliente = null;

        
        	}
        else if (botonPulsado=="guardar"){
    		GuardarArchivo GA= new GuardarArchivo(ev, this);
    		GA.start();
        }
        	
        }
        
		
/*******************************************************************/		
     

	public static void main( String args[] )
    {
		SwingUtilities.invokeLater(new Runnable(){  
            public void run(){ 
		new InterfazCliente().crearInterfaz();
            }  
        });  
    }

}