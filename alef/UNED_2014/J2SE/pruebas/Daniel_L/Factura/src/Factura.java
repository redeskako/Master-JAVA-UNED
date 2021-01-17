
public class Factura {

	
	
	private String numpieza;
	private String descripcionpieza;
	private int cantidad;
	private double precio;
	
	public Factura(String argPieza, String argDescripcion, int argCantidad, double argPrecio)
	{
		numpieza=argPieza;
		descripcionpieza=argDescripcion;
		cantidad=argCantidad;
		precio=argPrecio;
	}
	
	public void EstablecerPieza(String pieza)
	{
		numpieza=pieza;
	}


	public void EstablecerDescripcion(String descripcion)
	{
		descripcionpieza=descripcion;
	}
	
	public void EstablecerCantidad(int cantidad)
	{
		if (cantidad<=0)
			cantidad=0;
		
		cantidad=cantidad;
	}
	
	public void EstablecerPrecio(double precio)
	{
		if (precio<=0)
			precio=0.00;
		
		precio=precio;
	}
	
	public String ObtenerPieza()
	{
		return numpieza;
	}
	
	public String ObtenerDescripcion()
	{
		return descripcionpieza;
	}
	public int ObtenerCantidad()
	{
		return cantidad;
	}
	public double ObtenerPrecio()
	{
		return precio;
	}
		
	public double ObtenerMontoFactura()
	{
		double monto;
		monto=precio*cantidad;
		return monto;
	}
	
}
