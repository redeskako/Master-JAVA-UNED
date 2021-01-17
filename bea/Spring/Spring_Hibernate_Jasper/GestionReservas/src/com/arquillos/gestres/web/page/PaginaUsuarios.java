/*
 *
 *
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.reports.ListadoUsuariosPDF;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.BotonNuevoUsuario;
import com.arquillos.gestres.web.page.component.PanelEditarEliminarUsuario;
import com.arquillos.gestres.web.page.component.TituloMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@Autorizado
public class PaginaUsuarios extends WebPage {
  @SpringBean
  private UsuarioDao usuarioDao;
  
  @SuppressWarnings({"serial", "rawtypes", "unchecked"})
	public PaginaUsuarios() {
    add(new TituloMenu("tituloMenu"));

  	ServletContext context = ((WebApplication) getApplication()).getServletContext(); 
    add(new ResourceLink("enlaceListar", new ListadoUsuariosPDF(context.getRealPath("/reports/listadoUsuarios.jrxml"))));
    
    List<IColumn<?>> columnas = new ArrayList<IColumn<?>>();
    
    final Stylist stylist = new Stylist.ParaUsuario();
    
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Usuario"), "username", "username", stylist));    
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Nombre"), "nombre", "nombre", stylist));
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Apellidos"), "apellidos", "apellidos", stylist));
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Email"), "email", "email", stylist));    
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Telefono"), "telefono", "telefono", stylist));    
    columnas.add(new AbstractColumn<Usuario>(new Model<String>("Autorizador")){
      public void populateItem(
        Item<ICellPopulator<Usuario>> itemCelda, 
        String componentId, 
        IModel<Usuario> modelo) {
        	Label label = null;
        	if (modelo.getObject().getAutorizador() != null) {
        		label = new Label(componentId, new PropertyModel( modelo, "autorizador.nombre"));
        	} 
        	else {
        		label = new Label(componentId, "");          
        	}        
        	label.add(new SimpleAttributeModifier("style", stylist.getStyleFor(modelo.getObject())));        
          itemCelda.add(label);
      }
      @Override
      public String getSortProperty() {
      	return "autorizador.nombre";
      }

      @Override
      public boolean isSortable() {
      	return true;
      }
    });
    
    columnas.add( 
    	new AbstractColumn<Usuario>(new Model<String>("Admin")) {
    		public void populateItem(
    			Item<ICellPopulator<Usuario>> itemCelda, 
    			String id, 
    			IModel<Usuario> modelo) {
        
    			Label label = new Label(id,(modelo.getObject().isAdmin() ? "Sí" : ""));
    			label.add(new SimpleAttributeModifier("style", stylist.getStyleFor(modelo.getObject())));        
    			itemCelda.add(label);                 
    		}
    		@Override
    		public String getSortProperty() {
    			return "admin";
    		}
    		@Override
    		public boolean isSortable() {
    			return true;
    		}
    	}
    );
    
    columnas.add(new ColumnaPropiedadColor(new Model<String>("Color"), "color", "color", stylist));    
    columnas.add( 
      new ColumnaPropiedadColor(new Model<String>("Comentarios"), "comentarios", "comentarios", stylist) {
    		@Override
    		public String getCssClass() {return "comentario";}      
    	}
    );
    
    if (((Session)getSession()).getUsuarioActual().isAdmin()) {
    	columnas.add( 
    		new AbstractColumn<Usuario>(new Model<String>("Nuevo Usuario")) {
    			public void populateItem(
    				Item<ICellPopulator<Usuario>> itemCelda, 
    				String id, 
    				IModel<Usuario> modelo) {
    				
    				itemCelda.add(new PanelEditarEliminarUsuario(id, modelo));
    			}
    			@Override
    			public Component getHeader(String id) {
    				return new BotonNuevoUsuario(id, new Model(getPage()));
    			}      
    		}
    	);
    }    
    add(new DefaultDataTable("tabla", columnas, new UsuarioDataProvider(this), 10));
  }

  public UsuarioDao getUsuarioDao() {
    return usuarioDao;
  }  
  
  @SuppressWarnings("serial")
	public static class UsuarioDataProvider extends SortableDataProvider<Usuario> {

    private PaginaUsuarios paginaUsuarios;

    public UsuarioDataProvider(PaginaUsuarios paginaUsuarios) {
      this.paginaUsuarios = paginaUsuarios;      
      setSort("nombre", true);
    }    
    
    public Iterator<? extends Usuario> iterator(int primero, int maxRegistros) {
      SortParam sp = getSort();      
      return paginaUsuarios.getUsuarioDao().getUsuarios( primero, maxRegistros, sp.getProperty(), sp.isAscending() ).iterator();
    }

    public IModel<Usuario> model(Usuario usuario) {
      return new UsuarioDetachableModel(paginaUsuarios.getUsuarioDao(), usuario);
    }

    public int size() {
      return paginaUsuarios.getUsuarioDao().totalUsuarios();
    }    
  }
  
  @SuppressWarnings("serial")
	public static class UsuarioDetachableModel extends LoadableDetachableModel<Usuario> {

    private int id;
    private UsuarioDao usuarioDao;
    
    public UsuarioDetachableModel(UsuarioDao usuarioDao, Usuario usuario) {
      super(usuario);
      
      this.usuarioDao = usuarioDao;      
      this.id = usuario.getId();
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 53 * hash + this.id;
      return hash;
    }   

    @Override
    public boolean equals( Object obj ) {
      if (obj == null) return false;      
      if (getClass() != obj.getClass()) return false;      
      final UsuarioDetachableModel otro = (UsuarioDetachableModel)obj;
      if (this.id != otro.id) return false;
      return true;
    }        

    @Override
    protected Usuario load() {
      return usuarioDao.getUsuarioPorId(id);
    }    
  }
}