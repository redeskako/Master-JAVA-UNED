/*
 *
 *
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.reports.ListadoReservasPDF;
import com.arquillos.gestres.service.ServicioCorreo;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.PanelAprobarDenegarReserva;
import com.arquillos.gestres.web.page.component.PanelEditarEliminarReserva;
import com.arquillos.gestres.web.page.component.PanelEstadoFinal;
import com.arquillos.gestres.web.page.component.TituloMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
@SuppressWarnings({ "serial", "unchecked", "rawtypes" })

@Autorizado
public class PaginaReservas extends WebPage {
	@SpringBean
	private ReservaDao reservaDao;  
	@SpringBean
	private ServicioCorreo servicioCorreo;  
	
	public PaginaReservas() {
		add(new TituloMenu("tituloMenu"));
  	ServletContext context = ((WebApplication) getApplication()).getServletContext(); 
    add(new ResourceLink("enlaceListar", new ListadoReservasPDF(context.getRealPath("/reports/listadoReservas.jrxml"))));
		
		WebMarkupContainer contenedorAprobar = new WebMarkupContainer("contenedorAprobacion");    
		List<IColumn<?>> colsAprobar= new ArrayList<IColumn<?>>();    
		final Stylist stylist = new Stylist.ParaReserva();
		// Tabla solicitudes asignadas: columna solicitante
		colsAprobar.add(
			new ColumnaPropiedadColor(
				new Model<String>("Solicitante"), 
				"solicitante.nombre", 
				"solicitante.nombre", 
				stylist 
			) 
		);    
		// Tabla solicitudes asignadas: columna recurso
		colsAprobar.add(
			new ColumnaPropiedadColor(
				new Model<String>("Recurso"), 
				"recurso.nombre", 
				"recurso.nombre", 
				stylist
			)
		);    
		// Tabla solicitudes asignadas: fecha inicio
		colsAprobar.add(
			new ColumnaPropiedadColor(
				new Model<String>("Desde"), 
				"inicio", 
				"inicio", 
				stylist
			)
		);    
		// Tabla solicitudes asignadas: fecha fin
		colsAprobar.add(
			new ColumnaPropiedadColor(
				new Model<String>("Hasta"), 
				"fin", 
				"fin",
				stylist
			)
		);    
		// Tabla solicitudes asignadas: acción
    colsAprobar.add(
      new AbstractColumn<Reserva>(
        new Model<String>("Acción")){
          public void populateItem(          		
          	Item<ICellPopulator<Reserva>> celda, 
            String id, 
            IModel<Reserva> modelo) {
          	
            celda.add(new PanelAprobarDenegarReserva(id, modelo));
          }    
      }
    );
    
    DefaultDataTable tablaAprobar = new DefaultDataTable("tablaAprobar", colsAprobar, new AprobarDataProvider(), 10);    
    contenedorAprobar.add(tablaAprobar);
    Usuario usuario = ((Session)getSession()).getUsuarioActual();        
    contenedorAprobar.setVisible(reservaDao.totalReservasAprobables(usuario) > 0);
    
    // Tabla solicitudes pendientes
    WebMarkupContainer contenedorPendientes = new WebMarkupContainer("contenedorPendientes");    
    List<IColumn<?>> colsPendientes = new ArrayList<IColumn<?>>();
    // Tabla solicitudes pendientes: solicitante
    colsPendientes.add(
      new ColumnaPropiedadColor(
        new Model<String>("Solicitante"), 
      	"solicitante.nombre", 
      	"solicitante.nombre", 
      	stylist
      )
    );
    // Tabla solicitudes pendientes: recurso
    colsPendientes.add(
      new ColumnaPropiedadColor(
      	new Model<String>("Recurso"), 
      	"recurso.nombre", 
      	"recurso.nombre", 
      	stylist
      )
    );
    // Tabla solicitudes pendientes: fecha inicio
    colsPendientes.add(
      new ColumnaPropiedadColor(
      	new Model<String>("Desde"), 
      	"inicio", 
      	"inicio", 
      	stylist
      )
    );
    // Tabla solicitudes pendientes: fecha fin
    colsPendientes.add(
      new ColumnaPropiedadColor(
      	new Model<String>("Hasta"), 
      	"fin", 
      	"fin", 
      	stylist
      )
    );
    // Tabla solicitudes pendientes: autorizador
    colsPendientes.add(
      new AbstractColumn<Reserva>(
      	new Model<String>("Autorizador")){
      	  public void populateItem(
            Item<ICellPopulator<Reserva>> celda, 
            String componentId, 
            IModel<Reserva> modelo) {
        
          Label label = null;
          String aut1 = null;
          String aut2 = null;
        
          Reserva reserva = modelo.getObject();
          if (reserva.getSolicitante() != null && reserva.getSolicitante().getAutorizador() != null)
            aut1 = reserva.getSolicitante().getAutorizador().getNombre();
          if (reserva.getRecurso() != null && reserva.getRecurso().getAutorizador() != null)
            aut2 = reserva.getRecurso().getAutorizador().getNombre();

          label =
            new Label(
              componentId, 
              (aut1 == null 
                ?
                  (aut2 == null ? "" : aut2)
                :
                  (aut2 == null || aut1.equals(aut2) ? aut1 : aut1 + ", " + aut2)));

          label.add(new SimpleAttributeModifier("style", stylist.getStyleFor(modelo.getObject())));        
          celda.add(label);
        }
      }
    );
    // Tabla solicitudes pendientes: acción
    colsPendientes.add(
    	new AbstractColumn<Reserva>(new Model<String>("Acción")){
        public void populateItem(
          Item<ICellPopulator<Reserva>> celda, 
          String id, 
          IModel<Reserva> modelo) {

          celda.add(new PanelEditarEliminarReserva(id, modelo));
        }    
      }
    );
    
    DefaultDataTable tablaPendientes = new DefaultDataTable("tablaPendientes",colsPendientes,new PendientesDataProvider(),10);    
    contenedorPendientes.add(tablaPendientes);
    contenedorPendientes.setVisible(reservaDao.totalReservasPendientes(usuario) > 0);
    
    add(contenedorAprobar);
    add(contenedorPendientes);
    
    // Tabla de todas las reservas realizadas
    List<IColumn<?>> colsTodas = new ArrayList<IColumn<?>>();
    // Tabla reservas: columna solicitante
    colsTodas.add(
      new ColumnaPropiedadColor(
        new Model<String>("Solicitante"), 
        "solicitante.nombre", 
        "solicitante.nombre", 
        stylist));
    // Tabla reservas: columna recurso    
    colsTodas.add(
      new ColumnaPropiedadColor(
      	new Model<String>("Recurso"), 
      	"recurso.nombre", 
      	"recurso.nombre", 
      	stylist
      )
    );
    // Tabla reservas: columna fecha inicio
    colsTodas.add(
      new ColumnaPropiedadColor(
        new Model<String>("Desde"), 
        "inicio", 
        "inicio", 
        stylist
      )
    );
    // Tabla reservas: columna fecha fin
    colsTodas.add(
      new ColumnaPropiedadColor(
        new Model<String>("Hasta"), 
        "fin", 
        "fin", 
        stylist
      )
    );
    // Tabla reservas: columna autorizador
    colsTodas.add(
      new ColumnaPropiedadColor(
      	new Model<String>("Autorizado por"), 
      	"autorizador.nombre", 
      	"autorizador.nombre", 
      	stylist
      )
    );
    // Tabla reservas: estado aprobación
    colsTodas.add(
      new AbstractColumn<Reserva>(new Model<String>("Estado")){
        public void populateItem(
          Item<ICellPopulator<Reserva>> celda, 
          String idComponente, 
          IModel<Reserva> modelo) {
        
          Label label = new Label(idComponente, new PropertyModel(modelo, "autorizacion"));                
          if (modelo.getObject().getAutorizacion() != null)
            label.add(new SimpleAttributeModifier("style", "color: " +  modelo.getObject().getAutorizacion().getColor()));        
          celda.add(label);
        }
      }
    );
    // Tabla reservas: estado inicial del recurso
    colsTodas.add(new AbstractColumn<Reserva>(new Model<String>("Estado Inicial")){
      public void populateItem(Item<ICellPopulator<Reserva>> celda, 
        String id, 
        IModel<Reserva> modelo) {
      	
        celda.add(
        	new Label(id, new PropertyModel(modelo, "estadoInicial")).add(
        		new SimpleAttributeModifier("style", "color: " + 
        			(modelo.getObject().getEstadoInicial() != null ?
                modelo.getObject().getEstadoInicial().getColor()
                : "inherit") 
            )
        	)
        );
      }

      @Override
      public String getSortProperty() {
        return "estadoInicial";
      }
      
      @Override
      public boolean isSortable() {
        return true;
      } 
    });
    // Tabla reservas: estado final del recurso  
    colsTodas.add(
    	new AbstractColumn<Reserva>(new Model<String>("Estado Final")){
        public void populateItem(
        	Item<ICellPopulator<Reserva>> celda, 
          String id, 
          IModel<Reserva> modelo) {
          
        	Reserva reserva = modelo.getObject();
        
          if (reserva.getEstadoFinal() != null) {
            celda.add(
            	new Label(id, new PropertyModel(modelo, "estadoFinal")).add(
            		new SimpleAttributeModifier("style", "color: " + (modelo.getObject().getEstadoFinal() != null ?
                  modelo.getObject().getEstadoFinal().getColor()
                  : "inherit") 
                ))
              );
          } 
          else {
            /* se puede actualizar el estado final si:
             *   es autorizador o
             *   no hay autorizador y es el solicitante o
             *   es administrador
             *
             * no se puede establecer si el estado del recurso se ha actualizado ya
             * (final de reserva pasado)
             */
            Usuario usuario = ((Session)getSession()).getUsuarioActual();
            boolean puedeActualizar = 
              usuario.equals(reserva.getAutorizador()) ||
              (usuario.equals(reserva.getSolicitante()) && reserva.getAutorizador() == null) ||
              usuario.isAdmin();          
            puedeActualizar &= (reserva.getFin().after(reserva.getRecurso().getFechaActualizacion()));
          
            if (puedeActualizar) {
              celda.add(new PanelEstadoFinal(id, modelo));
            } 
          }
      }
        
      @Override
      public String getSortProperty() {
        return "estadoFinal";
      }
      
      @Override
      public boolean isSortable() {
        return true;
      }
    });
            
    DefaultDataTable tablaTodas = new DefaultDataTable("tablaTodas", colsTodas, new TodasDataProvider(), 10);    
    add(tablaTodas);
  }

  public ServicioCorreo getServicioCorreo() {
    return servicioCorreo;
  }

  public ReservaDao getReservaDao() {
    return reservaDao;
  }
  
	public class AprobarDataProvider extends SortableDataProvider<Reserva> {
  	
    public AprobarDataProvider() {
      setSort("fin", true);
    }
    
    public Iterator<? extends Reserva> iterator(int primera, int max) {
      SortParam sp = getSort();
      Usuario usuario = ((Session)getSession()).getUsuarioActual();
      
      return getReservaDao()
        .getReservasAprobables(usuario, primera, max, sp.getProperty(), sp.isAscending()).iterator();
    }

    public IModel<Reserva> model(Reserva r) {
      return new DetachableReservaModel(getReservaDao(), r);
    }

    public int size() {
      Usuario usuario = ((Session)getSession()).getUsuarioActual();      
      return getReservaDao().totalReservasAprobables(usuario);
    }    
  }
  
	public class PendientesDataProvider extends SortableDataProvider<Reserva> {

    public PendientesDataProvider() {
      setSort("fin", true);
    }
    
    public Iterator<? extends Reserva> iterator(int primera, int max) {
      SortParam sp = getSort();
      Usuario usuario = ((Session)getSession()).getUsuarioActual();      
      return getReservaDao()
        .getReservasPendientes(usuario, primera, max, sp.getProperty(), sp.isAscending()).iterator();
    }

    public IModel<Reserva> model(Reserva r) {
      return new DetachableReservaModel(getReservaDao(), r);
    }

    public int size() {
      Usuario usuario = ((Session)getSession()).getUsuarioActual();
      return getReservaDao().totalReservasPendientes(usuario);
    }    
  }
  
	public class TodasDataProvider extends SortableDataProvider<Reserva> {
      
    public TodasDataProvider() {
      setSort("fin", true);
    }
    
    public Iterator<? extends Reserva> iterator(int primera, int max) {
      SortParam sp = getSort();
      Usuario usuario = ((Session)getSession()).getUsuarioActual();
      
      return getReservaDao()
        .getReservas(usuario, primera, max, sp.getProperty(), sp.isAscending()).iterator();
    }

    public IModel<Reserva> model(Reserva r) {
      return new DetachableReservaModel(getReservaDao(), r);
    }

    public int size() {
      Usuario usuario = ((Session)getSession()).getUsuarioActual();      
      return getReservaDao().totalReservas(usuario);
    }    
  }
  
	public static class DetachableReservaModel extends LoadableDetachableModel<Reserva> {
    
    private int id;
    private ReservaDao reservaDao;
    
    public DetachableReservaModel(ReservaDao reservaDao, Reserva reserva) {
      super(reserva);      
      this.reservaDao = reservaDao;      
      id = reserva.getId();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) return false;      
      if (getClass() != obj.getClass()) return false;
      final DetachableReservaModel otro = (DetachableReservaModel) obj;
      if (this.id != otro.id) return false;      
      return true;
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 53 * hash + this.id;
      return hash;
    }

    @Override
    protected Reserva load() {
      return reservaDao.getReserva(id);
    }
  }  
}