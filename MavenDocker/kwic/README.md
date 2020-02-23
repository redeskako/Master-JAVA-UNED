# KWIC

## Enunciado Kwic

El objetivo de esta práctica es realizar un glosario de palabras atendiendo a su aparición en un conjunto de frases (KeyWord? In Context, KWIC), desechando aquéllas que no se consideren significativas.

Para ello, contaremos con:
  *  Un array de String --> donde tendremos una relación de frases, a partir de las cuales, deberemos obtener el correspondiente índice.
  * Un String --> representa las palabras no significativas(y que, por lo tanto, no aparecerán en el listado KWIC)

Un ejemplo de String de palabras no significativas podría contener la siguiente línea:

```
el la los las un una unos unas y o a ante bajo cabe con contra de desde en entre hacia hasta para por según sin sobre tras si no
```

El siguiente listado de títulos de películas podría servir como ejemplo de contenido de un array de String con las frases (frase por línea) a partir de las cuales construir un índice KWIC:

```
El color del dinero
Color púrpura
Misión: imposible
La misión
La rosa púrpura del Cairo
El dinero llama al dinero
La rosa del azafrán
El nombre de la rosa
Toma el dinero y corre
```

El índice que se desea generar debe tener el siguiente aspecto:

**AZAFRÁN**

  La rosa del ...

**CAIRO**

  La rosa púrpura del ...

**COLOR**

  ... púrpura

  El ... del dinero

**DINERO**

  El color del ...

  El ... llama al ...

  Toma el ... y corre

**IMPOSIBLE**

  Misión: ...

**MISIÓN**

  La ...

  ...: imposible

**NOMBRE**

  El ... de la rosa

**PÚRPURA**

  Color ...

  La rosa ... del Cairo

**ROSA**

  El nombre de la ...

  La ... del azafrán

  La ... púrpura del Cairo

Como vemos, el índice está ordenado por palabras significativas y, por cada una de ellas, aparecen todas las frases que la contienen (ordenadas alfabéticamente), con las apariciones de la palabra sustituidas por "...".

Visto lo anterior, se pide:

  * a) Definir una clase TituloKWIC que dé envoltura a una frase o título (de tipo String), y que permita ordenar y comparar frases independientemente de si éstas contienen caracteres en minúsculas o mayúsculas, así como un método para producir una cadena, a partir del título, con las apariciones de una palabra sustituidas por “...”.
  *  Definir una clase KWIC que incluya los métodos necesarios para:
     * Leer (y almacenar) la información de las palabras no significativas,
     * Generar la estructura del índice a partir de un array de String (p.e. títulos de películas) teniendo en cuenta las palabras no significativas leídas previamente.

Para resolver esta práctica vamos a realizar una primera división del problema:
Tendremos las siguientes clases:

  * TituloKWIC, la cual me permitirá cadena de caracteres sin distinguir entre mayúsculas y minúsculas.
  * KWIC, va a ser :
    * Un Map<TituloKWIC,Set<String> glosario
    * Set<TituloKWIC> noClaves
    es una estructura para guardar las no claves.
![Representación Kwic] (https://github.com/redeskako/EjemplosJ2SE/blob/master/MavenDocker/kwic/kwic.png)

