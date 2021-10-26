package compiladores2021;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;

import compiladores2021.compiladorParser.AssignContext;
import compiladores2021.compiladorParser.BloqueContext;
import compiladores2021.compiladorParser.DeclContext;
import compiladores2021.compiladorParser.DeclaracionFnContext;
import compiladores2021.compiladorParser.DeclaracionVarContext;
import compiladores2021.compiladorParser.FnCallContext;
import compiladores2021.compiladorParser.InstruccionContext;
import compiladores2021.compiladorParser.ParamContext;
import compiladores2021.compiladorParser.ParamsContext;
import compiladores2021.compiladorParser.ProgramaContext;
import compiladores2021.compiladorParser.VarContext;
import compiladores2021.tabla.Funcion;
import compiladores2021.tabla.Id;
import compiladores2021.tabla.Id.TipoDato;
import compiladores2021.tabla.TablaSimbolo;
import compiladores2021.tabla.Variable;

public class MyListener extends compiladorBaseListener {

    private TablaSimbolo table = TablaSimbolo.getInstance();

    Stack<Id> stack = new Stack<Id>();

    @Override
    public void enterPrograma(ProgramaContext ctx) {
        table.addContexto();
    }

    @Override
    public void exitPrograma(ProgramaContext ctx) {
        // table.delContexto();
    }

    @Override
    public void enterInstruccion(InstruccionContext ctx) {
        System.out.println("> instruction: " + ctx.getText());
    }

    @Override
    public void enterBloque(BloqueContext ctx) {
        table.addContexto();
    }

    @Override
    public void exitBloque(BloqueContext ctx) {
        table.delContexto();
    }

    @Override
    public void exitDeclaracionVar(DeclaracionVarContext ctx) {
        int tokenType = ctx.tipovar().getStart().getType();
        Id.TipoDato tipo = Id.getTipoDato(tokenType);

        while (!stack.isEmpty()) {
            Id id = stack.pop();
            id.setTipo(tipo);
            table.addSimbolo(id);
        }
    }

    @Override
    public void exitDecl(DeclContext ctx) {
        initVar(ctx, false);
    }

    @Override
    public void exitAssign(AssignContext ctx) {
        initVar(ctx, true);
    }

    @Override
    public void exitVar(VarContext ctx) {
        String name = ctx.ID().getSymbol().getText();
        Id var = table.buscarSimbolo(name);
        if (var == null) {
            System.err.println("no such variable: " + name);
        }
        if (var instanceof Funcion) {
            System.err.println("is not a variable: " + name);
        }
        if (var instanceof Variable && !var.isInicializado()) {
            System.err.println("la siguiente variable no esta init: " + name);
        }
    }

    @Override
    public void enterDeclaracionFn(DeclaracionFnContext ctx) {
        int tokenType = ctx.tipovar().getStart().getType();
        Id.TipoDato tipo = Id.getTipoDato(tokenType);
        String name = ctx.ID().getText();

        Funcion id = new Funcion();
        id.setTipo(tipo);
        id.setName(name);
        id.setInicializado(true);

        table.addSimbolo(id);

        table.addContexto();
    }

    @Override
    public void exitDeclaracionFn(DeclaracionFnContext ctx) {
        table.delContexto();
    }

    @Override
    public void exitParams(ParamsContext ctx) {
        Map<String, Id> fnParamsContexto = table.getCurrentContexto();
        Map<String, Id> fnContexto = table.getPrevContexto();

        List<TipoDato> tipoDatos = new LinkedList<TipoDato>();

        for (Id id : fnParamsContexto.values()) {
            tipoDatos.add(id.getTipo());
        }

        ParserRuleContext fContext = ctx.getParent();
        while (!(fContext instanceof DeclaracionFnContext)) {
            fContext = fContext.getParent();
        }

        DeclaracionFnContext fnContext = (DeclaracionFnContext) fContext;

        Id id = fnContexto.get(fnContext.ID().getText());

        if (id instanceof Funcion) {
            ((Funcion) id).setArgs(tipoDatos);
            // System.out.println("Added: " + tipoDatos + " to " + id.getName());
        }

    }

    @Override
    public void exitParam(ParamContext ctx) {
        initVar(ctx, true);

        Id var = stack.pop();
        int tokenType = ctx.tipovar().getStart().getType();
        Id.TipoDato tipo = Id.getTipoDato(tokenType);

        var.setTipo(tipo);
        table.addSimbolo(var);
    }

    @Override
    public void exitFnCall(FnCallContext ctx) {
        String name = ctx.ID().getSymbol().getText();
        Id var = table.buscarSimbolo(name);
        if (var == null) {
            System.err.println("no such funcion: " + name);
        }
        if (var instanceof Variable) {
            System.err.println("is not a funcion: " + name);
        }
        if (var instanceof Funcion && !var.isInicializado()) {
            System.err.println("la siguiente funcion no esta init: " + name);
        }
    }

    private void initVar(ParserRuleContext ctx, boolean init) {
        String name = ctx.getToken(compiladorParser.ID, 0).getText();
        if (!table.containsSimboloLocal(name)) {
            Variable var = new Variable();
            var.setName(name);
            var.setInicializado(init);
            stack.add(var);
        } else {
            System.out.println("La variable ya existe: " + name);
        }
    }
}
