package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import model.Country;
import model.User;

@WebService
public interface DaoI {
	
	//Creaci�n de un nuevo usuario en bbdd a partir de un objeto usuario
	String crearteUser(@WebParam(name = "usuario") User usuario);
	
	//Creaci�n de un nuevo pais en bbdd a partir de un objeto country
	String addCountry(@WebParam(name = "pais") Country pais);
	
	//Obtenci�n del nombre del pa�s a partir del c�digo del pa�s
	String getCountryByCode(@WebParam(name = "codigo_pais") String code);
	
	//Borradp de un pais en bbdd a partir del c�digo del pa�s
	String deleteCountry(@WebParam(name = "codigo_pais") String code);
	
	//Update de un pais en bbdd a partir de un objeto country
	String updateCountry(@WebParam(name = "pais") Country country);
	
	//Obtenci�n de un listado de paises a partir de una p�gina y una fila
	List<Country> getCountryPagination(@WebParam(name = "pagina") int pag, @WebParam(name = "fila")int numeroFilas);

}
