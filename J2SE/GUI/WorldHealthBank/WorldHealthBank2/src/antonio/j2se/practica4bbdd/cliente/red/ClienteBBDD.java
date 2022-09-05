package antonio.j2se.practica4bbdd.cliente.red;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import antonio.j2se.practica4bbdd.cliente.vista.AppletBBDD;
import antonio.j2se.practica4bbdd.excepciones.ErrorComunicacionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.ErrorConfiguracionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.NegocioException;
import antonio.j2se.practica4bbdd.operaciones.OperacionesBBDD;
import antonio.j2se.practica4bbdd.servidor.modelo.Estadistica;
import antonio.j2se.practica4bbdd.servidor.modelo.Indicador;
import antonio.j2se.practica4bbdd.servidor.modelo.Pais;

/**
 * Clase que se encarga de realizar la comunicacion con el Servidor
 * Cada metodo sigue leyendo hasta recibir el mensaje "FINDATOS" desde el servidor 
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class ClienteBBDD {
    private AppletBBDD applet;
		
    
	public ClienteBBDD(AppletBBDD applet){
		this.applet=applet;
		
		
	}
	
	/**
	 * Devuelve la pagina indicada(limite) de paises a partir del offset
	 * @param limite
	 * @param offset
	 * @throws ErrorComunicacionNegocioException
	 */
    public void obtenerPaises(int limite,int offset)throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando Paises");
	    	direccion =applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.ALLPAISES,limite,offset);
	    	//recibimos los Paises
	        String mensaje="";
	        ArrayList<Pais>paises=new ArrayList<Pais>();
		     do{	
			     Object leido=entrada.readObject();
			     if(leido instanceof Pais){
			    	 paises.add((Pais)leido);
			     }else if(leido instanceof String){
			    	 mensaje=(String)leido;  
			     }else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }
		     } while(!mensaje.equals("FINDATOS"));
		     //si servidor ha comunicado error,elevo error para su gestion
		     if(errorNegocio!=null)
		    	  throw errorNegocio;
		     //Establezco los paises al applet indicando que NO ha habido filtro
	    	  applet.setPaises(paises,null);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }

	/**
	 * Devuelve la pagina indicada(limite) de indicadores a partir del offset
	 * @param limite
	 * @param offset
	 * @throws ErrorComunicacionNegocioException
	 */
      public void obtenerIndicadores(int limite,int offset) throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando Indicadores");
	    	direccion = applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.ALLINDICADORES,limite,offset);
	    	//recibimos los Paises
	        String mensaje="";
	        ArrayList<Indicador>indicadores=new ArrayList<Indicador>();
		     do{	
			     Object leido=entrada.readObject();
			     if(leido instanceof Indicador){
			    	 indicadores.add((Indicador)leido);
			     }else if(leido instanceof String){
			    	 mensaje=(String)leido;  
			     }else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }	 
		     } while(!mensaje.equals("FINDATOS"));
		       //si servidor ha comunicado error,elevo error para su gestion
    		   if(errorNegocio!=null)
		    	  throw errorNegocio;
		      //establezco los indicadores en el applet e indico que NO ha habido filtro por pais
	    	  applet.setIndicadores(indicadores,null);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }

    	/**
    	 * Devuelve la pagina indicada(limite) de paises por indicador a partir del offset
    	 * @param limite
    	 * @param offset
    	 * @param codIndicador
    	 * @throws ErrorComunicacionNegocioException
    	 */
      public void obtenerPaisesByIndicador(int limite,int offset,String codIndicador) throws ErrorComunicacionNegocioException{
      	InetAddress direccion=null;
      	Socket servidor=null;
      	DataOutputStream salida=null;
  	    ObjectInputStream entrada=null;
  	    ErrorComunicacionNegocioException errorNegocio=null;
  	    try{
  	    	System.out.println("Solicitando Paises por Indicador:"+codIndicador);
  	    	direccion = applet.getDireccionIPServidor();
  	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
  	    	salida=new DataOutputStream(servidor.getOutputStream());
  	    	entrada=new ObjectInputStream(servidor.getInputStream());
  	    	salida.flush();
  	    	enviaDatosComunes(salida,OperacionesBBDD.PAISESBYINDICADOR,limite,offset);
  	    	//Enviamos el codigo de Indicador
  	    	salida.writeUTF(codIndicador);
  	    	//recibimos los Paises
  	        String mensaje="";
  	        ArrayList<Pais>paises=new ArrayList<Pais>();
  		     do{	
  			     Object leido=entrada.readObject();
  			     if(leido instanceof Pais){
  			    	 paises.add((Pais)leido);
  			     }else if(leido instanceof String){
  			    	 mensaje=(String)leido;  
  			     }else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }	 
  		     } while(!mensaje.equals("FINDATOS"));
		       //si servidor ha comunicado error,elevo error para su gestion
     		   if(errorNegocio!=null)
		    	  throw errorNegocio;
 		      //establezco los paises en el applet e indico que SI ha habido filtro por indicador
  	    	  applet.setPaises(paises,codIndicador);
  	    }catch(UnknownHostException uhe){
  	    	uhe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
  	    }catch(IOException ioe){
  	    	ioe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
  	    }catch (ClassNotFoundException cnfe){
  	    	cnfe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
  	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
      }
      
      
    	/**
    	 * Devuelve la pagina indicada(limite) de indicadores por pais a partir del offset
    	 * @param limite
    	 * @param offset
    	 * @param codPais
    	 * @throws ErrorComunicacionNegocioException
    	 */
      public void obtenerIndicadoresByPais(int limite,int offset,String codPais) throws ErrorComunicacionNegocioException{
      	InetAddress direccion=null;
      	Socket servidor=null;
      	DataOutputStream salida=null;
  	    ObjectInputStream entrada=null;
  	    ErrorComunicacionNegocioException errorNegocio=null;
  	    try{
  	    	System.out.println("Solicitando Indicadores por Pais:"+codPais);
  	    	direccion = applet.getDireccionIPServidor(); 
  	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
  	    	salida=new DataOutputStream(servidor.getOutputStream());
  	    	entrada=new ObjectInputStream(servidor.getInputStream());
  	    	salida.flush();
  	    	enviaDatosComunes(salida,OperacionesBBDD.INDICADORESBYPAIS,limite,offset);
  	    	//Enviamos el codigo de Pais
  	    	salida.writeUTF(codPais);
  	    	//recibimos los Indicadores
  	        String mensaje="";
  	        ArrayList<Indicador>indicadores=new ArrayList<Indicador>();
  		     do{	
  			     Object leido=entrada.readObject();
  			     if(leido instanceof Indicador){
  			    	 indicadores.add((Indicador)leido);
  			     }else if(leido instanceof String){
  			    	 mensaje=(String)leido;  
  			     }else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }	 
  		     } while(!mensaje.equals("FINDATOS"));
		       //si servidor ha comunicado error,elevo error para su gestion
     		   if(errorNegocio!=null)
	  	    	  throw errorNegocio;
   		   //establezco los indicadores en el applet e indico que ha habido filtro por pais,pasando el codPais
  	    	  applet.setIndicadores(indicadores,codPais);
  	    }catch(UnknownHostException uhe){
  	    	uhe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
  	    }catch(IOException ioe){
  	    	ioe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
  	    }catch (ClassNotFoundException cnfe){
  	    	cnfe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
  	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
      }
      

    	/**
    	 * Devuelve las estadisticas para un indicador y un pais
    	 * @param codIndicador
    	 * @param codPais
    	 * @throws ErrorComunicacionNegocioException
    	 */
      public void obtenerEstadisticaByIndicadorPais(String codIndicador,String codPais) throws ErrorComunicacionNegocioException{
      	InetAddress direccion=null;
      	Socket servidor=null;
      	DataOutputStream salida=null;
  	    ObjectInputStream entrada=null;
  	    ErrorComunicacionNegocioException errorNegocio=null;
  	    try{
  	    	System.out.println("Solicitando Estadisticas por Indicador: "+codIndicador+" y Pais:"+codPais);
  	    	direccion = applet.getDireccionIPServidor(); 
  	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
  	    	salida=new DataOutputStream(servidor.getOutputStream());
  	    	entrada=new ObjectInputStream(servidor.getInputStream());
  	    	salida.flush();
  	    	enviaDatosComunes(salida,OperacionesBBDD.ESTADISTICASBYINDICADORPAIS,0,0);
  	    	//Enviamos el codigo de Pais
  	    	salida.writeUTF(codIndicador);
  	    	salida.writeUTF(codPais);
  	    	//recibimos las Estadisticas
  	        String mensaje="";
  	        ArrayList<Estadistica>estadistica=new ArrayList<Estadistica>();
  		     do{	
  			     Object leido=entrada.readObject();
  			     if(leido instanceof Estadistica){
  			    	 estadistica.add((Estadistica)leido);
  			     }else if(leido instanceof String){
  			    	 mensaje=(String)leido;  
  			     }else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }	 
  		     } while(!mensaje.equals("FINDATOS"));
		       //si servidor ha comunicado error,elevo error para su gestion
    		   if(errorNegocio!=null)
 	  	    	  throw errorNegocio;
  	    	  applet.setEstadisticas(estadistica);
  	    }catch(UnknownHostException uhe){
  	    	uhe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
  	    }catch(IOException ioe){
  	    	ioe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
  	    }catch (ClassNotFoundException cnfe){
  	    	cnfe.printStackTrace();
  	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
  	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
      }
      
      
      
      /**
     * Obtiene el total de paises
     * Este dato se utiliza para mostrar en el GUI informacion sobre total de registros y paginas
     * @throws ErrorComunicacionNegocioException
     */
    public void obtenerPaisesCount()throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    int numeroPaises=-1;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando Paises Count");
	    	direccion = applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.ALLPAISESCOUNT,0,0);
	    	String mensaje="";
	    	do{
	    	   //recibimos el numero de paises
	    		Object leido=entrada.readObject();
	    		if(leido instanceof Integer){
	    			numeroPaises=(Integer)leido;
	    		}else if(leido instanceof String){
	    			mensaje=(String)leido;
	    		}else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }
	    	}while (!mensaje.equals("FINDATOS"));	
		     //si servidor ha comunicado error
		     if(errorNegocio!=null)
		    	  throw errorNegocio;
	    	applet.setPaisesCount(numeroPaises);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }
    
    /**
     * Obtiene el total de indicadores
     * Este dato se utiliza para mostrar en el GUI informacion sobre total de registros y paginas
     * @throws ErrorComunicacionNegocioException
     */
    public void obtenerIndicadoresCount() throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    int numeroIndicadores=-1;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando Indicadotes Count");
	    	direccion = applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.ALLINDICADORESCOUNT,0,0);
	    	String mensaje="";
	    	do{
	    	   //recibimos el numero de indicadores
	    		Object leido=entrada.readObject();
	    		if(leido instanceof Integer){
	    			numeroIndicadores=(Integer)leido;
	    		}else if(leido instanceof String){
	    			mensaje=(String)leido;
	    		}else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }
	    	}while (!mensaje.equals("FINDATOS"));	
		     //si servidor ha comunicado error
		     if(errorNegocio!=null)
		    	  throw errorNegocio;
	    	applet.setIndicadoresCount(numeroIndicadores);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }

    /**
     * Obtiene el total de indicadores por pais
     * Este dato se utiliza para mostrar en el GUI informacion sobre total de registros y paginas
     * @throws ErrorComunicacionNegocioException
     */
    public void obtenerIndicadoresByPaisCount(String codigoPais) throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    int numeroIndicadores=-1;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando IndicadoresByPais Count para codPais: "+codigoPais);
	    	direccion = applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.INDICADORESBYPAISCOUNT,0,0);
	    	//enviamos el codigo de Pais sobre el que filtrar
	    	salida.writeUTF(codigoPais);
	    	String mensaje="";
	    	do{
	    	   //recibimos el numero de indicadores
	    		Object leido=entrada.readObject();
	    		if(leido instanceof Integer){
	    			numeroIndicadores=(Integer)leido;
	    		}else if(leido instanceof String){
	    			mensaje=(String)leido;
	    		}else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }
	    	}while (!mensaje.equals("FINDATOS"));	
		     //si servidor ha comunicado error
		     if(errorNegocio!=null)
		    	  throw errorNegocio;
		     //Establecemos el contador
	        applet.setIndicadoresCount(numeroIndicadores);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }
    
    /**
     * Obtiene el total de paises por indicador
     * Este dato se utiliza para mostrar en el GUI informacion sobre total de registros y paginas
     * @throws ErrorComunicacionNegocioException
     */
    public void obtenerPaisesByIndicadorCount(String codigoIndicador) throws ErrorComunicacionNegocioException{
    	InetAddress direccion=null;
    	Socket servidor=null;
    	DataOutputStream salida=null;
	    ObjectInputStream entrada=null;
	    int numeroPaises=-1;
	    ErrorComunicacionNegocioException errorNegocio=null;
	    try{
	    	System.out.println("Solicitando PaisesByIndicador Count para codIndicador: "+codigoIndicador);
	    	direccion = applet.getDireccionIPServidor(); 
	    	servidor=new Socket(direccion, applet.getPuertoServidor());	
	    	salida=new DataOutputStream(servidor.getOutputStream());
	    	entrada=new ObjectInputStream(servidor.getInputStream());
	    	salida.flush();
	    	enviaDatosComunes(salida,OperacionesBBDD.PAISESBYINDICADORCOUNT,0,0);
	    	//enviamos el codigo de Indicador sobre el que filtrar
	    	salida.writeUTF(codigoIndicador);
	    	String mensaje="";
	    	do{
	    	   //recibimos el numero de paises
	    		Object leido=entrada.readObject();
	    		if(leido instanceof Integer){
	    			numeroPaises=(Integer)leido;
	    		}else if(leido instanceof String){
	    			mensaje=(String)leido;
	    		}else if(leido instanceof NegocioException){
			    	 //Se ha producido un error en el servidor
			    	 errorNegocio=new ErrorComunicacionNegocioException(((NegocioException)leido).getMessage(),((NegocioException)leido));
			     }
	    	}while (!mensaje.equals("FINDATOS"));	
		     //si servidor ha comunicado error
		     if(errorNegocio!=null)
		    	  throw errorNegocio;
	    	applet.setPaisesCount(numeroPaises);
	    }catch(UnknownHostException uhe){
	    	uhe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Destino no alcanzable.Verifique posibles problemas de comunicacion",uhe);
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Problema en la comunicacion con el servidor.Verifique posibles problemas de comunicacion",ioe);
	    }catch (ClassNotFoundException cnfe){
	    	cnfe.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Informacion erronea recibida.Verifique posibles problemas de comunicacion",cnfe);
	    }catch(ErrorConfiguracionNegocioException ecne){
	    	ecne.printStackTrace();
	    	throw new ErrorComunicacionNegocioException("Error al establecer comunicacion.Revise correcta configuracion de puerto y direccionIP",ecne);
	    }
    }
    

    
    /**
     * Envia al servidor los datos comunes de todas las operaciones
     * @param salida
     * @param operacion
     * @param limite
     * @param offset
     * @throws IOException
     */
    protected void enviaDatosComunes(DataOutputStream salida,OperacionesBBDD operacion,int limite,int offset) throws IOException{
    	 //enviamos la operacion
    	 salida.writeInt(OperacionesBBDD.getOperacion(operacion));
    	   	 //enviamos el limite de registros
    	 salida.writeInt(limite);
    	 //enviamos el offset
    	 salida.writeInt(offset);
    }
    

	
}
