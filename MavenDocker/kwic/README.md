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

![Representación Kwic](https://raw.githubusercontent.com/redeskako/EjemplosJ2SE/master/MavenDocker/kwic/kwic.PNG)

El `map, será una clase `TreeMap`, y un `Set` será una clase `TreeSet`.
Como vamos a implementar clases de `TreeMap` y `TreeSet`, necesito redefinir:

  * `compareTo`
  * `equals`
  * `hasCode`
  * `toString`

## Comentarios del código fuente

#### contructor Kwic()

Método constructor, se inicializa la estructura

```
public KWIC(){
         noClaves = new TreeSet();
         glosario = new TreeMap();
    }
```

#### palabrasNoSignificativas(String)

Esta parte del codigo tratará exclusivamente las **noClaves**. Que metan una estructura de String y yo computarla, recibiendo el `String` noclaves.
  * Uso `StringTokenizer`para coger cada palabra no significativa.
  * Tomaré un token hasta que encuentre una `,` `:` `;`
  * Mientras halla elementos,lo introduzco en mi conjunto.

```
public void palabrasNoSignificativas(String pns){
        StringTokenizer st = new StringTokenizer(pns," ,:;");
        while (st.hasMoreTokens()){
                noClaves.add(new TituloKWIC(st.nextToken()));
        }

    }
```

#### generarFrases(String)

  * Aquí trataremos la parte de las palabras significativas. Ej: Si olvido de todas las frases, solo cojo una:
    `String str = "La silla está rota en el balcón"`
  * Tengo que coger una palabra significativa: `esta`  --> entra como clave, palabra del índice.Tengo que integrarla en el indice de este mapa, puede ser que exista ese indice o no exista. A la clave Hay que ponerle un envoltorio de `TituloKWIC` , lo que hará es que: `esta --> ESTA` 

  * Método que recibe una frase y se computa al kwic:

     1. primer paso --> para extraer la palabra de la frase.
     2. segundo paso: Detectar si esa palabra es indice o no.
     3. tercer paso: Sólamente para los indice: incluirlo en el kwic.

Utilizo el `StringTokenizer` para coger las palabras significativas que me valdrán como índice para mi estructura `map`

  * Mientras halla elementos, cojo una palabra y la envuelvo en `TituloKWIC`.
  * Si no es una palabra no significativa, significa que voy bien, lo que tengo es una palabra válida en este caso. En este caso, me voy a un método privado que ahora comentaremos.

```
public void generarFrases(String frase){
        StringTokenizer st = new StringTokenizer(frase);

        while (st.hasMoreTokens()){
            TituloKWIC palabra = (new TituloKWIC(st.nextToken()));
            if (!noClaves.contains(palabra)){
                //método privado
                frases(palabra,frase);
            }

        }
    }
```

Aqui tengo mi método private, que se va a llamar frases, lo único que ahora se recibe un `TituloKWIC` y un `String` que será la frase a introducir.
   1. Creo un conjunto para el caso que ese indice no esté en mi estructura.
   2. Si mi estructura Map contiene ese indice, devuelveme el conjunto de frases de ese índice.
   3. Tanto si está el índice en la estructura como si no, tengo que añadir la frase al conjunto y luego relacionar ese índice con mi conjunto actualizado.
   4. Lo saco del `if`, y no me hace falta poner un `else`.
   5. Añado `replace` para que me sustituya en la frase el indice por `...`. Este método está en `codigoTituloKWIC`.

```
private void frases(TituloKWIC indice,String frase){
        Set<String> s = new TreeSet<String>();
      
        if (glosario.containsKey(indice)){
            s = glosario.get(indice);
        }
        s.add(indice.replace(frase));
        glosario.put(indice,s);

    }

```

#### Método toString():String

Utilizaremos dos métodos privados:i
  *`imprimirNoClaves()` --> para imprimir por pantalla el conjunto de claves.
  *`imprimirGlosario()` --> para imprimir por pantalla la estructura `map`.
    * `clave (índice)` y valor (conjunto de frases relacionadas y con el índice sustituido por `...`)

```
public String toString(){
        String cadFinal = "";
        cadFinal += imprimirNoClaves();
        cadFinal += imprimirGlosario();
        return cadFinal;
    }
```

  * Método privado que me imprime el conjunto de las palabras no significativas. Este método será utilizado por `toString()`.
	 * Utilizo el iterador de conjuntos para recorrerlo e imprimirlo.
	 * Mientras halla elementos en el conjunto, imprime elementos
	 * Nos devuelve los elementos imprimidos.

```
private String imprimirNoClaves(){
        String cadNoClaves = "N O    C L A V E S: "; //inicializo la cadena

        Iterator<TituloKWIC> itNoClaves = this.noClaves.iterator();
        while (itNoClaves.hasNext()){
            cadNoClaves += itNoClaves.next().toString() + ", ";
        }
        return cadNoClaves;
    }
```

  * Utilizo un método privado que me imprime la estructura `Map<Indice,conjunto de frases>`. Este método será utilizado por `toString()`.
     * Como `map` no tiene iterator tengo que cogerlo de `map.Entry`.
     * Mientras halla elementos en mi estructura, meto el indice
     * Por cada indice llamo a un método que me recorrerá el conjunto de cada índice
     * El método me devolverá el indice con su conjunto.

```
private String imprimirGlosario(){
        String cadGlosario = "\n"+"\n"+"         G L O S A R I O    "+"\n";
      
        Iterator<Map.Entry<TituloKWIC,Set<String>>>itGlosario = this.glosario.entrySet().iterator();
        while (itGlosario.hasNext()){
            Map.Entry<TituloKWIC,Set<String>> me = itGlosario.next();
            cadGlosario +="\n"+ me.getKey()+"\n"+"\n";
            cadGlosario += imprimirGlosario(me.getValue());
        }
        return cadGlosario;
    }
```

Utilizo un método que me imprime el conjunto de frases. Este método será utilizado por
el método `imprimirGlosario()`.Hago lo mismo que hice con el conjunto formado por palabras no significativas.
    * Inicializo la cadena.
    * Creo el iterador.
    * Mientras halla elementos, imprimo elementos.
    * Devuelvo los elementos del conjunto.

```
private String imprimirGlosario(Set<String> s){
        
        String cjFrases = "";
        Iterator itCjFrases = s.iterator();
        while (itCjFrases.hasNext()){
            cjFrases += "\t" + itCjFrases.next()+ "\n";
        }
        return cjFrases;
    }
```

#### KwicException
  * Clase de contorl de Errores personalizada

#### TituloKwic
  * Código envoltorio de `String`
  
#### driverKwic
   * Código del `main(String [])`.
 