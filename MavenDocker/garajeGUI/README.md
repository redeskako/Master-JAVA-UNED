# Ejemplo Garaje

## Diseño

Las clases principales de la aplicación se distribuyen, siguiendo un patrón MVC, de la siguiente manera:

  * Driver: dirige la aplicación e interactúa con el resto de clases principales.
  * capaDatos.ConexionBD: encapsula las operaciones típicas sobre una BD
  * capaGestion.ControladorPlazas?: controla lo relativo a la ocupación y liberación de plazas en el garaje.
  * capaVista.GarajeGUI: se encarga de la presentación visual de la aplicación.

	![Imagen Clases Diseño](diagrama_clases.png?raw=True)