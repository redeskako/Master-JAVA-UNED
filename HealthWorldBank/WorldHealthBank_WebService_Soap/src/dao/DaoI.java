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
	
	//Creación de un nuevo usuario en bbdd a partir de un objeto usuario
	String crearteUser(@WebParam(name = "usuario") User usuario);
	
	//Creación de un nuevo pais en bbdd a partir de un objeto country
	String addCountry(@WebParam(name = "pais") Country pais);
	
	//Obtención del nombre del país a partir del código del país
	String getCountryByCode(@WebParam(name = "codigo_pais") String code);
	
	//Borradp de un pais en bbdd a partir del código del país
	String deleteCountry(@WebParam(name = "codigo_pais") String code);
	
	//Update de un pais en bbdd a partir de un objeto country
	String updateCountry(@WebParam(name = "pais") Country country);
	
	//Obtención de un listado de paises a partir de una página y una fila
	List<Country> getCountryPagination(@WebParam(name = "pagina") int pag, @WebParam(name = "fila")int numeroFilas);

}
