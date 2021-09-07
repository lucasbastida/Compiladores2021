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

