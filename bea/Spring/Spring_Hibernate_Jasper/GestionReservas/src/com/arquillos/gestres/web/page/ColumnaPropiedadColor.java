/*
 *
 *
 */
package com.arquillos.gestres.web.page;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ColumnaPropiedadColor extends PropertyColumn {

  private Stylist stylist;
  
	public ColumnaPropiedadColor( IModel<String> arg0, String arg1, Stylist stylist ) {
    super( arg0, arg1 );
    this.stylist = stylist;
  }

	public ColumnaPropiedadColor( IModel<String> arg0, String arg1, String arg2, Stylist stylist ) {
    super( arg0, arg1, arg2 );
    this.stylist = stylist;
  }
  
  private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );

	@Override
  public void populateItem( Item item, String id, IModel modelo ) {
    
    PropertyModel m = new PropertyModel( modelo, getPropertyExpression() );

    if ( m.getObject() instanceof Date ) {
      Label label = new Label( id, sdf.format( m.getObject() ) );
      label.add( crearAtributoColor( modelo ) );
      item.add( label );
    } 
    else {
      Label label = new Label( id, createLabelModel( modelo ) );
      label.add( crearAtributoColor( modelo ) );
      item.add( label );
    }
  }

	private SimpleAttributeModifier crearAtributoColor( IModel modelo ) {        
    return new SimpleAttributeModifier( "style", stylist.getStyleFor( modelo.getObject() ) );
  }
}