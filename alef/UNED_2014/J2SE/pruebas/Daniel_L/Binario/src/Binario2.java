
public class Binario2 {
	private int paramInt;
	private String strNumero;
	private int [] numeroArray;
	private boolean condicion=true;
	private int contador;
	
	public Binario2 (int numero)
	{
		paramInt=numero;
	}
	
	public int ObtenerDigitos()
	{
	
		strNumero=Integer.toString(paramInt);
		int contador=strNumero.length();
		return contador;
	}
	
	public int[] InicializarArrayNumero(int contador)
	{
		int numero;
		int numero2;
		int i=contador;
		numeroArray=new int[contador];
		
		numero=paramInt;
		while(i!=0)
		{	
			numero2=numero%10;
			numeroArray[i-1]=numero2;
	
			i--;
			numero=numero/10;
			
				
		}// fin while
		
		return numeroArray;
		
	}// fin metodo array
	
	public boolean CumpleCondicion()
	{
		for (int i = 0; i < numeroArray.length; i++) {
			
	
			if (numeroArray[i]>1)
				condicion=false;
			
		
			
		}// fin for
		
		return condicion;
	}// fin cumpleCondicion

	public void MultiplicadorBinarios(int contador)
	{
		int numero=paramInt;
		int numero2=paramInt;
		int multiplicador=1;
		int i=contador;
		System.out.println("Nº de Dígitos: " + contador);
		System.out.println("Número: "+paramInt);
		System.out.println("Número Binario Calculado: ");
		
		while(i!=0)
		{	
			numero2=numero%10;
			numeroArray[i-1]=numero2;
	
			System.out.print(numero2*multiplicador);
			multiplicador=multiplicador*2;
			i--;
			numero=numero/10;
			
				
		}// fin while
		
		
		
	}//fin multiplicadorBinarios
	
}
