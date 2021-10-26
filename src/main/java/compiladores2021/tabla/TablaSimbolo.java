package compiladores2021.tabla;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class TablaSimbolo {

    private static final TablaSimbolo instance = new TablaSimbolo();

    private List<Map<String, Id>> tabla = new LinkedList<>();

    public static TablaSimbolo getInstance() {
        return instance;
    }

    public void addContexto() {
        Map<String, Id> context = new HashMap<>();
        tabla.add(context);
    }

    public void delContexto() {
        // System.out.println("#######################OLD");
        // System.out.println(this);
        if (!tabla.isEmpty()) {
            tabla.remove(tabla.size() - 1);
        }
        // System.out.println("#######################NEW");
        // System.out.println(this);
    }

    public void addSimbolo(Id id) {
        Map<String, Id> ctx = tabla.get(tabla.size() - 1);
        ctx.putIfAbsent(id.getName(), id);
    }

    // buscarsimbolo general es cuando lo quiero usar. tengo que buscar en toda la
    // tabla de simbolo
    public Id buscarSimbolo(String name) {
        Iterator<Map<String, Id>> tbl = ((LinkedList<Map<String, Id>>) tabla).descendingIterator();

        while (tbl.hasNext()) {
            Map<String, Id> i = (Map<String, Id>) tbl.next();
            if (i.containsKey(name)) {
                return i.get(name);
            }
        }
        return null;
    }

    public Map<String,Id> buscarContextoSimbolo(String name) {
        Iterator<Map<String, Id>> tbl = ((LinkedList<Map<String, Id>>) tabla).descendingIterator();

        while (tbl.hasNext()) {
            Map<String, Id> i = (Map<String, Id>) tbl.next();
            if (i.containsKey(name)) {
                return i;
            }
        }
        return null;
    }

    public boolean containsSimboloLocal(String name) {
        Map<String, Id> ctx = tabla.get(tabla.size() - 1);
        return ctx.containsKey(name);
    }

    public Map<String, Id> getCurrentContexto() {
        return tabla.get(tabla.size() - 1);
    }

    public Map<String, Id> getPrevContexto() {
        return tabla.get(tabla.size() - 2);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("---Current Symbol table---\n");
        for (int i = 0; i < tabla.size(); i++) {
            str.append("context " + i + ": \n");
            for (Id id : tabla.get(i).values()) {
                str.append("\t" + id);
                str.append("\n");
            }
        }
        str.append("--------------------------");
        return str.toString();
    }

}
