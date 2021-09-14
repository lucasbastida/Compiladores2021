# Actividad 2 - Operaciones Aritmético-Lógicas
Esta actividad consiste en realizar en ANTLR las expresiones regulares y reglas gramaticales apropiadas para la detección y verificación de operaciones aritmético-lógicas y la estructura de control while para el lenguaje C reducido. Se dede realizar siguiendo los lineamientos vistos en clases.

Completar el proyecto de la actividad anterior para cumplir con esta consigna.

Se debe entregar un archivo comprimido con el proyecto y consignar en el cuadro de texto la URL del proyecto en GitHub.

## Solucion

> En la tabla siguiente se resume la prioridad y asociatividad (el orden en que se evalúan los operandos) de los operadores de C, que se enumeran por orden de prioridad, de mayor a menor. Cuando varios operadores aparecen juntos, tienen la misma prioridad y se evalúan según su asociatividad.

### Prioridad y asociatividad de los operadores de C

| Simbolo   | Tipo de operacion                   | Asociatividad       |
|-----------|-------------------------------------|---------------------|
| * /       | Multiplicativo (aritmetica)         | izquierda a derecha |
| + -       | Aditivo (aritmetica)                | izquierda a derecha |
| < > <= >= | Relacional (comparacion/relacional) | izquierda a derecha |
| == !=     | Igualdad (comparacion/relacional)   | Izquierda a derecha |
| &&        | AND (logica)                        | Izquierda a derecha |
| \|\|      | OR (logica)                         | Izquierda a derecha |

```java
...

instruccion : declaracion
            | iwhile
            | bloque
            | oal
            ;

...


bloque : LLA instrucciones LLC ;

iwhile : WHILE PA oal PC instruccion ;


oal: oplogica ;

oplogica: prop or;

or: OR oplogica
  |
  ;

prop: opcomp and ;

and: AND opcomp and
   |
   ;



opcomp: comp opigualdad;

opigualdad: ISEQ opcomp
              | ISNEQ opcomp
              |
              ;

comp: oparit oprela;

oprela: ISGREATHAN oparit oprela
              | ISLESSTHAN oparit oprela
              | ISLESSEQ oparit oprela
              | ISGREATEQ oparit oprela
              |
              ;



oparit: term t;

t : SUMA oparit
  | RESTA oparit
  |
  ;

term : factor f
     ;

f : MULT factor f
  | DIV  factor f
  |
  ;

factor : ENTERO
       | ID
       | PA oal PC
       ;

```

# Actividad 1 - Declaracion de variables

Esta actividad consiste en realizar en ANTLR las expresiones regulares y reglas gramaticales apropiadas para la detección y verificación de declaraciones de variables para el lenguaje C reducido. Debe aceptar como válidas las siguientes instrucciones:

int x;
double x = y;
char a, b, c;
int x = 5, y, z = x, w;

Se debe entregar un archivo comprimido con el proyecto y consignar en el cuadro de texto la URL del proyecto en GitHub.

## Solucion

**Archivo a parsear:** src/declaraciones.txt

```
programa : instrucciones EOF ;

instrucciones : instruccion instrucciones
              |
              ;

instruccion : declaracion
            ;

declaracion : tipovar PYC
            ;

tipovar : INT secvar
        | DOUBLE secvar
        | CHAR secvar
        ;

secvar : ID varopt
       | ID '=' ( NATURAL | ID)  varopt
       ;

varopt : COMA secvar
       |
       ;
```

