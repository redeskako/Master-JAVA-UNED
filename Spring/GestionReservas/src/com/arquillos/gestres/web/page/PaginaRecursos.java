/*
 *
 *
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.data.Color;
import com.arquillos.gestres.data.EstadoRecurso;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.reports.ListadoRecursosPDF;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.BotonNuevoRecurso;
import com.arquillos.gestres.web.page.component.PanelEditarEliminarRecurso;
import com.arquillos.gestres.web.page.component.TituloMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import javax.servlet.ServletContext;


/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@Autorizado
public class PaginaRecursos extends WebPage {  
  @SpringBean
  private RecursoDao recursoDao;
  
  @SuppressWarnings({"serial", "unchecked", "rawtypes"})
	public PaginaRecursos() {
    add(new TituloMenu("tituloMenu"));
  	ServletContext context = ((WebApplication) getApplication()).getServletContext(); 
    add(new ResourceLink("enlaceListar", new ListadoRecursosPDF(context.getRealPath("/reports/listadoRecursos.jrxml"))));
    
    List<IColumn<?>> cols = new ArrayList<IColumn<?>>();    
    final Stylist paraColor = new ColorRecursoStylist();
    Stylist paraEstado = new EstadoRecursoStylist();
    
    // columna identificador de recurso:
    cols.add(
      new ColumnaPropiedadColor(
        new Model<String>("Recurso"), 
        "nombre", 
        "nombre", 
        paraColor
      )
    );    
    // columna descripción de recurso:
    cols.add(
      new ColumnaPropiedadColor(
        new Model<String>("Descripción"), 
        "descripcion", 
        "descripcion", 
        paraColor
      ){
        @Override
        public String getCssClass() {
          return "comentario";
        }      
      }
    );    
    // columna con el estado de conservación del recurso
    cols.add(
      new ColumnaPropiedadColor(
        new Model<String>("Estado"), 
        "estado", 
        "estado", 
        paraEstado
      )
    );    
    // columna con el color de visualización del recurso
    cols.add(
      new ColumnaPropiedadColor(
        new Model<String>("Color Visualización"), 
        "color", 
        "color", 
        paraColor
      )
    );
    // columna autorizador; se puede ordenar por nombre de autorizador
    cols.add(
      new AbstractColumn<Recurso>(new Model<String>("Autorizador")){
			  @SuppressWarnings("unused")
			  public void populateItem(
          Item<ICellPopulator<Recurso>> celda, 
          String idComponente, 
          IModel<Recurso> modelo) {
			  	
          Label label = null;
          if (modelo.getObject().getAutorizador() != null)
            label = new Label(idComponente, new PropertyModel(modelo, "autorizador.nombre"));
          else
          	label = new Label(idComponente, "");                  
        
          Recurso u = modelo.getObject();        
          label.add(new SimpleAttributeModifier("style", paraColor.getStyleFor(modelo.getObject())));
        
          celda.add(label);
        }			  
        @Override
        public String getSortProperty() {
          return "autorizador.nombre";
        }
        @Override
        public boolean isSortable() {
          return true;
        }
      }
    );
    
    if (((Session)getSession()).getUsuarioActual().isAdmin()) {
      cols.add(
      	new AbstractColumn<Recurso>(new Model<String>("Añadir nuevo recurso")){
          public void populateItem(
            Item<ICellPopulator<Recurso>> celda, 
            String id, 
            IModel<Recurso> modelo) {
          	
        	  celda.add(new PanelEditarEliminarRecurso(id, modelo));
          }
				  @Override
          public Component getHeader(String id) {
            return new BotonNuevoRecurso(id, new Model(getPage()));
          }             
        }
      );
    }
    // añadimos las columnas a la tabla
    add(new DefaultDataTable("tabla", cols, new RecursoDataProvider(this), 10));
  }

  public RecursoDao getRecursoDao() {
    return recursoDao;
  }
  
  @SuppressWarnings("serial")
  // data provider
	public static class RecursoDataProvider extends SortableDataProvider<Recurso> {
    private PaginaRecursos pr;
    
    public RecursoDataProvider(PaginaRecursos paginaRecursos) {
      this.pr = paginaRecursos;      
      setSort("nombre", true);
    }    
    
    public Iterator<? extends Recurso> iterator(int primero, int max) {
      SortParam sp = getSort();      
      return pr.getRecursoDao().getRecursos(primero, max, sp.getProperty(), sp.isAscending()).iterator();
    }

    public IModel<Recurso> model(Recurso recurso) {
      return new RecursoDetachableModel(pr.getRecursoDao(), recurso);
    }

    public int size() {
      return pr.getRecursoDao().getTotalRecursos();
    }    
  }
  
  @SuppressWarnings("serial")
	public static class RecursoDetachableModel extends LoadableDetachableModel<Recurso> {
    private int id;
    private RecursoDao recursoDao;
    
    public RecursoDetachableModel(RecursoDao recursoDao, Recurso recurso) {
      super(recurso);      
      this.recursoDao = recursoDao;      
      id = recurso.getId();
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 53 * hash + this.id;
      return hash;
    }   

    @Override
    public boolean equals(Object obj) {
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;      
      final RecursoDetachableModel otro = (RecursoDetachableModel)obj;
      if (this.id != otro.id) return false;      
      return true;
    }        

    @Override
    protected Recurso load() {
      return recursoDao.getRecurso(id);
    }    
  }
    
  public static class ColorRecursoStylist implements Stylist<Recurso> {
		public String getStyleFor(Recurso t) {
			return "color: " +
        (t != null && t.getColor() != null 
        ? 
          t.getColor().getHexColor() 
        : 
          Color.DEFECTO.getHexColor()
      );
		}  	  	
  }
  
  public static class EstadoRecursoStylist implements Stylist<Recurso> {
    public String getStyleFor(Recurso r) {
    	return "color: " +
        (r != null && r.getEstado() != null 
        ? 
          r.getEstado().getColor() 
        : 
          EstadoRecurso.DESCONOCIDO.getColor()
      );
    }    
  }
}