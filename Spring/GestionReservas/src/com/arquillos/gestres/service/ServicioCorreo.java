/*
 *
 *
 */

package com.arquillos.gestres.service;

import com.arquillos.gestres.data.Reserva;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public interface ServicioCorreo {
  void enviarCorreoPrueba(String from, String to);  
  void notifReservaCreada(Reserva reserva);
  void notifReservaAprobada(Reserva reserva);
  void notifReservaDenegada(Reserva reserva);
}