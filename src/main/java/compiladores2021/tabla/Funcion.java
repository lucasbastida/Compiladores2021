package compiladores2021.tabla;

import java.util.List;

public class Funcion extends Id{
    
    private List<TipoDato> args;

    public List<TipoDato> getArgs() {
        return args;
    }

    public void setArgs(List<TipoDato> args) {
        this.args = args;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", args="+args;
    }
}
