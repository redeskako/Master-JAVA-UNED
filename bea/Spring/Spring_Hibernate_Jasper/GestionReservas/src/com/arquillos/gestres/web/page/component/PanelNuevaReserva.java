/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.data.EstadoReserva;
import com.arquillos.gestres.service.ServicioCorreo;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.PaginaCalendario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

/**
 *
 * @author Antonio Jesús Arquillos Álvarezz
 */
@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class PanelNuevaReserva extends Panel {
  @SpringBean 
  private UsuarioDao usuarioDao;  
  @SpringBean
  private RecursoDao recursoDao;  
  @SpringBean
  private ReservaDao reservaDao;  
  @SpringBean
  private ServicioCorreo servicioCorreo;
  
  private QuantizedTime[] horas;
  
  private Model<QuantizedTime> desdeTimeModel;
  private Model<QuantizedTime> hastaTimeModel;    
  
	public PanelNuevaReserva(String id, IModel<Reserva> modelo, final Page paginaAnterior) {
    super(id, modelo);
    
    generarHoras();  
    Form formReserva = new Form("formReserva"){
      @Override
      protected void onSubmit() {
        Reserva res = (Reserva)PanelNuevaReserva.this.getDefaultModelObject();        
        // cambiar estado si necesitamos un autorizador
        if ((res.getSolicitante().getAutorizador() != null &&
             !res.getSolicitante().getAutorizador().equals(res.getSolicitante()))
            || (res.getRecurso().getAutorizador() != null) &&
             !res.getRecurso().getAutorizador().equals(res.getSolicitante())) {
            res.setAutorizacion(EstadoReserva.PENDIENTE);            
        } else {
          res.setAutorizacion(EstadoReserva.APROBADA);
        }        
        res.setCreacion(new Date());
        res.setInicio(desdeTimeModel.getObject().combinarCon(res.getInicio()));
        res.setFin(hastaTimeModel.getObject().combinarCon(res.getFin()));        
        res.setEstadoPrevio(res.getRecurso().getEstado());        
        reservaDao.salvarReserva(res);
        
        servicioCorreo.notifReservaCreada(res);
        
        if (paginaAnterior!= null)
          setResponsePage(paginaAnterior);
        else
          setResponsePage(new PaginaCalendario());
      }      
    };    
    formReserva.add(new FeedbackPanel("feedback"));
    
    WebMarkupContainer contenedorSeleccionUsuario = new WebMarkupContainer("contenedorSeleccionUsuario");    
    contenedorSeleccionUsuario.add(
      new DropDownChoice("seleccionUsuario", 
      new PropertyModel(modelo, "solicitante"),
      usuarioDao.getUsuarios())
    );
    contenedorSeleccionUsuario.setVisible(((Session)getSession()).getUsuarioActual().isAdmin());    
    formReserva.add(contenedorSeleccionUsuario);
    
    DropDownChoice seleccionRecurso = new DropDownChoice(
    		  "seleccionRecurso", 
          new PropertyModel(modelo, "recurso"),
          recursoDao.getRecursos()
    );
    seleccionRecurso.setLabel(new Model("recurso"));
    seleccionRecurso.add(new AbstractValidator(){
      @Override
      protected void onValidate(IValidatable validatable) {
        Recurso r = (Recurso)validatable.getValue();        
        if (r == null)
          error(validatable);
      }

      @Override
      protected String resourceKey() {
        return "DebeSerValido";
      }

      @Override
      public boolean validateOnNullValue() {
        return true;
      }      
    });
    
    formReserva.add(
        new FormComponentFeedbackBorder("bordeSeleccionRecurso")
          .add(seleccionRecurso));    
    
    DateField fechaDesde = new DateField("fechaDesde", new PropertyModel(modelo, "inicio"));
    fechaDesde.setRequired(true);
    fechaDesde.setLabel(new Model("Fecha Desde"));
    formReserva.add(fechaDesde);
        
    desdeTimeModel = new Model<QuantizedTime>(horaMasProxima(modelo.getObject().getInicio())); 
    DropDownChoice horaDesde = new DropDownChoice("horaDesde", desdeTimeModel, Arrays.asList(horas));
    formReserva.add(horaDesde);    
    
    DateField fechaHasta = new DateField("fechaHasta", new PropertyModel(modelo, "fin"));
    fechaHasta.setRequired(true);
    fechaHasta.setLabel(new Model("Fecha Hasta"));
    formReserva.add(fechaHasta);
        
    hastaTimeModel = new Model<QuantizedTime>(horaMasProxima(modelo.getObject().getFin())); 
    DropDownChoice horaHasta = new DropDownChoice("horaHasta", hastaTimeModel, Arrays.asList(horas));
    formReserva.add(horaHasta);
    
    formReserva.add(
        new ReservasSinConflictos(modelo.getObject(), seleccionRecurso, 
        fechaDesde, horaDesde, fechaHasta, horaHasta));
    
    formReserva.add(
        new Button("botonNuevaReserva", 
        new PropertyModel(this, "textoBotonNuevaReserva")));
    
    Button botonCancelarReserva = new Button("botonCancelarReserva", new Model("No se modificará la reserva")){
      @Override
      public void onSubmit() {        
        setResponsePage(paginaAnterior);
      }
    };
    botonCancelarReserva.setDefaultFormProcessing(false);
    botonCancelarReserva.setVisible(!modelo.getObject().isNueva() && paginaAnterior != null);
    formReserva.add(botonCancelarReserva);
    
    add(formReserva);
    
  }
  
  public String getTextoBotonNuevaReserva() {
    if (((Reserva)getDefaultModelObject()).isNueva())
      return "Solicitar Reserva";
    else
      return "Cambiar Reserva";
  }
  
  private void generarHoras() {
    
    // cada media hora
    horas = new QuantizedTime[ 48 ];
    
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);    
    
    SimpleDateFormat sdf = new SimpleDateFormat("h:mm aa");
    
    for (int i = 0; i < horas.length; i++) {
      c.getTime();
      
      horas[ i ] = new QuantizedTime(
          c.get(Calendar.HOUR_OF_DAY),
          c.get(Calendar.MINUTE),
          sdf.format(c.getTime()));
      c.add(Calendar.MINUTE, 30);
    }
  }
  
	public static class QuantizedTime implements Serializable {
    private int hora;
    private int minuto;
    private String display;

    public QuantizedTime(int hora, int minuto, String display) {
      this.hora = hora;
      this.minuto = minuto;
      this.display = display;
    }

    public String getDisplay() {
      return display;
    }

    public void setDisplay(String display) {
      this.display = display;
    }

    public int getHora() {
      return hora;
    }

    public void setHora(int hora) {
      this.hora = hora;
    }

    public int getMinuto() {
      return minuto;
    }

    public void setMinuto(int minuto) {
      this.minuto = minuto;
    }

    public Date combinarCon(Date d) {
      Calendar c = Calendar.getInstance();
      c.setTime(d);
      
      Calendar c2 = new GregorianCalendar(
          c.get(Calendar.YEAR), 
          c.get(Calendar.MONTH),
          c.get(Calendar.DATE),
          hora, minuto);
      
          
      return c2.getTime();
    }
    
    @Override
    public String toString() {
      return display;
    }   
  }  
  
  public QuantizedTime horaMasProxima(Date d) {
    Calendar c = Calendar.getInstance();
    
    c.setTime(d);
    
    int horaActual = c.get(Calendar.HOUR_OF_DAY);
    int minutoActual = c.get(Calendar.MINUTE);
        
    for (int i = 0; i < horas.length; i++) {
      int mejorHora = horas[ i ].getHora();
      int mejorMinuto = horas[ i ].getMinuto();      
      if (mejorHora > horaActual || (mejorHora == horaActual && mejorMinuto >= minutoActual)) {
        return horas[ i ];
      }
    }
    
    return horas[ 0 ];
  } 
  
	public class ReservasSinConflictos extends AbstractFormValidator {

    private Reserva reservaExistente;
    private DateField fechaDesde;    
    private DropDownChoice<QuantizedTime> horaDesde;
    private DateField fechaHasta;
    private DropDownChoice<QuantizedTime> horaHasta;
    private DropDownChoice<Recurso> seleccionRecurso;
    
    public ReservasSinConflictos(
      Reserva reservaExistente,
      DropDownChoice<Recurso> seleccionRecurso,
      DateField fechaDesde, 
      DropDownChoice<QuantizedTime> horaDesde, 
      DateField fechaHasta, 
      DropDownChoice<QuantizedTime> horaHasta) {
      
    	this.reservaExistente = reservaExistente;
      this.seleccionRecurso = seleccionRecurso;
      this.fechaDesde = fechaDesde;
      this.horaDesde = horaDesde;
      this.fechaHasta = fechaHasta;
      this.horaHasta = horaHasta;
    }

		public FormComponent[] getDependentFormComponents() {
      return new FormComponent[]{seleccionRecurso, fechaDesde, 
        horaDesde, fechaHasta, horaHasta};
    }

    public void validate(Form<?> form) {           
      Date inicio = horaDesde.getConvertedInput().combinarCon(fechaDesde.getConvertedInput());            
      Date fin = horaHasta.getConvertedInput().combinarCon(fechaHasta.getConvertedInput());
      if (fin.before(new Date())) {
        error(fechaDesde, "InicioAntesAhora");
      } 
      else if (!inicio.before(fin)) {
        error(fechaHasta, "InicioAntesFin");         
      } 
      else {
        List<Reserva> conflictos =               
          reservaDao.analizarConflictos(
          		reservaExistente, 
          		seleccionRecurso.getConvertedInput(), 
          		inicio, 
          		fin);
        
        if (conflictos != null && !conflictos.isEmpty()) {
          Reserva r1 = conflictos.get(0);          
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("usuario", r1.getSolicitante().getNombre());
          map.put("recurso", r1.getRecurso().getNombre());
          map.put("inicio", r1.getInicio());
          map.put("fin", r1.getFin());                    
          error(fechaHasta, "ReservasConflictos", map);
        }        
      }
    }
  }
}