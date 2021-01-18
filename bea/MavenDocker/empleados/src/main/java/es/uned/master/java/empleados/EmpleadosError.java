package es.uned.master.java.empleados;

// TODO: Auto-generated Javadoc
/**
 * The Class EmpleadosError.
 */
public class EmpleadosError extends RuntimeException{ 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new empleados error.
	 */
	public EmpleadosError() {
		super();
	}
	
	/**
	 * Instantiates a new empleados error.
	 *
	 * @param str the str
	 */
	public EmpleadosError(String str) {
		super(">>>>EmpleadosError:" + str);
	}
}
