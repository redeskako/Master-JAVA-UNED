/*
 * Clase  donde Se Desarrolla el Juego de la Oca y se llaman al Resto de Clases
 * @autor: Daniel Lozano
 * @Fecha: 05/04/14
 * @Version: 3
 * <docente> ESTUPENDO!<DOCENTE>
 */


package es.uned2014Oca;
public class Oca {

      private final static int NUMCASILLAS=63; //Casillas del tablero Constante
      private int numJugadores; // nº de jugadores
      private String str="El jugador: "; //Cadena de Texto apara añadirla a los printf necesarios

      /*
       * <docente> falta documentar este procedimiento
       * Que son los argumentos? aunque pones getter ha de ir en la cabecera como este comentario
       */
      public void numJugadores( int argNumJugadores) // getter
      {
            this.numJugadores=argNumJugadores;
      }
      /*
       * documentar en la cabecera
       * la función es densa, no has modularizado además de que no lo incluyes como propiedades de la clase, 
       * defines las estructura como variables del método?, aunque funciona
       * tienes la visión de la programación tradicional y eso ha de cambiar.
       * debes estimar que jugador, perdidaTurno, jugador penalizado sean propiedades de una clase.
       * usas tipos básicos!, para que tienes Collection? y toda la artillería de las API de JAVA?
       * Bien documentada cada apartado pero NO MODULARIZADO
       */
      public void jugar() 
      
      {
            int[] jugador=new int[numJugadores+1]; /* array que obtendra el n� de jugadores, se suma uno para luego
            * para luego poder igualar la variable j del for al array ya que como sabemos el array comienza en 0*/
            int[] perdidaTurno=new int[numJugadores]; // array para saber cuantos turnos pierde y lleva perdidos el jugador
            //for Cargar Tablero
            int []jugadorPenalizado=new int[numJugadores+1]; // identificacion de jugador con Perdida de Turnos
   
            /* <docente> Pedazo de for, no crees mejor que puedas modularizar elementos internos? hay que hacerlo más simples <docente> */

            for (int j = 0; j < jugador.length; j++) {
            	
            	/*Veririfica que el jugador Actual no coincide con el Jugador Penalizado
            	 * y que tenga un n� de perdidas de turnos todav�a pendientes.
            	 * En caso contrario que son todos los no penalizados o los que ya han sido penalizados
            	 * dos veces se restauran a no Penalizados(=0). 
            	 * Fantástico pero en un método private mejor!.
            	 */
            	
            	int puntuacion=0; // Inicalizacion varibale recoje los Puntos de los Dados
            	
            	if (j==jugadorPenalizado[j]&& perdidaTurno[j]!=0) // identifica si el jugador es penalizado y si le queda turnos por pasar
            		
            		{
            		if (perdidaTurno[j]<3 && perdidaTurno[j]>0)
            			{
            			
            			perdidaTurno[j]--;
            			System.out.printf("\n%40s %d Pierde 1 Turno\n\n", str,(j+1), (perdidaTurno[j]));
            			continue;
            			}// fin if secundario
            		
            		else // en caso de que ya haya gastado todos los Turnos se inicializa el contador a Cero para indicar que el jugador vuelve a jugar
            			
            			perdidaTurno[j]=0;
            		
            		}// fin if principal
            			
            	puntuacion=Dados.dados(); // lanzamiento aleatorio de datos y recogida en variable puntuacion
                jugador[j]=jugador[j]+puntuacion; // sumamos a los puntos que teniamos la nueva puntuacion sacada en los dados
               
                // Empezar a tirar desde el primer jugador
                 if (j==jugador.length-1) // le restamos 1 para facilitar kla igualda del ultimo jugador con el array
                 {
                     j=0; //se inicializa j para que comience de nuevos los turnos de tirada
                     puntuacion=Dados.dados();
                     jugador[j]=jugador[j]+puntuacion;
                 }// fin if
               
                  //Si me paso de la casilla 63 

                		 	 
                 
                 if (jugador[j]>NUMCASILLAS)// Si nos hemos pasado de la ultima casilla 63 se Retrocede
                    jugador[j]=NUMCASILLAS-(jugador[j]-NUMCASILLAS);
                 

                 if (jugador[j]==NUMCASILLAS) // Si llego exactamente a la casilla 63 gano y se finaliza el juego saliendo de bucle for
                 {
                    System.out.printf("\nEl Ganador es el Jugador %d \nPuntuacion: %d \n",(j+1),  jugador[j]);
                    break;
                 }// fin if
				
				System.out.printf("%s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n", str, (j+1),puntuacion, jugador[j] );
				/* Mostramos la Puntuacion en Pantalla */
				
	            if (jugador[j]==52 || jugador[j]==31) // Si hemos caido en Casilla Perdida de Turnos identificamos el jugador y el n� de Turnos
	            	{
	            	jugadorPenalizado[j]=j;
	            	perdidaTurno[j]=TiposCasos.PerdidaTurno(jugador[j], j);// se Envia a Metodo casos pa saber cuantos turnos se pierde
	            	}//fin if
	            else
	            	jugador[j]=TiposCasos.Casos(jugador[j], j); // En caso Contrario a Perdida de Turnos se Evalua con el resto de Opciones
	            
	            	

	      }// fin for jugadores
	        		                  

      }// fin jugar
      
}// fin clase

