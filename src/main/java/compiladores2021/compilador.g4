grammar compilador;

@header{ 
package compiladores2021;
}

fragment DIGITO : [0-9] ;

PYC    : ';';
COMA   : ',' ;
INT    : 'int' ;
DOUBLE : 'double' ;
CHAR   : 'char' ;

NATURAL : DIGITO+ ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

WS : [ \t\n\r] -> skip;

OTRO : . ;

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

