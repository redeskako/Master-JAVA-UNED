/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Color;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.EstadoReserva;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class PanelCalendario extends Panel {
  
	@SpringBean
  private ReservaDao reservaDao;
  
  private String[] meses;  
  private int[] años;
  private String[] dias;
  
  private Model<CalendarioDataProvider> calendarioDataModel;
  	
	public PanelCalendario(String id) {    
    super(id);
    
    add(HeaderContributor.forCss(PanelCalendario.class, "PanelCalendario.css"));
    
    inicializarDiasMesesAños();
    
    setOutputMarkupId(true);
    
    CalendarioDataProvider cdp = 
        new CalendarioDataProvider(dias, años);
    
    calendarioDataModel = new Model<CalendarioDataProvider>(cdp);    
   
    final GridView<Dia> gridCalendario = new GridView<Dia>( "filasCalendario", cdp) {
			@Override
      protected void populateEmptyItem(Item<Dia> item) {        
        item.add(new Label("diaCalendario", "*vacío*"));
      }

      @Override
      protected void populateItem(Item<Dia> item) {
        Dia d = item.getModelObject();
        item.add(new DiaCalendario("diaCalendario", new Model<Dia>(d)));
        
        if (d.isUltimoMes())
          item.add(new SimpleAttributeModifier("class", "ultimoMes"));
        else if (d.isSiguienteMes())
          item.add(new SimpleAttributeModifier("class", "siguienteMes"));
        else if (d.isHoy())
          item.add(new SimpleAttributeModifier("class", "hoy"));
        else if (d.isPasado())
          item.add(new SimpleAttributeModifier("class", "pasado"));
        else
          item.add(new SimpleAttributeModifier("class", "futuro"));
      }      
    };
    gridCalendario.setOutputMarkupId(true);
    gridCalendario.setOutputMarkupPlaceholderTag(true);
    
    gridCalendario.setColumns(7);
    gridCalendario.setRows(6);
    add(gridCalendario);
    
    final DropDownChoice seleccionMes = 
        new DropDownChoice(
          "seleccionMes", 
          new PropertyModel(calendarioDataModel, "mesActual"), 
          crearLista(0, meses.length),
          new IChoiceRenderer(){
						public Object getDisplayValue(Object value) {
              Integer index = (Integer) value;
              return meses[index];
            }

            public String getIdValue(Object value, int index) {
              return Integer.toString(index);
            }      
          }){
			@Override
      protected boolean wantOnSelectionChangedNotifications() {
        return true;
      }      
    };
        
    
    add(seleccionMes);
    seleccionMes.setOutputMarkupId(true);
    seleccionMes.setOutputMarkupPlaceholderTag(true);    
    
    final DropDownChoice yearChoice = new DropDownChoice("seleccionAno", 
        new PropertyModel(calendarioDataModel, "indiceAñoActual"),
        crearLista(0, años.length),
        new IChoiceRenderer(){
					public Object getDisplayValue(Object value) {
            Integer index = (Integer) value;
            return años[index];
          }
          
          public String getIdValue(Object value, int index) {
            return Integer.toString(index);
          }
        }){

			@Override
      protected boolean wantOnSelectionChangedNotifications() {
        return true;
      }          
    };
    
    add(yearChoice);
    yearChoice.setOutputMarkupId(true);
    yearChoice.setOutputMarkupPlaceholderTag(true);
    
    
    add(new AjaxFallbackLink("anoAnterior"){
			@Override
      public void onClick(AjaxRequestTarget target) {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        cdp.irAñoAnterior();
            
        target.addComponent(PanelCalendario.this);
      }
      @Override
      public boolean isEnabled() {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        return cdp.getIndiceAñoActual() > 0;
      }      
    });
    
    add(new AjaxFallbackLink("anoSiguiente"){
			@Override
      public void onClick(AjaxRequestTarget target) {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        cdp.irAñoSiguiente();        
        target.addComponent(PanelCalendario.this);
      }
      
      @Override
      public boolean isEnabled() {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        return cdp.getIndiceAñoActual() < años.length-1;
      }
    });
    
    add(new AjaxFallbackLink("mesAnterior") {
			@Override
      public void onClick(AjaxRequestTarget target) {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        cdp.irMesAnterior();
        target.addComponent(PanelCalendario.this);
      }
      @Override
      public boolean isEnabled() {
        CalendarioDataProvider cdp = (CalendarioDataProvider)
            calendarioDataModel.getObject();
        
        return cdp.getIndiceAñoActual() > 0 
            || cdp.getMesActual() > 0;
      }
    });   
    
    add(new AjaxFallbackLink("mesSiguiente") {
			@Override
      public void onClick(AjaxRequestTarget target) {
        CalendarioDataProvider cdp = (CalendarioDataProvider)calendarioDataModel.getObject();        
        cdp.irMesSiguiente();
        target.addComponent(PanelCalendario.this);
      }
      
      @Override
      public boolean isEnabled() {
        CalendarioDataProvider cdp = (CalendarioDataProvider)calendarioDataModel.getObject();        
        return cdp.getIndiceAñoActual() < años.length-1 || cdp.getMesActual() < meses.length-1;
      }
    });
  }
  
  private void inicializarDiasMesesAños() {
    Calendar c = Calendar.getInstance();
    
    años = new int[ 21 ];
    for (int i = -10; i <= 10; i++) {
      años[ i + 10 ] = c.get(Calendar.YEAR) + i;
    }
    
    meses = new String[ 12 ];
    Locale locale = Locale.getDefault();
    for (int i = 0; i < meses.length; i++) {
      c.set(Calendar.MONTH, i);
      meses[i] = c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
    }
    
    dias = new String[ 7 ];
    for (int i = 0; i < dias.length; i++) {
      c.set(Calendar.DAY_OF_WEEK, (i + 2) % 7 );
      dias[ i ] = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale);
    }
  } 
  
	public static class Dia implements Serializable {    
		private int dia;
    private int mes;
    private int año;
    private boolean hoy;
    private boolean ultimoMes;
    private boolean siguienteMes;
    private boolean pasado;
    private String nombre;
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");    
    private List<MensajeFormateado> mensajes;

    public Dia() {
      mensajes = new ArrayList<MensajeFormateado>();
    }   

    public int getDia() {
      return dia;
    }

    public void setDia(int dia) {
      this.dia = dia;
    }

    public int getMes() {
      return mes;
    }

    public void setMes(int mes) {
      this.mes = mes;
    }

    public int getAño() {
      return año;
    }

    public void setAño(int año) {
      this.año = año;
    }      
    
    public boolean isHoy() {
      return hoy;
    }

    public void setHoy(boolean hoy) {
      this.hoy = hoy;
    }

    public boolean isUltimoMes() {
      return ultimoMes;
    }

    public void setUltimoMes(boolean ultimoMes) {
      this.ultimoMes = ultimoMes;
    }

    public boolean isSiguienteMes() {
      return siguienteMes;
    }

    public void setSiguienteMes(boolean siguienteMes) {
      this.siguienteMes = siguienteMes;
    }

    public boolean isPasado() {
      return pasado;
    }

    public void setPasado(boolean pasado) {
      this.pasado= pasado;
    }

    public String getNombre() {
      return nombre;
    }

    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

    public List<MensajeFormateado> getMensajes() {
      return mensajes;
    }

    public void nuevoMensajePara(Reserva reserva) {
      Calendar c = new GregorianCalendar(año, mes, dia, 0, 0, 0);
      
      Date inicio = c.getTime();
      
      c.add(Calendar.DATE, 1);
      c.add(Calendar.SECOND, -1);
      
      Date fin = c.getTime();
      
      if (reserva.validarPeriodo(inicio, fin)) {
        String formato = null;
        String mensaje = null;
        if (reserva.getSolicitante().getColor().equals(Color.DEFECTO))
          formato = "color: " + reserva.getRecurso().getColor().getHexColor();
        else
          formato = "color: " + reserva.getSolicitante().getColor().getHexColor();
        
        if (!reserva.getAutorizacion().equals(EstadoReserva.APROBADA))
          formato += "; font-style: italic; size: 50%";
        
        if (inicio.before(reserva.getInicio())) {
          if (fin.after(reserva.getFin())) {
            // inicio y fin en día actual
            mensaje = reserva.getSolicitante().getNombre() 
                + " tiene " 
                + reserva.getRecurso()
                + " desde "
                + sdf.format(reserva.getInicio())
                + " hasta "
                + sdf.format(reserva.getFin());
          } else {
            // inicio hoy, fin mañana o más tarde
            mensaje = reserva.getSolicitante().getNombre() 
                + " tiene " 
                + reserva.getRecurso()
                + " desde "
                + sdf.format(reserva.getInicio());                
          }
        } else if (fin.after(reserva.getFin())) {
          // inicio ayer o antes, fin hoy
          mensaje = reserva.getSolicitante().getNombre() 
                + " tiene " 
                + reserva.getRecurso()                
                + " hasta "
                + sdf.format(reserva.getFin());
        } else {
          // inicio ayer o antes, fin mañana o después
          mensaje = reserva.getSolicitante().getNombre() 
                + " aún tiene " 
                + reserva.getRecurso();
                
        }
        mensajes.add(new MensajeFormateado(mensaje, formato));
      }
    }
    
    public static class MensajeFormateado {
      private String mensaje;
      private String formato;

      public MensajeFormateado(String mensaje, String formato) {
        this.mensaje = mensaje;
        this.formato = formato;
      }

      public String getFormato() {
        return formato;
      }

      public String getMensaje() {
        return mensaje;
      }     
    }
  }
  
  public static class DiaIterator implements Iterator<Dia> {
    private Dia[] dias;
    private int primero;
    private int total;
    private int actual;

    public DiaIterator(Dia[] dias, int primero, int total) {
      this.dias = dias;
      this.primero = primero;
      this.total = total;
      actual = 0;
    }

    public boolean hasNext() {
      return actual < total;
    }

    public Dia next() {
      Dia d = dias[ primero + actual ];
      actual++;
      return d;
    }

    public void remove() {
      throw new UnsupportedOperationException("No soportado.");
    }       
  }
  
	public class CalendarioDataProvider implements IDataProvider<Dia> {    
		private Dia[] dias;    
    
    private int indiceAñoActual;
    private int mesActual;
    
    private String[] nombresDias;
    private int[] añosDisponibles;

    public CalendarioDataProvider(String[] nombresDias, int[] añosDisponibles) {
      Calendar c = Calendar.getInstance();
            
      for (int i = 0; i < añosDisponibles.length; i++)
        if (añosDisponibles[i] == c.get(Calendar.YEAR))
          indiceAñoActual = i;
      
      mesActual = c.get(Calendar.MONTH);
      
      this.nombresDias = nombresDias;
      this.añosDisponibles = añosDisponibles;
      
      crearDias();
    }
    
    public int getMesActual() {
      return mesActual;
    }

    public void setMesActual(int mesActual) {
      this.mesActual = mesActual;
      crearDias();
    }
    
    public int getIndiceAñoActual() {
      return indiceAñoActual;
    }
    
    public void setIndiceAñoActual(int i) {      
      indiceAñoActual = i;
      crearDias();
    }
    
    public Calendar getPrimerDia() {
      Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, añosDisponibles[indiceAñoActual]);
      c.set(Calendar.MONTH, mesActual);
      c.set(Calendar.DATE, 1);
      c.set(Calendar.HOUR_OF_DAY, 0);
      c.set(Calendar.MINUTE, 0);
      c.set(Calendar.SECOND, 0);
      c.set(Calendar.MILLISECOND, 0);
      
      c.getTime();
      
      return c;
    }
    
    public Calendar getUltimoDia() {
      Calendar c = getPrimerDia();
      c.add(Calendar.MONTH, 1);
      c.add(Calendar.SECOND, -1);
      
      c.getTime();
      
      return c;
    }
    
    public void irAñoAnterior() {
      indiceAñoActual--;
      crearDias();
    }
    
    public void irAñoSiguiente() {
      indiceAñoActual++;
      crearDias();
    }
    
    public void irMesAnterior() {
      mesActual--;
      if (mesActual < 0) {
        mesActual = 11;
        indiceAñoActual--;
      }
      crearDias();
    }
    
    public void irMesSiguiente() {
      mesActual++;
      if (mesActual > 11) {
        mesActual = 0;
        indiceAñoActual++;
      }
      crearDias();
    }
    
    private boolean isUltimoMesOAntes(Calendar destino, Calendar ahora) {
      if (destino.get(Calendar.YEAR) == ahora.get(Calendar.YEAR)) {
        return (destino.get(Calendar.MONTH) < ahora.get(Calendar.MONTH));
      } else if (destino.get(Calendar.YEAR) < ahora.get(Calendar.YEAR)) {
        return true;
      } else 
        return false;
    }
    
    private boolean isSiguienteMesODespues(Calendar destino, Calendar ahora) {
      if (destino.get(Calendar.YEAR) == ahora.get(Calendar.YEAR)) {
        return (destino.get(Calendar.MONTH) > ahora.get(Calendar.MONTH));
      } else if (destino.get(Calendar.YEAR) > ahora.get(Calendar.YEAR)) {
        return true;
      } else 
        return false;
    }
    
    private boolean isHoy(Calendar destino, Calendar ahora) {
      return destino.get(Calendar.YEAR) == ahora.get(Calendar.YEAR) 
          && destino.get(Calendar.MONTH) == ahora.get(Calendar.MONTH)
          && destino.get(Calendar.DATE) == ahora.get(Calendar.DATE);
    }
    
    private void crearDias() {
      Calendar ahora = Calendar.getInstance();      
      Calendar calendario = getPrimerDia();
      calendario.set(Calendar.DAY_OF_WEEK, 2);
      calendario.getTime();
      
      List<Reserva> reservas = reservaDao.getReservas(
          getPrimerDia().getTime(),
          getUltimoDia().getTime());      
      
      dias = new Dia[42];      
      
      for (int i = 0; i < 42; i++) {        
        Dia d = new Dia();
        d.setDia(calendario.get(Calendar.DAY_OF_MONTH));
        d.setMes(calendario.get(Calendar.MONTH));
        d.setAño(calendario.get(Calendar.YEAR));        
        if (calendario.before(ahora)) {
          d.setPasado(true);
          d.setUltimoMes(isUltimoMesOAntes(calendario, ahora));
          d.setSiguienteMes(false);
        } else {
          d.setPasado(false);
          d.setSiguienteMes(isSiguienteMesODespues(calendario, ahora));
          d.setUltimoMes(false);
        }
        d.setHoy(isHoy(calendario, ahora));
        
        if (i < 7)
          d.setNombre(nombresDias[i]);
        
        for (Reserva r : reservas)
          d.nuevoMensajePara(r);
        
        dias[i] = d;
        calendario.add(Calendar.DATE, 1);
      }
    }

    public Iterator<? extends Dia> iterator(int primero, int total) {
      return new DiaIterator(dias, primero, total);
    }

		public IModel<Dia> model(Dia dia) {
      return new Model(dia);
    }

    public int size() {
      return dias.length;
    }

    public void detach() {      
    }   
  }
  
  private static List<Integer> crearLista(int desde, int hasta) {
    ArrayList<Integer> lista = new ArrayList<Integer>(Math.max(desde, hasta) - Math.min(hasta, desde));
    
    if (hasta != desde) {
      int inicio = desde;
      int delta = (hasta - desde) / Math.abs(hasta - desde);
      
      while (inicio != hasta) {
        lista.add(inicio);
        inicio += delta;
      }
    }
    
    return lista;
  }
}