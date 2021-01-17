/*
 *
 *
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.data.Color;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.EstadoReserva;
import com.arquillos.gestres.data.Usuario;

/**
 *
 * @author Fictional Hero
 */
public interface Stylist<T> {
  String getStyleFor(T t);
  
  public static class ParaReserva implements Stylist<Reserva> {

    public String getStyleFor(Reserva reserva) {
      String style = null;

      if (reserva.getSolicitante().getColor().equals(Color.DEFECTO)) {
        style = "color: " + reserva.getRecurso().getColor().getHexColor();
      } else {
        style = "color: " + reserva.getSolicitante().getColor().getHexColor();
      }
      if (reserva.getAutorizacion().equals(EstadoReserva.PENDIENTE)) {
        style += "; font-style: italic";
      }
      
      return style;
    }    
  }
  
  public static class ParaUsuario implements Stylist<Usuario> {

    public String getStyleFor(Usuario u) {
      return "color: " + 
            (u != null && u.getColor() != null 
              ? 
                u.getColor().getHexColor() 
              : 
                Color.DEFECTO.getHexColor());
    }    
  }
}
