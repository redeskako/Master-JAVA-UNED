// Ejercicio 3.13: Factura.java, fecha: 2014/03/07
/* Aplicacion para crear facturas para una ferreteria de algun articulo
 * Incluira: numero de pieza(String), descripcion de la pieza(String), cantidad a comprar(int) y precio(double) 
 */
public class Factura {
	private String codigoPieza;
	private String descripcionPieza;
	private int unidadesPieza;
	private double precioPieza;
	
	// metodo constructor Factura
	public Factura( String codigo, String descripcion, int unidades, double precio) {
		this.codigoPieza = codigo;
		this.descripcionPieza = descripcion;
		this.unidadesPieza = unidades;
		this.precioPieza = precio;
	} // fin constructor Factura
	
	
	// set & get codigoPieza
	public void setCodigoPieza( String codigo) {
		codigoPieza = codigo; // almacena codigo de la pieza
	} // fin setCodigoPieza
	public String getCodigoPieza() {
		return codigoPieza;
	} // fin getCodigoPieza
	
	// set & get descripcionPieza
	public void setDescripcionPieza( String descripcion) {
		descripcionPieza = descripcion; // almacena descripcion de la pieza
	} // fin setDescripcionPieza
	public String getDescripcionPieza() {
		return descripcionPieza;
	} // fin getDescripcionPieza
	
	// set & get unidadesPieza
	public void setUnidadesPieza( int unidades) {
		unidadesPieza = unidades;
	} // fin setUnidadesPieza
	public int getUnidadesPieza() {
		return unidadesPieza;
	} // fin getUnidadesPieza
	
	// set & get precioPieza
	public void setPrecioPieza( double precio) {
		precioPieza = precio;
	} // fin setPrecioPieza
	public double getPrecioPieza() {
		return precioPieza;
	} // fin getPrecioPieza


	// metodo calculo de la factura
	public void obtenerTotalFactura( int unidades, double precio) {
		double totalFactura = unidadesPieza * precioPieza;
		
		if ( totalFactura < 0)
			totalFactura = 0;
		
		System.out.printf( "\nEl total de la factura es: %f\n", totalFactura);
	} // fin metodo obtenerMontoFactura
	
	/*
	// metodo mostrar factura
	public void mostrarFactura() {
		System.out.printf( "Codigo articulo: %s\n", codigoPieza);
		System.out.printf( "Descripcion articulo: %s\n", descripcionPieza);
		System.out.printf( "Unidades articulo: %d\n", unidadesPieza);
		System.out.printf( "Precio unidad articulo: %f\n", precioPieza);
	} // fin mostrarFactura
	*/
	
} // fin clase Factura
