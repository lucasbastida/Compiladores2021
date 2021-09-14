grammar compilador;

@header{ 
package compiladores2021;
}

fragment DIGITO : [0-9] ;

PA : '(';
PC : ')';
LLA : '{' ;
LLC : '}' ;
PYC    : ';';
COMA   : ',' ;
OR    : '||' ;
AND   : '&&';
ISEQ: '==';
ISNEQ: '!=';

ISLESSTHAN: '<';
ISGREATHAN: '>';
ISLESSEQ: '<=';
ISGREATEQ: '>=';


INT    : 'int' ;
DOUBLE : 'double' ;
CHAR   : 'char' ;

SUMA   : '+' ;
RESTA  : '-' ;
MULT   : '*' ;
DIV    : '/' ;

WHILE  : 'while' ;


ENTERO : DIGITO+ ;
// NATURAL : DIGITO+ ;
// ENTERO : '-'? NATURAL ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

WS : [ \t\n\r] -> skip;

OTRO : . ;

programa : instrucciones EOF ;

instrucciones : instruccion instrucciones
              |
              ;

instruccion : declaracion
            | iwhile
            | bloque
            | oal
            ;

declaracion : tipovar PYC
            ;

tipovar : INT secvar
        | DOUBLE secvar
        | CHAR secvar
        ;

secvar : ID varopt
       | ID '=' ( ENTERO | ID)  varopt
       ;

varopt : COMA secvar
       |
       ;

/* BLOQUE INSTRUCCION */
bloque : LLA instrucciones LLC ;

/* INSTRUCCION WHILE */
iwhile : WHILE PA oal PC instruccion ;


/* OPERACIONES ARITMETICO LOGICAS */
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
