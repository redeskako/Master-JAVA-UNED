/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import libreria.error.*;
import libreria.lenguaje.*;

public class CSocios extends DefaultTableModel implements TableModelListener{
	private Map<String,Socio> socios;
	private Map<String,Socio> sociosMod;
	private final int BORRAR=1;
	private final int NUEVO=0;
	private final int MODIFICAR=2;
	public static final int BUSQUEDADNI=0;
	public static final int BUSQUEDANOMBRE=1;
	public static final int BUSQUEDAAPELLIDOS=2;
	public static final int BUSQUEDADIRECCION=3;
	public static final int BUSQUEDAFECHAALTA=4;
	public static final int BUSQUEDATOTAL=5;
	public static final int DIA=0;
	public static final int MES=1;
	public static final int ANYO=3;
	public static final int COMPLETO=4;
	public final String [] ACTUALIZADO;
	private int [] resActualizado={0,0,0};
	public static final String NEW="*>";
	private int encontrados;
	/**********************************************************************************
	 * Configuracion de la clase y tabla....
	 * @param tabla
	 **********************************************************************************/
		private void genera(){
			this.socios= new TreeMap<String,Socio>();
			this.sociosMod= new TreeMap<String,Socio>();
			this.resActualizado[0]=0;
			this.resActualizado[1]=0;
			this.resActualizado[2]=0;
			this.encontrados=0;
			this.conectaSQL("Select * from Socios order by Apellidos, Nombre");
		}
		public CSocios(){
		super();
		this.ACTUALIZADO= new String [3];
//		this.ACTUALIZADO[0]=Main.idioma.traduce("_NUEVO");
		this.ACTUALIZADO[1]=Main.idioma.traduce("_BORRAR");
		this.ACTUALIZADO[2]=Main.idioma.traduce("_ACTUALIZAR");
		
		genera();
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
			return Socio.COLUMNAS;
		}
		public int getRowCount(){
			if (this.socios==null){
				return 0;
			}else{
				return this.socios.keySet().size();
			}
		}
		///Obtencion de contenidos de columnas
		public String getColumnName(int column){
			Socio s= new Socio(0,"","","","",new java.sql.Date(System.currentTimeMillis()));
			return s.NOMCOL()[column].toString();
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
			return this.socios.get(this.socios.keySet().toArray()[row]).getColumn(column);
		}
		/**********************************************************************************
		 * Actualizaciones del JTable
		 * @param tabla
		 **********************************************************************************/	
				private void actualizaSocio(String nuevo, int fil, int col){
						if (col>0){
							Socio s;
							//Obtengo la key original.
							String oldkey= (String) this.socios.keySet().toArray()[fil];
							s= this.socios.get(oldkey);
							if (s.estado()==Libro.BORRAR) return;
							if (s.estado()==Libro.NADA) s.estado(Libro.ACTUALIZAR);
							//if (l.estado()==Libro.NUEVO) l.estado(Libro.NUEVO);
							//if (l.estado()==Libro.ACTUALIZAR) l.estado(Libro.ACTUALIZAR);
							switch (col){
								case 1:
										//Está actualizando la columna 'DNI'
										//Primero en el map DNI
										if (nuevo.length()>9){
											nuevo=nuevo.substring(0,10);
										}else if(nuevo.length()<9){
											int l=9-(nuevo.length());
											StringBuffer cad=new StringBuffer("");
											for (int i=0;i<l;i++){
												cad.append("0");
											}
											nuevo= cad.toString().toUpperCase()+nuevo.toUpperCase();
										}
										s.dni(nuevo);
										this.socios.remove(oldkey);
										this.socios.put(nuevo, s);
										//Segundo añadir los campos a 'modificar'
										this.sociosMod.put(nuevo, s);
										break;
								case 2:
										//Este es mas facil solo modificar el socio
										//Primero en socios apellidos
										s.apellidos(nuevo);
										this.socios.put(oldkey, s);
										//Segundo al map modificar
										this.sociosMod.put(oldkey, s);
										break;
								case 3:
									//El nombre
									//Primero en socios
									s.nombre(nuevo);
									this.socios.put(oldkey, s);
									//Segundo al map modificar
									this.sociosMod.put(oldkey, s);			
									break;
								case 4:
									//La direccion
									//primero en socios
									s.direccion(nuevo);
									this.socios.put(oldkey,s);
									//Segundo al map a modificar
									this.sociosMod.put(oldkey,s);
									break;
								case 5:
									//La fecha
									s.fechaAlta(nuevo);
									this.socios.put(oldkey,s);
									this.sociosMod.put(oldkey,s);
							}
						}
						this.resActualizado[MODIFICAR]++;
					}
					private void borraSocio(Boolean tachado,int fil, int col){
						// Eliminar el registro
						String oldkey= (String) this.socios.keySet().toArray()[fil];
						Socio s= this.socios.get(oldkey);
						s.aEliminar(tachado.booleanValue());
						if (s.estado()==Libro.NUEVO){
							this.socios.remove(oldkey);
							this.sociosMod.remove(oldkey);
							return;
						}
						if (tachado.booleanValue()){
							s.estado(Libro.BORRAR);
						}else{
							s.estado(Libro.NADA);
						}
						this.sociosMod.put(oldkey,s);
						this.socios.put(oldkey,s);
						this.resActualizado[BORRAR]++;
					}
		public void setValueAt(Object o, int fil, int col){
			try{
				int sizeKey=this.socios.keySet().size();
				
				if (col==6) borraSocio((Boolean) o,fil,col);
				else actualizaSocio((String) o,fil,col);
				
				this.fireTableDataChanged();
			}catch(ClassCastException e){
				throw new ErrorLibreriaException(Main.idioma.traduce("_CLASENODETECTADA"+e));
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
		
		public String toString(){
			String str=Main.idioma.traduce("_SOCIOS")+": ------\n";
			str+=this.socios.toString()+"\n";
			str+="A ACTUAR: -----\n";
			str+=this.sociosMod.toString()+"\n";
			return str;
		}
/**********************************************************************************
 * Gestion con la bbbdd
 * @param tabla
 **********************************************************************************/
		public void conectaSQL(String tabla){
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				ResultSet rst= stm.executeQuery(tabla);
				this.encontrados=0;
				while (rst.next()){
					String clave= rst.getString("DNI");
					java.sql.Date fechaAlta= rst.getDate("FechaAlta");
					String DNI=rst.getString("DNI");
					String apellidos=rst.getString("Apellidos");
					String nombre=rst.getString("Nombre");
					String direccion=rst.getString("Direccion");
					int i= rst.getInt("idSocio");
					Socio s= new Socio (i,DNI,apellidos,nombre,direccion,fechaAlta);
					this.socios.put(clave,s);
					this.encontrados++;
				}
				stm.close();
				con.close();
			}catch(SQLException e){
				throw new ErrorLibreriaException(Main.idioma.traduce("_ERRORCONSULTA")+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException(Main.idioma.traduce("_ERRORNODEFINIDO")+". "+e);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}				
				try{
					con.close();
				}catch(Exception e){}
			}
		}
			private String cadenaSQL(Socio s){
				String sql="";
				switch(s.estado()){
					case Libro.ACTUALIZAR:
						sql="UPDATE Socios set DNI='"+s.dni();
						sql+="', Apellidos='"+s.apellidos()+"', Nombre='"+s.nombre();
						sql+="', Direccion='"+s.direccion()+"', FechaAlta='"+s.fechaAltaSQL();
						sql+= "' where (idSocio="+s.idSocio()+")";
						
						break;
					case Libro.BORRAR:
						sql="DELETE FROM Socios WHERE (idSocio="+s.idSocio()+")";
						break;
					case Libro.NUEVO:
						sql= "INSERT INTO Socios (DNI,Apellidos,Nombre,Direccion,FechaAlta) VALUES (";
						sql+= "'"+s.dni()+"','"+s.apellidos()+"','"+s.nombre()+"','"+s.direccion()+"','"+s.fechaAltaSQL()+"')";
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
				Iterator<Map.Entry<String,Socio>> it= this.sociosMod.entrySet().iterator();
				while (it.hasNext()){
					Socio s= it.next().getValue();
					String sql= this.cadenaSQL(s);
					//System.out.println("Ejecuto:"+sql);
					stm.addBatch(sql);
				}
				int [] res= stm.executeBatch();
			}catch(SQLException e){
				throw new ErrorLibreriaException(Main.idioma.traduce("_ERRORACTUALIZACION")+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException(Main.idioma.traduce("_ERRORNODEFINIDO")+". "+e);
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
			this.genera();
			this.fireTableDataChanged();
			return r;
		}
		public void nuevo(){
			Socio s=new Socio(0,NEW,NEW,NEW,NEW,new java.sql.Date(System.currentTimeMillis()));
			s.estado(Libro.NUEVO);
			this.socios.put(NEW, s);
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
		public int buscar(String busca,int razonBus){
			this.genera();
			this.socios= new TreeMap<String,Socio>();
			this.sociosMod=new TreeMap<String,Socio> ();
			int res=0;
			String sql;
			switch(razonBus){
				case CSocios.BUSQUEDADNI:
					sql= "Select * from Socios where (DNI like '%"+busca+"%') order by Nombre";
	//System.out.println("Busqueda: "+sql);				
					this.conectaSQL(sql);
					res= this.encontrados;
					this.fireTableDataChanged();
					break;
			
				case CSocios.BUSQUEDANOMBRE:
						sql= "Select * from Socios where (Nombre like '%"+busca+"%') order by Nombre";
//System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				case CSocios.BUSQUEDAAPELLIDOS:
						sql= "Select * from Socios where (Apellidos like '%"+busca+"%') order by Apellidos";
//					System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				case CSocios.BUSQUEDADIRECCION:
						sql= "Select * from Socios where (Direccion like '%"+busca+"%') order by Direccion";
//					System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				default:
						sql= "Select * from Socios where (DNI like '%"+busca+"%') OR (Nombre like '%"+busca+"%')";
						sql+=" OR (Apellidos like '%"+busca+"%') OR (Direccion like '%"+busca+"%') order by Apellidos,Nombre";
//				System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
			}
			return res;
		}
		public int buscarFecha(String fecha, int tipofecha){
			int res=0;
			String sql="";
			String fechaValida="";
			this.genera();
			this.socios= new TreeMap<String,Socio>();
			this.sociosMod= new TreeMap<String,Socio>();
			if (tipofecha==DIA){
				fechaValida="(DAY(FechaAlta)="+fecha+")";
			}
			if (tipofecha==MES){
				fechaValida="(MONTH(FechaAlta)="+fecha+")";
			}
			if (tipofecha==ANYO){
				fechaValida="(YEAR(FechaAlta)="+fecha+")";
			}
			if (tipofecha==COMPLETO){
				if ((fechaValida=Socio.validaFecha(fecha))!=null){
					fechaValida="(FechaAlta='"+fechaValida+"')";
				}else{
					return -1;
				}
			}
				sql= "Select * from Socios where "+fechaValida+ " order by FechaAlta";
				System.out.println("Busqueda: "+sql);
				this.conectaSQL(sql);
				res= this.encontrados;
				this.fireTableDataChanged();
			return res;
		}
	}
