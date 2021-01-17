/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.web.page.component.PanelCalendario.Dia;
import com.arquillos.gestres.web.page.component.PanelCalendario.Dia.MensajeFormateado;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class DiaCalendario extends Panel {

	public DiaCalendario(String id, IModel<Dia> modelo) {
    super(id, modelo);   
    
    Dia d = modelo.getObject();
      
    WebMarkupContainer dia = new WebMarkupContainer( "dia" );
    
    Label fecha = new Label("fecha", new Model(d.getDia()));
    Label nombreDia = new Label("nombreDia", d.getNombre());
    nombreDia.setVisible(d.getNombre() != null);
    
    dia.add(fecha);
    dia.add(nombreDia);
    
    dia.add(new ListView<MensajeFormateado>("mensajes", d.getMensajes()){
      @Override
      protected void populateItem(ListItem<MensajeFormateado> item) {
        MensajeFormateado mf = item.getModelObject();        
        item.add(new Label("mensaje", mf.getMensaje()).add(
          new SimpleAttributeModifier("style", mf.getFormato()))
        );
      }            
    });
    
    add(dia);    
  }
}