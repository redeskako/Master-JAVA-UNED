
/**
 * Clase Gestion Mensajes
 * Construye los mensajes de usuario
 * Version 1
 * @author jrequeijo2, Jose lopez
 */

package es.uned.master.java.grajeGUI.capaGestion;

public class GestionMensajes {
   private String msg;

   public GestionMensajes() {
		
   }
   
public void setMsg(String msg) {
	if (msg.equals("lleno")){
		this.msg ="PARKING LLENO, NO HAY PLAZAS DISPONIBLES";
	} 
}
/**
 * 
 * @return mensaje mostrado en pantalla
 */
public String getMsg() {
	return msg;
}
/**
 * 
 * @return tickets de entrada/salida
 */
public void setMsg(int planta, int plaza ,String tipoTicket, String fec, String fec2) {
	 String plantaAsignada =""; 
	 if (planta==0){
		 plantaAsignada="Sotano";
	 }else{
		 planta = planta -1;
		 plantaAsignada= String.valueOf(planta);
	 }
	 String mensaje = "TICKET  DE " + tipoTicket +"\n" +
		"==================\n" +
		"\n"+
		"Plaza Asignada \n" +
		"Planta: " + plantaAsignada + "\n" +
		"Plaza: " + plaza + "\n" +
		"Hora de Entrada: " + fec + "\n"; 
	 if (tipoTicket.equals("SALIDA")){
		 mensaje = mensaje + "Hora de Salida: " + fec2 + "\n"; 
	 }
	this.msg = mensaje;
}
   
}
