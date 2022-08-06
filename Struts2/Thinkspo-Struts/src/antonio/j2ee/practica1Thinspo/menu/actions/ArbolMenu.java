package antonio.j2ee.practica1Thinspo.menu.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte a obtencion del menu de la aplicacion
 * Ofrece el formaMenu() que es el que forma el arbol de menu
 * El resultado lo devuelve con JSON
  * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 * @see com.jgeppert.struts2.jquery.tree.result.TreeNode
 */
public class ArbolMenu extends ActionSupport {
	private static final long serialVersionUID = -6727574871311451928L;
	 //datos del arbol
	protected List<TreeNode> menu;

	/**
	 * Forma el arbol
	 * @return
	 */
    public String formaMenu() {
    	if (menu==null)
    		 menu=new ArrayList<TreeNode>();
    	
    	//formamos el menu
    	
    	//Gestion de usuarios
        TreeNode nodoUsuarios = new TreeNode();
        Collection<TreeNode> operacionesUsuarios=new ArrayList<TreeNode>();
        nodoUsuarios.setId("Usuarios");
        nodoUsuarios.setTitle("Gestion de Usuarios");
        nodoUsuarios.setState(TreeNode.NODE_STATE_OPEN);
        nodoUsuarios.setIcon("../img/closed.gif");

        TreeNode creaUsuarios = new TreeNode();
        creaUsuarios.setId("cargarCrearUsuario.action");
        creaUsuarios.setTitle("Crear Usuarios");
        creaUsuarios.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesUsuarios.add(creaUsuarios);

        TreeNode eliminarUsuarios = new TreeNode();
        eliminarUsuarios.setId("cargarEliminarUsuario.action");
        eliminarUsuarios.setTitle("Eliminar Usuarios");
        eliminarUsuarios.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesUsuarios.add(eliminarUsuarios);

        TreeNode listarUsuarios = new TreeNode();
        listarUsuarios.setId("cargarListarUsuarios.action");
        listarUsuarios.setTitle("Listar Usuarios");
        listarUsuarios.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesUsuarios.add(listarUsuarios);
        
        
        TreeNode modificarUsuarios = new TreeNode();
        modificarUsuarios.setId("cargarModificarUsuarios.action");
        modificarUsuarios.setTitle("Modificar Usuarios");
        modificarUsuarios.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesUsuarios.add(modificarUsuarios);
        
        nodoUsuarios.setChildren(operacionesUsuarios);
       

        
        ////Gestion de Canales
        TreeNode nodoCanales = new TreeNode();
        Collection<TreeNode> operacionesCanales=new ArrayList<TreeNode>();
        nodoCanales.setId("Canales");
        nodoCanales.setTitle("Gestion de Canales");
        nodoCanales.setState(TreeNode.NODE_STATE_OPEN);
        nodoCanales.setIcon("../img/closed.gif");

        TreeNode uoloadCanales = new TreeNode();
        uoloadCanales.setId("cargarUploadCanales.action");
        uoloadCanales.setTitle("Upload Canales");
        uoloadCanales.setState(TreeNode.NODE_STATE_OPEN);
        operacionesCanales.add(uoloadCanales);

        TreeNode listarCanales = new TreeNode();
        listarCanales.setId("cargarListarChannels.action");
        listarCanales.setTitle("Listar Canales");
        listarCanales.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesCanales.add(listarCanales);
        
        nodoCanales.setChildren(operacionesCanales); 


        ////Visualizacion Datos
        TreeNode nodoDatos = new TreeNode();
        Collection<TreeNode> operacionesVisualizacion=new ArrayList<TreeNode>();
        nodoDatos.setId("Visualizacion");
        nodoDatos.setTitle("Visualizacion de Datos");
        nodoDatos.setState(TreeNode.NODE_STATE_OPEN);
        nodoDatos.setIcon("../img/closed.gif");

        TreeNode visualizarTablas = new TreeNode();
        visualizarTablas.setId("cargarVisualizarDatos.action");
        visualizarTablas.setTitle("Tablas(Channels,Video & VideoSearch)");
        visualizarTablas.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesVisualizacion.add(visualizarTablas);

        TreeNode visualizarGrafos = new TreeNode();
        visualizarGrafos.setId("cargarVisualizarGrafos.action");
        visualizarGrafos.setTitle("Grafos JUNG");
        visualizarGrafos.setState(TreeNode.NODE_STATE_CLOSED);
        operacionesVisualizacion.add(visualizarGrafos);
        
        TreeNode visualizarJFree = new TreeNode();
        visualizarJFree.setId("cargarVisualizarJFree.action");
        visualizarJFree.setTitle("Graficas JFreeChart");
        visualizarJFree.setState(TreeNode.NODE_STATE_CLOSED);
         
        operacionesVisualizacion.add(visualizarJFree);
        
        nodoDatos.setChildren(operacionesVisualizacion); 
       
        menu.add(nodoUsuarios);
        menu.add(nodoCanales);
        menu.add(nodoDatos);
        return SUCCESS;
     }

    // getters y setters
	public List<TreeNode> getMenu() {
		return menu;
	}

	public void setMenu(List<TreeNode> menu) {
		this.menu = menu;
	}

}
