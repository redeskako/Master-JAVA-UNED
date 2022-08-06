/*
 *
 *
 */

package com.arquillos.gestres.service;

import com.arquillos.gestres.data.Reserva;

/**
 *
 * @author Antonio Jes�s Arquillos �lvarez
 */
public interface ServicioCorreo {
  void enviarCorreoPrueba(String from, String to);  
  void notifReservaCreada(Reserva reserva);
  void notifReservaAprobada(Reserva reserva);
  void notifReservaDenegada(Reserva reserva);
}