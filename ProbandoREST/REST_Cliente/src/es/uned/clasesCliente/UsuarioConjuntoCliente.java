package es.uned.clasesCliente;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

@XmlRootElement(name="usuarioConjunto")
public class UsuarioConjuntoCliente {
	private ArrayList<UsuarioCliente> listado;
	
	public UsuarioConjuntoCliente(){
		listado = new ArrayList<UsuarioCliente>();
	}

	public ArrayList<UsuarioCliente> getListado() {
		return listado;
	}

	public void setListado(ArrayList<UsuarioCliente> listado) {
		this.listado = listado;
	}
	
}
