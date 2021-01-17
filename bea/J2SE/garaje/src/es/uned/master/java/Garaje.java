package es.uned.master.java;

import java.util.*;

//clase Garaje
public class Garaje {

	// Atributos
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

	public Color garaje[][] = new Color[5][13];
	public String garajeLleno[][] = new String[5][13];

	public Color random_Color() {
		Random r = new Random();
		int n = r.nextInt(4);
		Color[] values = Color.values();  //Obtenemos un array de Color 
										
		Color colorVal = values[n];
		return colorVal;
	}
	//Constructor
	public Garaje() {
	
		//inicializo el garaje a [color_plaza,vacio]; garaje vacio
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 13; j++) {
				this.garaje[i][j] = random_Color();
				this.garajeLleno[i][j] = "[" + this.garaje[i][j] + " - vacio]";
			}
			
			// Comprobamos que al menos cada color aparezca una vez
			boolean rojo = false, azul = false, amarillo = false, verde = false;
			for (int x = 0; x < 13; x++) {
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
	 * @return sobra: numero de coches que no han cabido en el garaje si aleatorio es mayor que 65
	 */
	public int entradaCoches(int aleatorio) {
		int nSotanosOcupados = aleatorio / 13;
		int nCochesResto = aleatorio % 13;
		int sobra = 0;

		if (aleatorio >= 65) {
			/*Si el garaje esta lleno rellenamos todas las plazas con el color
			de la plaza y coche*/
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 13; j++) {
					this.	garajeLleno[i][j] = "[" + this.garaje[i][j]
							+ " - coche]";
				}
			}
			sobra = aleatorio - 65;
			
		} else {
			// Rellenamos los sotanos que estén totalmente ocupados
			for (int z = 0; z < nSotanosOcupados; z++) {
				for (int x = 0; x < 13; x++) {
					this.garajeLleno[z][x] = "[" + this.garaje[z][x]
							+ " - coche]";
				}
			}

			/* Colocamos el resto de coches que nos quedan atendiendo al peso de
			 las plazas*/
			while (nCochesResto != 0) {
				int max = 0;
				int j = 0;
				for (int i = 0; i < 13; i++) {
					if ((this.garaje[nSotanosOcupados][i].getValor() > max)
							&& (this.garajeLleno[nSotanosOcupados][i]
									.contains("vacio"))) {
						max = this.garaje[nSotanosOcupados][i].getValor();
						j = i;
					}
				}
				this.garajeLleno[nSotanosOcupados][j] = "["
						+ this.garaje[nSotanosOcupados][j] + " - coche]";
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

			System.out.println("Garaje vacío:\n");
		}

		for (int i = 0; i < 5; i++) {
			//Pintamos la comluna con el nombre de las plantas
			if (i == 0) {
				System.out.print("Planta Sot ");
			} else {
				System.out.print("Planta " + (i - 1) + "   ");
			}
			
			//Pintamos las plazas
			for (int j = 0; j < 13; j++) {
				int longitud = this.garajeLleno[i][j].length();
				for (int x = longitud; x < 20; x++) {
					this.garajeLleno[i][j] = this.garajeLleno[i][j] + " ";
				}
				if (j == 12) {
					System.out.print(this.garajeLleno[i][j] + "\n");
				} else {
					System.out.print(this.garajeLleno[i][j]);
				}
				if ((i == 4) && (j == 12)) {
					System.out.println("\n");
				}
			}
		}

	}
}
