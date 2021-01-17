import java.util.Scanner;
public class Binario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	
		int numero;

		int multiplicador;
		int contador;
		String x;
		
		contador=1;
	
		multiplicador=1;
		
		Scanner entrada= new Scanner(System.in);
		System.out.println("Introduzca Numero que sea 0 ó 1: ");
		numero=entrada.nextInt();
		
		Binario2 numero2=new Binario2(numero);

		
		int[] numero3=numero2.InicializarArrayNumero(numero2.ObtenerDigitos());
		

		
		System.out.println("");

			
			if ((numero2.CumpleCondicion()==false))
			{
				
				System.out.println("Debe Introducir Datos que Sean 1 ó 0");
			
			}// fin if
			
			else
				numero2.MultiplicadorBinarios(numero2.ObtenerDigitos());
			
			

		

		
	}// fin main

}// fin clase
