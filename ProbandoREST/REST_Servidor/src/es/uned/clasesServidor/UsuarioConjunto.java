package es.uned.clasesServidor;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
@XmlRootElement(name="usuarioConjunto")
public class UsuarioConjunto {
	private ArrayList<Usuario> listado;
	
	public UsuarioConjunto(){
		listado = new ArrayList<Usuario>();
	}

	public ArrayList<Usuario> getListado() {
		return listado;
	}

	public void setListado(ArrayList<Usuario> listado) {
		this.listado = listado;
	}
	
}
