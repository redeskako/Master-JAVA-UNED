/*
 * Clase donde se evaluan las distintas Opciones enviadas desde la Clase Oca
 * @autor: Daniel Lozano
 * @Fecha: 05/04/14
 * @Version: 3
 */

package es.uned2014Oca;

public class TiposCasos {
	
	private static int puntuacionJugador;// nº casilla del jugador
	private static int numJugador;// numero de jugadores
	private static String str="El Jugador";//Cadena Texto a Utlizar en Literales

	
	
	public TiposCasos(int argPuntuacionJugador, int argNumJugador) // Constructor
	{
		this.puntuacionJugador=argPuntuacionJugador;
		this.numJugador=argNumJugador;
	}
	
	
	
	public static int Casos(int argPuntuacionJugador, int argNumJugador )// Devuelve El Resultado En casillas en Funcion de la Casilla Asignada
	
	{
		puntuacionJugador=argPuntuacionJugador;
		numJugador=argNumJugador;
	
		int puntuacion=0;
		
	switch (puntuacionJugador)// distintas Opciones en funcion de la Casilla que se Cae
	{


		case 5: // Oca tipo 1, (4 Casillas)
		case 14:// Oca tipo 1, (4 Casillas)
		case 23:// Oca tipo 1, (4 Casillas)
		case 32:// Oca tipo 1, (4 Casillas)
		case 41:// Oca tipo 1, (4 Casillas)
		case 50:// Oca tipo 1, (4 Casillas)
		case 59:// Oca tipo 1, (4 Casillas)
			
			puntuacionJugador=puntuacionJugador+4;
			System.out.printf("\n%40s %d: ¡De Oca a Oca y Tiro porque me Toca!\n", str, (numJugador+1));
            puntuacion=Dados.dados();
            puntuacionJugador=puntuacionJugador+puntuacion;
            System.out.printf("%40s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str, (numJugador+1),puntuacion, puntuacionJugador );
            break;
		case 18: //Oca tipo 2, (5 Casillas)
		case 27:// Oca tipo 2, (5 Casillas)
		case 36:// Oca tipo 2, (5 Casillas)
		case 45:// Oca tipo 2, (5 Casillas)
		case 54:// Oca tipo 2, (5 Casillas)
		
			
			 puntuacionJugador=puntuacionJugador+5;
			 
			 System.out.printf("\n%40s %d ¡De Oca a Oca y Tiro porque me Toca!\n",str, (numJugador+1));
             puntuacion=Dados.dados();
             puntuacionJugador=puntuacionJugador+puntuacion;
             System.out.printf("%40s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str, (numJugador+1),puntuacion, puntuacionJugador );
             break;
		
		case 6:// Puente a favor Jugador
			
			 puntuacionJugador=puntuacionJugador+6;
			 
			 System.out.printf("\n%40s %d ¡Bien De Puente a Puente y Tiro porque me Toca!\n",str, (numJugador+1));
             puntuacion=Dados.dados();
             puntuacionJugador=puntuacionJugador+puntuacion;
             System.out.printf("%40s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str, (numJugador+1),puntuacion, puntuacionJugador );
  
             break;
		case 12:// Puente en contra Jugador
			
			 puntuacionJugador=puntuacionJugador-6;
			 
			 System.out.printf("\n%40s %d ¡Vuelve a Casilla 6 De Puente a Puente y Tiro porque me Toca!\n",str, (numJugador+1));
             puntuacion=Dados.dados();
             puntuacionJugador=puntuacionJugador+puntuacion;
             System.out.printf("%40s %d Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str,  (numJugador+1), puntuacion, puntuacionJugador );
             break;
		
		case 26://Dados
			 puntuacionJugador=puntuacionJugador+27;
			 
			 System.out.printf("\n%40s %d ¡Bien De Dado a Dado y Tiro porque me Toca!\n",str, (numJugador+1));
            puntuacion=Dados.dados();
            puntuacionJugador=puntuacionJugador+puntuacion;
            System.out.printf("%40s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str, (numJugador+1),puntuacion, puntuacionJugador );
 
            break;
            
		case 53://Dados
			 puntuacionJugador=puntuacionJugador-27;
			 
			 System.out.printf("\n%40s %d ¡Ohh Retrocedo De Dado a Dado y Tiro porque me Toca!\n",str, (numJugador+1));
           puntuacion=Dados.dados();
           puntuacionJugador=puntuacionJugador+puntuacion;
           System.out.printf("%40s %d: Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str, (numJugador+1),puntuacion, puntuacionJugador );

           break;
            
             
		case 42://Laberinto contra Jugador
			
			puntuacionJugador=puntuacionJugador-12;
			
			System.out.printf("\n%40s %d ¡Laberinto, Vuelve a la Casilla 30!\n",str,(numJugador+1));
			puntuacion=Dados.dados();
            puntuacionJugador=puntuacionJugador+puntuacion;
            //System.out.println(puntuacionJugador+" jugador "+(numJugador+1)+"  la puntuacion en Dados es:  "+puntuacion);
            System.out.printf("%40s %d Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str,  (numJugador+1), puntuacion, puntuacionJugador );
            break;

			
		case 30://Laberinto a favor Jugador
			
			puntuacionJugador=puntuacionJugador+12;
			
			System.out.printf("\n%40s %d ¡Laberinto, Avanza a la Casilla 42!\n", str, (numJugador+1));
			
			break;
		
		case 58: // Calavera        
				puntuacionJugador=0;
				
    			System.out.printf("\n%40s %d ¡Ha caido en la Calavera, empieza desde el Principcio y Tira de Nuevo!\n",str, (numJugador+1));
    			puntuacion=Dados.dados();
                puntuacionJugador=puntuacionJugador+puntuacion;
                System.out.printf("%40s %d Puntuacion Obtenida en Dados %d, Total Acumulado: %d\n\n", str,  (numJugador+1), puntuacion, puntuacionJugador );
                break;

        	
			
	}// fin case
	
	return puntuacionJugador;
		
	}// fin void Casos
		
	public static int PerdidaTurno(int argPuntuacionJugador, int argNumJugador ) // metodo donde se evalua el nº de turnos Pierde el Jugador
	{
		puntuacionJugador=argPuntuacionJugador;
		numJugador=argNumJugador;

			int penalizado=0;
		switch (puntuacionJugador)
		{
			case 52:// Carcel Pierde 3 Turnos
			{
			puntuacionJugador=numJugador;
			penalizado=3;
			System.out.printf("\n%40s %d ¡Ha caido en la Carcel, Pierde 3 Turnos!\n\n",str, (numJugador+1));
			break;
			}
			
			case 19:// Posada Pierde 2 Turnos
			case 31:// Posada Pierde 2 Turnos
			{
			puntuacionJugador=numJugador;
			penalizado=2;
			System.out.printf("\n%40s %d ¡Ha caido en la Casilla %d, Pierde 2 Turnos!\n\n",str, (numJugador+1), puntuacionJugador);
			break;
			}
		
			
	}// fin case
		
		return penalizado; // devuelve el nº de turnos que pierde
		

		}// fin PerdidaTurno
}// fin clase
		
