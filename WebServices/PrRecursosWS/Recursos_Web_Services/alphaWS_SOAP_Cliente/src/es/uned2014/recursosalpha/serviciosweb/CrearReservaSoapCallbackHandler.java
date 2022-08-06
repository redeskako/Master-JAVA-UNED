/**
 * CrearReservaSoapCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package es.uned2014.recursosalpha.serviciosweb;

/**
 * CrearReservaSoapCallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class CrearReservaSoapCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public CrearReservaSoapCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public CrearReservaSoapCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getArrayUsuarios method
	 * override this method for handling normal response from getArrayUsuarios
	 * operation
	 */
	public void receiveResultgetArrayUsuarios(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.GetArrayUsuariosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getArrayUsuarios operation
	 */
	public void receiveErrorgetArrayUsuarios(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for comboEstados method override
	 * this method for handling normal response from comboEstados operation
	 */
	public void receiveResultcomboEstados(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboEstadosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from comboEstados operation
	 */
	public void receiveErrorcomboEstados(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getIdNuevo method override this
	 * method for handling normal response from getIdNuevo operation
	 */
	public void receiveResultgetIdNuevo(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.GetIdNuevoResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getIdNuevo operation
	 */
	public void receiveErrorgetIdNuevo(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for crearReserva method override
	 * this method for handling normal response from crearReserva operation
	 */
	public void receiveResultcrearReserva(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.CrearReservaResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from crearReserva operation
	 */
	public void receiveErrorcrearReserva(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getArrayEstados method override
	 * this method for handling normal response from getArrayEstados operation
	 */
	public void receiveResultgetArrayEstados(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.GetArrayEstadosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getArrayEstados operation
	 */
	public void receiveErrorgetArrayEstados(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getArraySucursales method
	 * override this method for handling normal response from getArraySucursales
	 * operation
	 */
	public void receiveResultgetArraySucursales(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.GetArraySucursalesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getArraySucursales operation
	 */
	public void receiveErrorgetArraySucursales(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for comboSucursales method override
	 * this method for handling normal response from comboSucursales operation
	 */
	public void receiveResultcomboSucursales(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboSucursalesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from comboSucursales operation
	 */
	public void receiveErrorcomboSucursales(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for comboUsuarios method override
	 * this method for handling normal response from comboUsuarios operation
	 */
	public void receiveResultcomboUsuarios(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboUsuariosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from comboUsuarios operation
	 */
	public void receiveErrorcomboUsuarios(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getArrayRecursos method
	 * override this method for handling normal response from getArrayRecursos
	 * operation
	 */
	public void receiveResultgetArrayRecursos(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.GetArrayRecursosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getArrayRecursos operation
	 */
	public void receiveErrorgetArrayRecursos(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for comboRecursos method override
	 * this method for handling normal response from comboRecursos operation
	 */
	public void receiveResultcomboRecursos(
			es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboRecursosResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from comboRecursos operation
	 */
	public void receiveErrorcomboRecursos(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	// No methods generated for meps other than in-out

}
