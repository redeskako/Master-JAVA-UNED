/**
 * Clase que encapsula los datos de un registro de la BD.
 */

public class Registro
{
	// Propiedades.
	private int nPlaza;
	private int nPlanta;
	private String sFechaEntrada;
	private String sFechaSalida;
	
	/**
	 * Constructor genérico.
	 * @param nPlaza número de plaza de garaje.
	 * @param nPlanta número de planta de garaje.
	 * @param sFechaEntrada fecha y hora de entrada.
	 * @param sFechaSalida fecha y hora de salida.
	 */
	public Registro(int nPlaza, int nPlanta, String sFechaEntrada, String sFechaSalida)
	{
		this.nPlaza = nPlaza;
		this.nPlanta = nPlanta;
		this.sFechaEntrada = sFechaEntrada;
		this.sFechaSalida = sFechaSalida;
	}
	
	/**
	 * Constructor sin fecha de salida. Para registrar un coche 
	 * mientras todavía no ha abandonado el garaje. 
	 */
	public Registro(int nPlaza, int nPlanta, String sFechaEntrada)
	{
		this(nPlaza, nPlanta, sFechaEntrada, null);
	}
	
	// Getters y setters.
	public int getPlaza()
	{
		return nPlaza;
	}
	
	public void setPlaza(int nPlaza)
	{
		this.nPlaza = nPlaza;
	}
	
	public int getPlanta()
	{
		return nPlanta;
	}
	
	public void setPlanta(int nPlanta)
	{
		this.nPlanta = nPlanta;
	}
	
	public String getFechaEntrada()
	{
		return sFechaEntrada;
	}
	
	public void setFechaEntrada(String sFechaEntrada)
	{
		this.sFechaEntrada = sFechaEntrada;
	}
	
	public String getFechaSalida()
	{
		return sFechaSalida;
	}
	
	public void setFechaSalida(String sFechaSalida)
	{
		this.sFechaSalida = sFechaSalida;
	}	
}