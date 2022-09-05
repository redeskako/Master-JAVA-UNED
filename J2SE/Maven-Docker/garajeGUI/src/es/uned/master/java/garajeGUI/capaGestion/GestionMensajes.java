package es.uned.master.java.garajeGUI.capaGestion;

public class GestionMensajes {
   private String msg;
   public GestionMensajes(){
   }
   public void setMsg(String msg){
	if (msg.equals("lleno")){
		this.msg ="PARKING LLENO, NO HAY PLAZAS DISPONIBLES";
	}
   }
   public String getMsg(){
		return msg;
   }
   public void setMsg(int planta, int plaza ,String tipoTicket, String fec, String fec2){
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
			"Hora de Entrada: " + fec;
		this.msg = mensaje;
	}
}