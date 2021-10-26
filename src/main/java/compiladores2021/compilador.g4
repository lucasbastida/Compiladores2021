grammar compilador;

@header{ 
package compiladores2021;
}

programa : instrucciones EOF ;

instrucciones : instruccion instrucciones
              |
              ;

instruccion : declaracionVar ';'
            | iwhile
            | bloque
            | asignacion ';'
            | forloop
            | ifelse
            | declaracionFn
            | expr ';'
            ;

declaracionFn: tipovar ID '(' params ')' bloque;
params  : param
        | param ',' params
        |
        ;

param : tipovar ID;

declaracionVar : tipovar secvar ;

tipovar : INT | DOUBLE | CHAR | VOID;

secvar : ID varopt            #Decl
       | ID '=' expr varopt   #Assign
       ;

varopt : ',' secvar
       |
       ;

asignacion : ID '=' expr ;


iwhile : 'while' '(' expr ')' instruccion ;
bloque : '{' instrucciones '}' ;
forloop : 'for' '(' (declaracionVar|asignacion) ';' expr ';' asignacion  ')' instruccion ;
ifelse
  : 'if' '(' expr ')' instruccion
  | 'if' '(' expr ')' instruccion 'else' instruccion
  ;



expr: oal;
/* OPERACIONES ARITMETICO LOGICAS */
oal: oplogica ;

oplogica: prop or;

or: '||' oplogica
  |
  ;

prop: opcomp and ;

and: '&&' opcomp and
   |
   ;

opcomp: comp opigualdad;

opigualdad
  : '==' opcomp
  | '!=' opcomp
  |
  ;

comp: oparit oprela;

oprela
  : '>' oparit oprela
  | '<' oparit oprela
  | '<=' oparit oprela
  | '>=' oparit oprela
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

factor : ENTERO           #Int
       | ID               #Var
       | ID '(' args ')'  #fnCall
       | '(' oal ')'      #Paren
       ;


args: expr
    | expr ',' args
    |
    ;


SUMA   : '+' ;
RESTA  : '-' ;
MULT   : '*' ;
DIV    : '/' ;

INT: 'int';
DOUBLE: 'double' ;
CHAR: 'char' ;
VOID: 'void';

ENTERO : DIGITO+ ;

fragment DIGITO : [0-9] ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

WS : [ \t\n\r] -> skip;
COMMENT: '//' .*? ('\n'|EOF) -> skip;
OTRO : . ;