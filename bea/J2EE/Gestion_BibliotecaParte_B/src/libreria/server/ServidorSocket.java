/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria.server;

import java.io.*;
import java.net.*;
import libreria.lenguaje.*;
import libreria.error.*;
import java.util.*;

import javax.swing.JTextArea;

public class ServidorSocket extends Thread{
	public JTextArea log;
	public static final String HOST="localhost";
	public static final int PARADO=0;
	public static final int INICIADO=1;
	public static final int MAXCLIENTES=3;
	private Config idioma;
	private int puerto;
	public static final String IMPOSIBLE="_COMIMPOSIBLE";
	private ServerSocket servidor;
	private LinkedList<CLibreriaServer> clientesMuertos;
	private LinkedList<CLibreriaServer> clientes;
	
	public ServidorSocket(int puerto, String local){
		super();
		this.log = new JTextArea();
		this.idioma = new Config(local);
		this.clientes = new LinkedList<CLibreriaServer>();
		this.clientesMuertos = new LinkedList<CLibreriaServer>();
		this.log.setText("");
		this.puerto = puerto;
	}
		private void comunicacionImposible(Socket s,String mensaje){
			try{
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				ObjectInputStream entrada = new ObjectInputStream(is);
				
				String sql = (String) entrada.readObject();

				//Calculo los bytes a enviar primeramente
				ObjectOutputStream salida = new ObjectOutputStream(os);
				//Recepcion perfecta.
				salida.writeObject(Config.ERROR);
				salida.writeObject(mensaje);
				
				entrada.close();
				os.close();
				is.close();
			}catch(ClassNotFoundException e){
				throw new ErrorLibreriaException(""+e);
			}catch(IOException e){
				throw new ErrorLibreriaException(""+e);
			}finally{
				try{
					s.close();
				}catch(Exception e){}
			}			
		}
		private synchronized boolean nohaylibres(){
			for (int i=0;i<this.clientes.size();i++){			
				CLibreriaServer cli = this.clientes.get(i);
				if (cli.estado() == CLibreriaServer.INICIADO){
					i++;
				}else{
					//Debería guardar el valor de resultado de socket
					this.clientes.remove(i);
					this.clientesMuertos.add(cli);
				}
			}
			return (this.clientes.size() < ServidorSocket.MAXCLIENTES);
		}
		public void iniciaServidor(){
		try{
			if (this.servidor == null){
				this.servidor = new ServerSocket(this.puerto);
			}else if (this.servidor.isClosed()){
				this.servidor = new ServerSocket(this.puerto);
			}
			this.log.append("\nIniciando servidor...");
			this.log.append("\n------------------");
		}catch(IOException e){
			this.log.append(this.idioma.traduce("_ERRORLECTURA"));
		}
	}
		private void cliente(){
			
			this.log.append("\n");
			try{
				while(true){
					   Socket s = this.servidor.accept();
						this.log.append("\nRecepción de cliente....");
						if (nohaylibres()){
							CLibreriaServer cl = new CLibreriaServer(s);
							this.log.append("\nCliente:"+cl.toString());
							this.clientes.add(cl);
							cl.start();
						}else{
							this.comunicacionImposible(s,IMPOSIBLE);
							this.log.append("\nMuchos clientes");
						}
				 }
			}catch(IOException e){}
		}
			public void grabaRes(){
				FileWriter fw = null;
				PrintWriter salida = null;
				try{
					fw = new FileWriter("resServer.txt");
					salida = new PrintWriter(fw);
					salida.println("\nClientes activos:"+this.clientes.size()+"\n");
					for (int i = 0; i < this.clientes.size(); i++){
						salida.println("\t" + this.clientes.toString()+"\n");
					}
					salida.println("\nCliente no activos:" + this.clientesMuertos.size()+"\n");
					for (int i = 0 ; i < this.clientesMuertos.size(); i++){
						salida.println("\t" + this.clientesMuertos.toString() + "\n");
						salida.println("\nTiempo de actividad: " + this.clientesMuertos.get(i).getTime()+" mseg.");
					}
				}catch(IOException e){
					this.log.append(this.idioma.traduce("Error de Lectura"));
				}finally{
					try{
						fw.close();
					}catch(Exception e){}
				}
			}
			private String eliminaClientes(){
				String res = "\nGrabando a fichero....";
				this.grabaRes();
				for (int i = 0 ; i < this.clientes.size(); i++){
					this.clientes.remove(i);
				}
				for (int i = 0; i < this.clientesMuertos.size(); i++){
					this.clientesMuertos.remove(i);
				}
				return res;
			}
	public void pararServidor(){
		this.log.append("\nParando servidor...");
		try{
			this.servidor.close();
			this.eliminaClientes();
		}catch(Exception e){}
		this.log.append("----------------------");
	}
	public void run(){
		if (this.servidor!=null){
			this.cliente();
		}
	}
	public void start(){
		this.iniciaServidor();
		super.start();
	}
}