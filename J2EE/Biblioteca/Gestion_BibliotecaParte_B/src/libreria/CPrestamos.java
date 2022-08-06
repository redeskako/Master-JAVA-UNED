/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import libreria.error.*;
import libreria.lenguaje.*;
import libreria.server.ServidorSocket;
import java.text.*;

public class CPrestamos extends DefaultTableModel implements TableModelListener{
	private Map<Integer,Prestamo> prestamos;
	private Map<Integer,Prestamo> prestamosMod;
	private final int BORRAR=1;
	private final int NUEVO=0;
	private final int MODIFICAR=2;
	public static final int BUSQUEDATOTAL=0;
	public static final int BUSQUEDALIBRO=1;
	public static final int BUSQUEDACLIENTE=2;
	public static final int DIA=0;
	public static final int MES=1;
	public static final int ANYO=3;
	public static final int COMPLETO=4;
	public final String [] ACTUALIZADO;
	private int [] resActualizado={0,0,0};
	public static final String NEW="*>";
	public static final String CPRESTAMO="CPRESTAMO";
	private int nuevo;
	private int encontrados;
	private Config idioma;
	private static Config idioma2;
	public String mensajeSocket;
	
	/**********************************************************************************
	 * Configuracion de la clase y tabla....
	 * @param tabla
	 **********************************************************************************/
		private void genera(){
			this.prestamos= new TreeMap<Integer,Prestamo>();
			this.prestamosMod= new TreeMap<Integer,Prestamo>();
			this.resActualizado[0]=0;
			this.resActualizado[1]=0;
			this.mensajeSocket="";
			this.resActualizado[2]=0;
			this.encontrados=0;
			this.conectaSQL("Select p.IdPrestamo, l.Nombre as IdLibro, CONCAT(s.DNI,' - ',s.Apellidos,' ',s.Nombre) as IdSocio, p.FechaInicio,p.FechaFin from Prestamos p, Libros l, Socios s where (p.idLibro=l.idLibro) and (s.idSocio=p.idSocio)");
		}
		public CPrestamos(Config  local){
			super();
			this.idioma= local;
			this.ACTUALIZADO= new String [3];
			this.ACTUALIZADO[0]=this.idioma.traduce("_NUEVO");
			this.ACTUALIZADO[1]=this.idioma.traduce("_BORRAR");
			this.ACTUALIZADO[2]=this.idioma.traduce("_ACTUALIZAR");
			this.nuevo=0;
			this.genera();
	}
		public boolean isCellEditable(int fila, int col){
			if (col>0){
				return true;
			}else{
				return false;
			}
		}		
		/**********************************************************************************
		 * Lectura de Datos de la tabla
		 * @param tabla
		 **********************************************************************************/
		// Redefinicion de DefaultTableModel
		public int getColumnCount(){
			return Prestamo.COLUMNAS;
		}
		public int getRowCount(){
			if (this.prestamos==null){
				return 0;
			}else{
				return this.prestamos.size();
			}
		}
		///Obtencion de contenidos de columnas
		public String getColumnName(int column){
			Prestamo s= new Prestamo(0,NEW,NEW,new java.sql.Date(System.currentTimeMillis()),null,this.idioma.idioma());
			return s.NOMCOL()[column];
		}
		/**********************************************************************************
		 * Paso de los valores Map al JTable
		 * @param tabla
		 **********************************************************************************/
		//Necesario para obtener el objeto referenciante.
		public Class<?> getColumnClass(int c) {
			//System.out.println("Clase: "+this.getValueAt(0, c).getClass().toString());
			return this.getValueAt(0,c).getClass();
		}		
		public Object getValueAt(int row, int column){
			Prestamo p= this.prestamos.get(this.prestamos.keySet().toArray()[row]);
			return (p.getColumn(column));
		}
		/**********************************************************************************
		 * Actualizaciones del JTable
		 * @param tabla
		 **********************************************************************************/	
				private void actualizaPrestamo(Object nuevo, int fil, int col){
						if (col>0){
							Prestamo p;
							//Obtengo la key original.
							Integer oldkey= (Integer) this.prestamos.keySet().toArray()[fil];
							p= this.prestamos.get(oldkey);
							if (p.estado()==Libro.BORRAR) return;
							if (p.estado()==Libro.NADA) p.estado(Libro.ACTUALIZAR);
							//if (l.estado()==Libro.NUEVO) l.estado(Libro.NUEVO);
							//if (l.estado()==Libro.ACTUALIZAR) l.estado(Libro.ACTUALIZAR);
							switch (col){
								case 1:
										//Está actualizando el idSocio
										//Primero en el map DNI
										p.idSocio((String) nuevo);
										this.prestamos.put(oldkey, p);
										//Segundo añadir los campos a 'modificar'
										this.prestamosMod.put(oldkey, p);
										break;
								case 2:
										//Este es mas facil solo modificar el idLibro
										p.idLibro((String) nuevo);
										this.prestamos.put(oldkey, p);
										//Segundo al map modificar
										this.prestamosMod.put(oldkey, p);
										break;
								case 3:
										//La fechaInicio
										p.fechaInicio((String) nuevo);
										this.prestamos.put(oldkey,p);
										this.prestamosMod.put(oldkey,p);
										break;
								case 4:
										// la fechafin
										p.fechaFin((String) nuevo);
										this.prestamos.put(oldkey,p);
										this.prestamosMod.put(oldkey,p);
										break;
							}
						}
						this.resActualizado[MODIFICAR]++;
					}
					private void borraPrestamo(Boolean tachado,int fil, int col){
						// Eliminar el registro
						Integer oldkey= (Integer) this.prestamos.keySet().toArray()[fil];
						Prestamo p= this.prestamos.get(oldkey);
						p.aEliminar(tachado.booleanValue());
						if (p.estado()==Libro.NUEVO){
							this.prestamos.remove(oldkey);
							this.prestamosMod.remove(oldkey);
							return;
						}
						if (tachado.booleanValue()){
							p.estado(Libro.BORRAR);
						}else{
							p.estado(Libro.NADA);
						}
						this.prestamosMod.put(oldkey,p);
						this.prestamos.put(oldkey,p);
						this.resActualizado[BORRAR]++;
					}
		public void setValueAt(Object o, int fil, int col){
			try{
				int sizeKey=this.prestamos.keySet().size();
				
				if (col==5) borraPrestamo((Boolean) o,fil,col);
				else actualizaPrestamo(o,fil,col);
				
				this.fireTableDataChanged();
			}catch(ClassCastException e){
				throw new ErrorLibreriaException(this.idioma.traduce("_CLASENODETECTADA"+e));
			}
		}		
		/**********************************************************************************
		 * EVENTOS
		 * @param tabla
		 **********************************************************************************/
		public void tableChanged(TableModelEvent te){
			//TableModel t=(TableModel) te.getSource();
		}
		/**********************************************************************************
		 * ToString
		 * @param tabla
		 **********************************************************************************/
		
/**********************************************************************************
 * Gestion con la bbbdd
 * @param tabla
 **********************************************************************************/
				private Map<Integer,Prestamo> actualizaValores(String valores){
					 Map<Integer,Prestamo> m= new TreeMap<Integer,Prestamo>();
					 StringTokenizer strk= new StringTokenizer(valores,"|");
					 while(strk.hasMoreTokens()){
						 int k= Integer.parseInt(strk.nextToken());
						 String idSocio= strk.nextToken();
						 String idLibro= strk.nextToken();
						 SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
						 java.sql.Date fInicio=null;
						 try{
							 fInicio= new java.sql.Date(df.parse(strk.nextToken()).getTime());
						 }catch(Exception e){}
						 java.sql.Date fFin=null;
						 try{
							 fFin= new java.sql.Date(df.parse(strk.nextToken()).getTime());
						 }catch(Exception e){}
						 
						 Prestamo p= new Prestamo(k,idSocio,idLibro,fInicio,fFin,this.idioma.idioma());
						 m.put(k, p);
					 }
					 return m;
				}
		public void conectaSQL(String tabla){
			try{
				Socket cliente= new Socket(ServidorSocket.HOST,this.idioma.puerto());
//System.out.println(cliente.toString());
				InputStream flujoEntrada= cliente.getInputStream();
				OutputStream os= cliente.getOutputStream();
				ObjectOutputStream salida= new ObjectOutputStream(os);
				
				salida.writeObject(tabla);
				salida.writeObject(CPrestamos.CPRESTAMO);
				
				ObjectInputStream entrada= new ObjectInputStream(flujoEntrada);
				String ack=(String) entrada.readObject();
				if (ack.equalsIgnoreCase(Config.OK)){
					String valores= (String) entrada.readObject();
					
					this.prestamos= new TreeMap<Integer,Prestamo>();
					this.prestamosMod= new TreeMap<Integer,Prestamo>();
					this.encontrados=0;
					this.prestamos= actualizaValores(valores);
					this.mensajeSocket="";
				}else{
					this.mensajeSocket=this.mensajeSocket=this.idioma.traduce("_COMIMPOSIBLE");
				}
				salida.close();
				flujoEntrada.close();
				os.close();
				cliente.close();
			}catch(IOException e){
				throw new ErrorLibreriaException(this.idioma.traduce("_ERRORLECTURA"));
			}catch(ClassNotFoundException e){
				throw new ErrorLibreriaException(this.idioma.traduce("_ERROROBJETO"));
			}
		}
			private int calculaId(String sql){
				int id=0;
				Connection con= null;
				Statement stm= null;
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
					stm= con.createStatement();
					ResultSet rst= stm.executeQuery(sql);
					while (rst.next()){
						id= rst.getInt("Id");
					}
					stm.close();
					con.close();
				}catch(SQLException e){
					throw new ErrorLibreriaException(this.idioma.traduce("_ERRORCONSULTA")+". "+e);
				}catch(Exception e){
					throw new ErrorLibreriaException(this.idioma.traduce("_ERRORNODEFINIDO")+". "+e);
				}finally{
					try{
						stm.close();
					}catch(Exception e){}				
					try{
						con.close();
					}catch(Exception e){}
				}				
				return id;
			}
			private String cadenaSQL(Prestamo p){
				String sql="";
				int socio=this.calculaId("SELECT IdSocio as Id from Socios where (DNI='"+p.idSocio()+"')");
				int libro =this.calculaId("SELECT IdLibro as Id from Libros where (Nombre='"+p.idLibro()+"')");
				switch(p.estado()){
					case Libro.ACTUALIZAR:
						sql="UPDATE Prestamos set idSocio="+socio;
						sql+=", idLibro="+libro;
						sql+=", FechaInicio='"+p.fechaInicioSQL();
						if (p.fechaFin()!=""){
							sql+="', FechaFin='"+p.fechaFinSQL();
						}
						sql+= "' where (idPrestamo="+p.idPrestamo()+")";
						
						break;
					case Libro.BORRAR:
						sql="DELETE FROM Prestamos WHERE (idPrestamo="+p.idPrestamo()+")";
						break;
					case Libro.NUEVO:
						sql= "INSERT INTO Prestamos (idSocio,idLibro,FechaInicio,FechaFin) VALUES (";
						sql+= socio+", "+libro+", '"+p.fechaInicioSQL()+"', '";
						if (p.fechaFinSQL()!=""){
							sql+=p.fechaFinSQL();
						}else{
							sql+= "0000-00-00";
						}
						sql+="')";
						break;
					default:	
				}
				return sql;
			}
		public void actualizaSQL(){
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				Iterator<Map.Entry<Integer,Prestamo>> it= this.prestamosMod.entrySet().iterator();
				while (it.hasNext()){
					Prestamo p= it.next().getValue();
					String sql= this.cadenaSQL(p);
					//System.out.println("Ejecuto:"+sql);
					stm.addBatch(sql);
				}
				int [] res= stm.executeBatch();
			}catch(SQLException e){
				throw new ErrorLibreriaException(this.idioma.traduce("_ERRORACTUALIZACION")+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException(this.idioma.traduce("_ERRORNODEFINIDO")+". "+e);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}	
				try{
					con.close();
				}catch(Exception e){}
			}			
		}
		public int [] cancela(){
			int [] r={0,0,0};
			r[0]=this.resActualizado[0];
			r[1]=this.resActualizado[1];
			r[2]=this.resActualizado[2];
			this.nuevo=0;
			this.genera();
			this.fireTableDataChanged();
			return r;
		}
		public void nuevo(){
			Prestamo p=new Prestamo(this.nuevo,NEW,NEW,new java.sql.Date(System.currentTimeMillis()),null,this.idioma.idioma());
			p.estado(Libro.NUEVO);
			this.prestamos.put(new Integer(this.nuevo),p);
			this.nuevo--;
			this.fireTableDataChanged();
			this.resActualizado[NUEVO]++;
		}
		public void actualizaIdioma(){
			this.fireTableDataChanged();
			this.fireTableStructureChanged();
		}
		public int [] aceptar(){
				int[] r={0,0,0};
				this.actualizaSQL(); //Actualizo la consulta
				r[0]= this.resActualizado[0]; //Devuelvo los cambios realizados
				r[1]= this.resActualizado[1];
				r[2]= this.resActualizado[2];
				this.genera(); //Recalculo consulta
				this.fireTableDataChanged();
				return r;
		}
		public static JComboBox carga(String sql,String local){
			idioma2= new Config(local);
			JComboBox combo= new JComboBox();
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				ResultSet rst= stm.executeQuery(sql);
				while (rst.next()){
					String valor= rst.getString("Nombre");
					combo.addItem(valor);
				}
				stm.close();
				con.close();
			}catch(SQLException err){
				throw new ErrorLibreriaException(idioma2.traduce("_ERRORCONSULTA")+". "+err);
			}catch(Exception err){
				throw new ErrorLibreriaException(idioma2.traduce("_ERRORNODEFINIDO")+". "+err);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}				
				try{
					con.close();
				}catch(Exception e){}
			}			
			return combo;
		}
		public int buscar(String busca,int razonBus){
			this.genera();
			this.prestamos= new TreeMap<Integer,Prestamo>();
			this.prestamosMod=new TreeMap<Integer,Prestamo> ();
			int res=0;
			String sql;
			switch(razonBus){
				case CPrestamos.BUSQUEDALIBRO:
					sql= "Select p.IdPrestamo, l.Nombre as IdLibro, ";
					sql+="CONCAT(s.DNI,' - ',s.Apellidos,' ',s.Nombre) as IdSocio, p.FechaInicio,";
					sql+="p.FechaFin from Prestamos p, Libros l, Socios s ";
					sql+="where (p.idLibro=l.idLibro) and (s.idSocio=p.idSocio) and (l.Nombre like '%"+busca+"%') order by l.Nombre"; 
//System.out.println("Busqueda: "+sql);
					this.conectaSQL(sql);
					res= this.encontrados;
					this.fireTableDataChanged();
					break;
				case CPrestamos.BUSQUEDACLIENTE:
					sql= "Select p.IdPrestamo, l.Nombre as IdLibro, ";
					sql+="CONCAT(s.DNI,' - ',s.Apellidos,' ',s.Nombre) as IdSocio, p.FechaInicio,";
					sql+="p.FechaFin from Prestamos p, Libros l, Socios s ";
					sql+="where (p.idLibro=l.idLibro) AND (s.idSocio=p.idSocio) and ";
					sql+="((s.DNI like '%"+busca+"%') OR (s.Nombre like '%"+busca+"%') OR (s.Apellidos like '%"+busca+"%')) order by s.Apellidos,s.Nombre"; 
//System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				default:
					sql= "Select p.IdPrestamo, l.Nombre as IdLibro, ";
					sql+="CONCAT(s.DNI,' - ',s.Apellidos,' ',s.Nombre) as IdSocio, p.FechaInicio,";
					sql+="p.FechaFin from Prestamos p, Libros l, Socios s ";
					sql+="where (p.idLibro=l.idLibro) and (s.idSocio=p.idSocio) and ((l.Nombre like '%"+busca+"%') OR ";
					sql+="(s.DNI like '%"+busca+"%') OR (s.Nombre like '%"+busca+"%') OR (s.Apellidos like '%"+busca+"%')) order by l.Nombre"; 
//System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
			}
			return res;
		}
		public int buscarFecha(String fecha, int tipofecha, String campo){
			int res=0;
			String sql="";
			String fechaValida="";
			this.genera();
			this.prestamos= new TreeMap<Integer,Prestamo>();
			this.prestamosMod= new TreeMap<Integer,Prestamo>();
			if (tipofecha==DIA){
				fechaValida="(DAY("+campo+")="+fecha+") OR (DAY(FechaFin)="+fecha+")";
			}
			if (tipofecha==MES){
				fechaValida="(MONTH("+campo+")="+fecha+") OR (DAY(FechaFin)="+fecha+")";
			}
			if (tipofecha==ANYO){
				fechaValida="(YEAR("+campo+")="+fecha+") OR (DAY(FechaFin)="+fecha+")";
			}
			if (tipofecha==COMPLETO){
				if ((fechaValida=Socio.validaFecha(fecha))!=null){
					fechaValida="("+campo+"='"+Socio.bfechaAltaSQL(fechaValida)+"')";
				}else{
					fechaValida= "("+campo+" is null) ";
				}
			}
				sql= "Select p.IdPrestamo, l.Nombre as IdLibro, ";
				sql+="CONCAT(s.DNI,' - ',s.Apellidos,' ',s.Nombre) as IdSocio, p.FechaInicio,";
				sql+="p.FechaFin from Prestamos p, Libros l, Socios s ";
				sql+="where (p.idLibro=l.idLibro) and (s.idSocio=p.idSocio) and "+fechaValida+" order by "+campo; 
				
System.out.println("Busqueda: "+sql);
				this.conectaSQL(sql);
				res= this.encontrados;
				this.fireTableDataChanged();
			return res;
		}		
	}

