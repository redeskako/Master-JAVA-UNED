/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.PanelCalendario;
import com.arquillos.gestres.web.page.component.PanelNuevaReserva;
import com.arquillos.gestres.web.page.component.TituloMenu;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author 
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Autorizado
public class PaginaCalendario extends WebPage {
  
  @SpringBean
  private RecursoDao recursoDao;  
  @SuppressWarnings("unused")
	private Page paginaAnterior;
  
	public PaginaCalendario() {
  	add(new TituloMenu("tituloMenu"));
  	add(HeaderContributor.forCss(PanelCalendario.class, "css/styles.css"));
    if (recursoDao.getTotalRecursos() > 0) {
      add(
      	new PanelNuevaReserva(
          "nuevaReserva", 
          new Model(
          new Reserva(((Session)getSession()).getUsuarioActual())), null)
      	);
    } 
    else {
      add(labelNoHayRecursos());
    }
    add(new PanelCalendario("calendario"));
  }

  public PaginaCalendario(IModel<Reserva> modelo) {
    super(modelo);
  	add(HeaderContributor.forCss(PanelCalendario.class, "css/styles.css"));
    add(new TituloMenu("tituloMenu"));
    if (recursoDao.getTotalRecursos() > 0) {
      add(new PanelNuevaReserva("nuevaReserva", modelo, null));
    } 
    else {
      add(labelNoHayRecursos());
    }
    add(new PanelCalendario("calendario"));
  }
  
  public PaginaCalendario(IModel<Reserva> modelo, Page paginaAnterior) {
    super(modelo);
  	add(HeaderContributor.forCss(PaginaCalendario.class, "css/styles.css"));
    this.paginaAnterior = paginaAnterior;    
  	add(new TituloMenu("tituloMenu"));
    if (recursoDao.getTotalRecursos() > 0) {
      add(new PanelNuevaReserva("nuevaReserva", modelo, paginaAnterior));
    } else {
      add(labelNoHayRecursos());
    }
    add(new PanelCalendario("calendario"));
  }
  
  private Label labelNoHayRecursos() {
    Label label = new Label("nuevaReserva", "¡No hay recursos que reservar!");    
    label.add(
      new SimpleAttributeModifier("style", "font-weight: bold; font-size: 200%; text-align: center")
    );
    return label;
  }
}