package compiladores2021;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class App {

    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("input/decl.txt");
        compiladorLexer lexer = new compiladorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        compiladorParser parser = new compiladorParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.programa();

        // System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        MyListener loader = new MyListener();
        walker.walk(loader, tree);

    }
}
