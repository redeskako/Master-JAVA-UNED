/**
 * Clase Controlador de Plazas
 * Version 1
 * @author jrequeijo2, Jose lopez
 */


package es.uned.master.java.grajeGUI.capaGestion;

public class ControladorPlazas {
	private String[] cabecera;  //Muestra la cabecera con el numero de plaza
	private Object[][] plazas;  //Contiene las plazas de aparcamineto
	public static int plazasOcupadas; //Indica el numero de plazas ocupadas
	
	public ControladorPlazas() {
		plazasOcupadas=0;
        this.cabecera = new String[15];
        this.plazas = new Object[7][15];
        
        // Se incializa la cabecera con el numero de plaza
		for (int i=0;i<15;i++){
			if (i==0){
				this.cabecera[i]="";	
			}else {
				this.cabecera[i]= "P" + i;
			}
		}
        //Se inicializa el array con las plazas de aparcamiento
		//La primera columna contine el numero de planta
		
        for (int i =0; i<7;i++ ){
        	for (int j=0;j<10;j++){
        		if ((i==0) && (j==0)){
        			this.plazas[i][j]="SOTANO";
        		} else if ((i>0) && (j==0)){
        			this.plazas[i][j]= "PLANTA " + (i - 1);
        		} else if((i>0) && (j>0)){
        			this.plazas[i][j]="";
        		}        		
        	}
        }	

	}
	
	
	public String[] getCabecera() {
		return cabecera;
	}


	public void setCabecera(String[] cabecera) {
		this.cabecera = cabecera;
	}


	public Object[][] getPlazas() {
		return plazas;
	}


	public void setPlazas(Object[][] plazas) {
		this.plazas = plazas;
	}

    /**
     * * 
     * @param planta
     * @param plaza
     * @return plaza liberada en formato: planta,plaza
     * Decrementa el numero de plazas ocupadas en una unidad
     */
  
	public String liberarPlaza (int planta, int plaza){
 		String plazaLibre="NULL";  	    
 		if( this.plazas[planta][plaza].equals("X")){
 	 		this.plazas[planta][plaza]="";
 	 		plazaLibre=planta +","+ plaza;
 	 		liberarPlaza();
 	 	} 		
 		return plazaLibre;
    }
	/**
	 * 
	 * Devuelve la plaza asignada en formato: planta,plaza
	 * Incrementa en una unidad el numero de plazas ocupadas
	 */
	 public String buscarPlaza(){
		 String plaza="";
		 int i=0;
		 int j=1;
		 boolean plazaAsignada = false;
		  while (!plazaAsignada ) {
			  if( this.plazas[i][j].equals("")){
			      this.plazas[i][j]="X";
					plaza=i + "," + j;
					asignarPlaza();
					plazaAsignada = true;					
		     }else{
		    	 if (j==14){
		    		 j=1;
		    		 i++;
		    	 }else{
		    		 j++;
		    	 }
		     }			
		 }		 
		 return plaza;
	 }		
     /**
      * Incrementa en una unidad el numero de plazas ocupadas
      */
	 public static void asignarPlaza(){
    	 if (plazasOcupadas < 98){
    		 plazasOcupadas++; 
    	 }    	 
     } 
	 /**
	  * Decrementa el numero de plazas ocupadas en una unidad
	  */
     public static void liberarPlaza(){
    	 if (plazasOcupadas >0){
    		 plazasOcupadas--; 
    	 }    	 
     } 
     /**
      * 
      * Devuelve el numero de plazas ocupadas
      */
     public static int plazasOcupadas() {
         return plazasOcupadas;
     }
	
	
}
