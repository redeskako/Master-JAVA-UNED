package es.uned.master.java.garaje ;

import java.util.*;

import es.uned.master.java.garaje.configuracion.GestorRecursos;

//clase Garaje
public class Garaje {

	// Atributos
	
	private int plantas; 	// Propiedades nuevas para dotar de dinamismo a la clase.
	private int plazas;	
	private GestorRecursos recursos;
	public enum Color {

		ROJO(4), AZUL(3), AMARILLO(2), VERDE(1);

		private int valor;

		private Color(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return valor;
		}
	}

	public Color garaje[][] = null;
	public String garajeLleno[][] = null;

	public Color random_Color() {
		Random r = new Random();
		int n = r.nextInt(4);
		Color[] values = Color.values();  //Obtenemos un array de Color 
										
		Color colorVal = values[n];
		return colorVal;
	}
	//Constructor, actualizamos para que acepte parametros desde Driver.
	public Garaje(int plantas, int plazas, GestorRecursos recursos) {
		this.plantas= plantas;//Asignamos a propiedades el valor de los parámetros.
		this.plazas= plazas;
		this.recursos=recursos;
		 garaje  = new Color[this.plantas][this.plazas];
		   garajeLleno  = new String[this.plantas][this.plazas];
	
		//inicializo el garaje a [color_plaza,vacio]; garaje vacio
		for (int i = 0; i < this.plantas; i++) {
			for (int j = 0; j < this.plazas; j++) {
				this.garaje[i][j] = random_Color();
				
				this.garajeLleno[i][j] = "[" + recursos.muestraMensaje(this.garaje[i][j].toString()) + " - " + recursos.muestraMensaje("msg14") + "]";
			}
			
			// Comprobamos que al menos cada color aparezca una vez
			boolean rojo = false, azul = false, amarillo = false, verde = false;
			for (int x = 0; x < this.plazas; x++) {
				switch (this.garaje[i][x]) {
				case ROJO:
					rojo = true;
					break;
				case AZUL:
					azul = true;
					break;
				case AMARILLO:
					amarillo = true;
					break;
				case VERDE:
					verde = true;
					break;
				}
			}
			
			if ((rojo == false) || (azul == false) || (amarillo == false)
					|| (verde == false)) {
				/* decrementamos i para volver a llenar la planta hasta que cada
				 color aparezca una vez por lo menos*/
				i--;
			}
		}
	}
	
	/***************************************************************************************************
	 *Funcion entradaCoches:
	 *
	 * @param aleatorio: numero de coches al azar entre un rango entre 0 y 100 a aparcar en el garaje
	 * @return sobra: numero de coches que no han cabido en el garaje si aleatorio es mayor que el valor dinámico de las plazas del garaje obtenido de multiplicar el número de las plazas por el número de plantas. 
	 */
	public int entradaCoches(int aleatorio) {
		int nSotanosOcupados = aleatorio / this.plazas;
		int nCochesResto = aleatorio % this.plazas;
		int sobra = 0;

		if (aleatorio >= (this.plantas*this.plazas)) {
			/*Si el garaje esta lleno rellenamos todas las plazas con el color
			de la plaza y coche*/
			for (int i = 0; i < this.plantas; i++) {
				for (int j = 0; j < this.plazas; j++) {
					this.garajeLleno[i][j] = "[" + recursos.muestraMensaje(this.garaje[i][j].toString())
							+ " " + recursos.muestraMensaje("msg15") + "]";
				}
			}
			sobra = aleatorio - (this.plantas*this.plazas);
			
		} else {
			// Rellenamos los sotanos que est?n totalmente ocupados
			for (int z = 0; z < nSotanosOcupados; z++) {
				for (int x = 0; x < this.plazas; x++) {
					this.garajeLleno[z][x] = "[" + recursos.muestraMensaje(this.garaje[z][x].toString())
							+ " - " + recursos.muestraMensaje("msg15") + "]";
				}
			}

			/* Colocamos el resto de coches que nos quedan atendiendo al peso de
			 las plazas*/
			while (nCochesResto != 0) {
				int max = 0;
				int j = 0;
				for (int i = 0; i < this.plazas; i++) {
					if ((this.garaje[nSotanosOcupados][i].getValor() > max)
							&& (this.garajeLleno[nSotanosOcupados][i]
									.contains(recursos.muestraMensaje("msg14")))) {
						max = this.garaje[nSotanosOcupados][i].getValor();
						j = i;
					}
				}
				this.garajeLleno[nSotanosOcupados][j] = "["
						+ recursos.muestraMensaje(this.garaje[nSotanosOcupados][j].toString()) + " - " + recursos.muestraMensaje("msg15") + "]";
				nCochesResto--;
			}
		}

		return sobra;
	}
	/***************************************************************************************************
	 * Funcion pintarGaraje:
	 * 
	 * @param leyenda: variable booleana, si es true entonces el garaje esta vacio
	 *  en caso contrario esta lleno
	 */
	public void pintarGaraje(boolean leyenda) {

		if (leyenda) {

			System.out.println(recursos.muestraMensaje("msg11"));
		}

		for (int i = 0; i < this.plantas; i++) {
			//Pintamos la comluna con el nombre de las plantas
			if (i == 0) {
				System.out.print(recursos.muestraMensaje("msg12"));
			} else {
				System.out.print(recursos.muestraMensaje("msg13") + (i - 1) + "   ");
			}
			
			//Pintamos las plazas
			for (int j = 0; j < this.plazas; j++) {
				int longitud = this.garajeLleno[i][j].length();
				for (int x = longitud; x < 20; x++) {
					this.garajeLleno[i][j] = this.garajeLleno[i][j] + " ";
				}
				if (j == (this.plazas-1)) {
					System.out.print(this.garajeLleno[i][j] + "\n");
				} else {
					System.out.print(this.garajeLleno[i][j]);
				}
				if ((i == (this.plantas-1)) && (j == (this.plazas-1))) {
					System.out.println("\n");
				}
			}
		}

	}
}   