# Ejemplo de uso de lambda-expression

Este ejemplo demuestra como funciona el uso de lambda expression en java.

Uno de los puntos fuertes en la implementacion de java 1.8 o posterior es la incorporación de la programación funcional a través de funciones lambda.

## Zero Parametros

Si el método que quieres ajustar a la expression lambda no tiene parámetros puede representar una función lambda como la siguiente:

```
() -> System.out.println("Lambda sin parámetros");
```
## Un parámetro

Si el método a incluir en una lambda expresion requiere de un parámetro. Un ejemplo puede ser como:

```
(var) -> System.out.println("Lambda con un parámetro: " + var);
```

La variable entre paréntesis puede utilizarse en el cuerpo de la lambda.

Tambíen puede quitarse el paréntesis si sólo tiene un parámetro:

```
var -> System.out.println("Lambda con un parámetro: " + var);
```

## Varios parámetros

Si se contempla múltiples parámetros, se incluyen dentro del paréntesis separados por comas.

```
(p1, p2) -> System.out.println("Multiple parameters: " + p1 + ", " + p2);
```

## Tipado en los parámetros

A veces es importante determinar a una clase de los parámetros lambda:

```
(Libro libro) -> System.out.println("El libro es: " + libro.getNombre());
```

## JAVA 11 estimación parámetros var

Desde la versión 11 puede usar la palabra reservada `var` como un formato de From Java 11 you can use the var keyword as parameter type. The var keyword was introduced in Java 10 as local variable type inference. From Java 11 var can also be used for lambda parameter types. Here is an example of using the Java var keyword as parameter types in a lambda expression:

``` 
Function<String, String> aMinuscula = (var entrada) -> entrada.toLowerCase();
```

