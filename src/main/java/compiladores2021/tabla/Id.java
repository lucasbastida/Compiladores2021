package compiladores2021.tabla;

import compiladores2021.compiladorParser;

public abstract class Id {

    public static enum TipoDato {
        VOID, INT, DOUBLE, CHAR, INVALID
    }

    public static TipoDato getTipoDato(int tokenType) {
        switch (tokenType) {
        case compiladorParser.INT:
            return Id.TipoDato.INT;
        case compiladorParser.DOUBLE:
            return Id.TipoDato.DOUBLE;
        case compiladorParser.CHAR:
            return Id.TipoDato.CHAR;
        case compiladorParser.VOID:
            return Id.TipoDato.VOID;
        }
        return TipoDato.INVALID;
    }

    private String name;
    private TipoDato tipo;
    private boolean inicializado;
    private boolean usado;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoDato getTipo() {
        return tipo;
    }

    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @Override
    public String toString() {
        return getTipo() + " " + getName() + " , init=" + isInicializado() + " , usado=" + isUsado();
    }

}
