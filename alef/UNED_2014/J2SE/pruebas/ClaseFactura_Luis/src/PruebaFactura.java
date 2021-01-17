// Ejercicio 3.13: PruebaFactura.java, fecha: 2014/03/07
/* Genera facturas y el total del precio de un articulo 
 */
import java.util.Scanner; // importa clase Scanner

public class PruebaFactura {
	// inicio metodo main
	public static void main( String[] args) {
		// crea lectura informacion 
		Scanner entrada = new Scanner( System.in);
		
		// obtenemos informacion del articulo
		System.out.print( "Introduzca el codigo del articulo: "); // indicador
		String codigo = entrada.nextLine(); // lee codigo
		
		System.out.print( "Introduzca descripcion del articulo: "); // indicador
		String descripcion = entrada.nextLine(); // lee descripcion
		
		System.out.print( "Introduzca la cantidad de unidaddes del articulo: "); // indicador
		int unidades = entrada.nextInt(); // lee unidades
				
		System.out.print( "Introduzca precio del articulo: "); // indicador
		double precio = entrada.nextDouble(); // lee precio
				
		// crea objeto con constructor Factura
		Factura factura1= new Factura( codigo, descripcion, unidades, precio);
		
		factura1.setCodigoPieza( codigo); // establece codigo
		factura1.setDescripcionPieza( descripcion); //establece descripcio pieza
		factura1.setUnidadesPieza( unidades); // establece unidades
		factura1.setPrecioPieza( precio); // establece precio
			
		// muestra la factura y el precio
		System.out.printf( "Codigo articulo: %s\n", factura1.getCodigoPieza());
		System.out.printf( "Descripcion articulo: %s\n", factura1.getDescripcionPieza());
		System.out.printf( "Unidades articulo: %s\n", factura1.getUnidadesPieza());
		System.out.printf( "Precio articulo: %s\n", factura1.getPrecioPieza());
		
		// obtiene total factura
		factura1.obtenerTotalFactura( unidades, precio);
		
	} // fin metodo main	

} // fin clase PruebaFactura