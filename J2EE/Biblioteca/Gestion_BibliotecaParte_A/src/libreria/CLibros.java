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

public class CLibros extends DefaultTableModel implements TableModelListener{
	private Map<String,Libro> libros;
	private Map<String,Libro> librosMod;
	private final int BORRAR=1;
	private final int NUEVO=0;
	private final int MODIFICAR=2; 
	public static final int BUSQUEDANOMBRE=0;
	public static final int BUSQUEDAAUTOR=1;
	public static final int BUSQUEDATEMA=2;
	public static final int BUSQUEDATOTAL=3;	
	public final String [] ACTUALIZADO;
	private int [] resActualizado={0,0,0};
	public static final String NEW="*>";
	private int encontrados;
	
	/**********************************************************************************
	 * Configuracion de la clase y tabla....
	 * @param tabla
	 **********************************************************************************/
		private void genera(){
			this.libros= new TreeMap<String,Libro>();
			this.librosMod= new TreeMap<String,Libro>();
			this.resActualizado[0]=0;
			this.resActualizado[1]=0;
			this.resActualizado[2]=0;
			this.encontrados=0;
			this.conectaSQL("Select * from Libros order by Nombre, Autor");
		}
	public CLibros(){
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
			return Libro.COLUMNAS;
		}
		public int getRowCount(){
			if (this.libros==null){
				return 0;
			}else{
				return this.libros.keySet().size();
			}
		}
		///Obtencion de contenidos de columnas
		public String getColumnName(int column){
			Libro l= new Libro(0,"","","");
			return l.NOMCOL()[column].toString();
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
			return this.libros.get(this.libros.keySet().toArray()[row]).getColumn(column);
		}
		/**********************************************************************************
		 * Actualizaciones del JTable
		 * @param tabla
		 **********************************************************************************/	
				private void actualizaLibro(String nuevo, int fil, int col){
						if (col>0){
							Libro l;
							//Obtengo la key original.
							String oldkey= (String) this.libros.keySet().toArray()[fil];
							l= this.libros.get(oldkey);
							if (l.estado()==Libro.BORRAR) return;
							if (l.estado()==Libro.NADA) l.estado(Libro.ACTUALIZAR);
							//if (l.estado()==Libro.NUEVO) l.estado(Libro.NUEVO);
							//if (l.estado()==Libro.ACTUALIZAR) l.estado(Libro.ACTUALIZAR);
							switch (col){
								case 1:
										//Está actualizando la columna 'Nombre'
										//Primero en el map libros
										l.nombre(nuevo);
										this.libros.remove(oldkey);
										this.libros.put(nuevo, l);
										//Segundo añadir los campos a 'modificar'
										this.librosMod.put(nuevo, l);
										break;
								case 2:
										//Este es mas facil solo modificar el autor
										//Primero en libros
										l.autor(nuevo);
										this.libros.put(oldkey, l);
										//Segundo al map modificar
										this.librosMod.put(oldkey, l);
										break;
								case 3:
									//El tema
									//Primero en libros
									l.tema(nuevo);
									this.libros.put(oldkey, l);
									//Segundo al map modificar
									this.librosMod.put(oldkey, l);			
									break;
							}
						}
						this.resActualizado[MODIFICAR]++;
					}
					private void borraLibro(Boolean tachado,int fil, int col){
						// Eliminar el registro
						String oldkey= (String) this.libros.keySet().toArray()[fil];
						Libro l= this.libros.get(oldkey);
						l.aEliminar(tachado.booleanValue());
						if (l.estado()==Libro.NUEVO){
							this.libros.remove(oldkey);
							this.librosMod.remove(oldkey);
							return;
						}
						if (tachado.booleanValue()){
							l.estado(Libro.BORRAR);
						}else{
							l.estado(Libro.NADA);
						}
						this.librosMod.put(oldkey,l);
						this.libros.put(oldkey,l);
						this.resActualizado[BORRAR]++;
					}
		public void setValueAt(Object o, int fil, int col){
			try{
				int sizeKey=this.libros.keySet().size();
				
				if (col==4) borraLibro((Boolean) o,fil,col);
				else actualizaLibro((String) o,fil,col);
				
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
			String str=Main.idioma.traduce("_LIBROS")+": ------\n";
			str+=this.libros.toString()+"\n";
			str+="A ACTUAR: -----\n";
			str+=this.librosMod.toString()+"\n";
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
				this.encontrados= 0;
				while (rst.next()){
					String clave= rst.getString("Nombre");
					Libro l= new Libro (rst.getInt("IdLibro"),clave,rst.getString("Autor"), rst.getString("Tema"));
					this.libros.put(clave,l);
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
			private String cadenaSQL(Libro l){
				String sql="";
				switch(l.estado()){
					case Libro.ACTUALIZAR:
						sql="UPDATE Libros set Nombre='"+l.nombre()+"', ";
						sql+="Autor='"+l.autor()+"', Tema='"+l.tema();
						sql+="' where (idLibro="+l.idLibro()+")";
						
						break;
					case Libro.BORRAR:
						sql="DELETE FROM Libros WHERE (idLibro="+l.idLibro()+")";
						break;
					case Libro.NUEVO:
						sql= "INSERT INTO Libros (Nombre,Autor,Tema) VALUES (";
						sql+= "'"+l.nombre()+"','"+l.autor()+"','"+l.tema()+"')";
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
				Iterator<Map.Entry<String,Libro>> it= this.librosMod.entrySet().iterator();
				while (it.hasNext()){
					Libro l= it.next().getValue();
					String sql= this.cadenaSQL(l);
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
			Libro l=new Libro(0,NEW,NEW,NEW);
			l.estado(Libro.NUEVO);
			this.libros.put(NEW, l);
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
			this.libros= new TreeMap<String,Libro>();
			this.librosMod=new TreeMap<String,Libro> ();
			int res=0;
			String sql;
			switch(razonBus){
				case CLibros.BUSQUEDANOMBRE:
						sql= "Select * from Libros where (Nombre like '%"+busca+"%') order by Nombre";
//System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				case CLibros.BUSQUEDAAUTOR:
						sql= "Select * from Libros where (Autor like '%"+busca+"%') order by Autor";
//					System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				case CLibros.BUSQUEDATEMA:
						sql= "Select * from Libros where (Tema like '%"+busca+"%') order by Tema";
//					System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
						break;
				default:
						sql= "Select * from Libros where (Nombre like '%"+busca+"%')";
						sql+=" OR (Autor like '%"+busca+"%') OR (Tema like '%"+busca+"%') order by Nombre";
//				System.out.println("Busqueda: "+sql);				
						this.conectaSQL(sql);
						res= this.encontrados;
						this.fireTableDataChanged();
			}
			return res;
		}		
	}
