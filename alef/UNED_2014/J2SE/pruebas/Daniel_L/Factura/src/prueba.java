import java.util.Scanner;
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String entradaDescripcion;
		String entradaNumeroPieza;
		int entradaCantidad;
		double entradaPrecio;
		
		
		Scanner entrada= new Scanner(System.in);
		
		System.out.println("Introduzca Referencia Articulo: ");
		entradaNumeroPieza=entrada.nextLine();
		

		switch (entradaNumeroPieza)
		{
		
		case "Brocha": entradaDescripcion="Brocha de Afeita";
			break;
		
		case "Cepillo":
			
			 entradaDescripcion="Cepillo de Dientes";
			 break;
		
		case "Detergente":
			 entradaDescripcion="Detergente Fairy Multiusos";
			 break;
			 
		case "Accesorios Varios":
			 entradaDescripcion="Fregona Vileda";
			 break;
		default:
			 entradaDescripcion="No se ha Marcado Artículo Específico";
			 break;
		
			
		
		}
		
		
		
		//System.out.println("Introduzca Descripción Articulo: ");
		//entradaDescripcion=entrada.nextLine();
		
	
		System.out.printf("El Articulo Seleccionado es: %s\n", entradaDescripcion);
		System.out.println("Introduzca Cantidad Articulo: ");
		entradaCantidad=entrada.nextInt();
		

		System.out.println("Introduzca Precio Articulo: ");
		entradaPrecio=entrada.nextDouble();
		
		Factura total = new Factura(entradaNumeroPieza,entradaDescripcion,  entradaCantidad, entradaPrecio);
		
		System.out.printf("El total es: %.2f", total.ObtenerMontoFactura());
		
		
	}

}
