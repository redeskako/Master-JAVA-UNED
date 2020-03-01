# Ejemplo Garaje

## Enunciado

Tenemos un garaje de alquiler de plazas.

 * El garaje tiene 5 plantas:
   * Planta Sótano
   * Planta 0
   * Planta 1
   * Planta 2
   * Planta 3

Se busca rellenar el garaje de forma equilibrada y repartida por plantas, para ello:

  * Se asignan colores a las plazas, cada color tiene un precio que favorece la disposición del coche:
    * (ROJO, AZUL, AMARILLO, VERDE)
    * peso(ROJO)>peso(AZUL)>peso(AMARILLO)>peso(VERDE)

 * Hay un total de 65 plazas (13 por planta) y en cada planta debe tener todos los colores.
 * La forma de cubrir el garaje es : rojo, azul, amarillo y verde, en este orden.

 * Puede probarse con este Driver que contiene el método main()

```
import java.util.*;
public class Driver{
    public static void main(String [] arg){
        //Inicializa un garaje de asignando colores de  forma aleatoria,
        // Debe tener cada planta todos lo colores.
        Garaje g = new Garaje();
        //Calculo el número de vehículos a entrar de forma aleatoria..
        Random random= new Random();
        int aleatorio= random.netInt(100);
        //Si no caben más devuelve el valor de los coches que no ha podido aparcar.
        //Si devuelve un valor negativo (-1) es que el random ha sido 0= ningún coche
        int sobra= g.entradaVehiculos(aleatorio);
        if (aleatorio>0){
            //Entra más de un coche
            System.out.println("Resultado de vehículos aparcados:");
            System.out.println(g);
            if (sobra!=0){
                //Han sobrado coches.
                System.out.println("No han cabido "+sobra+" coches");
            }
        }else{
            System.out.println("No hay coches");
        }
    }
```
 * Se propone un diseño basado en interfaz gráfica Swing y Awt.
 * Se propone guardar datos a una base de datos SQLite (garaje.db) con los siguientes campos:

```
	(fichero: garaje.db)
CREATE TABLE registro (
					 	"id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
					 	"planta" INT NOT NULL,
						"plaza" INT NOT NULL,
						"fecha_entrada" TEXT NOT NULL,
						"fecha_salida" TEXT
						)
```

## Diseño

Las clases principales de la aplicación se distribuyen, siguiendo un patrón MVC, de la siguiente manera:

  * Driver: dirige la aplicación e interactúa con el resto de clases principales.
  * capaDatos.ConexionBD: encapsula las operaciones típicas sobre una BD
  * capaGestion.ControladorPlazas?: controla lo relativo a la ocupación y liberación de plazas en el garaje.
  * capaVista.GarajeGUI: se encarga de la presentación visual de la aplicación.

	![Imagen Clases Diseño](diagrama_clases.png?raw=True)

[![Video del funcionamiento](https://img.youtube.com/vi/pItPfo5XpUE/0.jpg)](https://www.youtube.com/watch?v=pItPfo5XpUE)
