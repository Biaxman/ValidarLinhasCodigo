import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        Path arquivo = Paths.get("arquivo.txt");
        ArrayList<String> fimLinhasCodigo = new ArrayList<>();
        List<String> linhaCodigos = Files.readAllLines(arquivo);
        
        int linhaCodigoAtual = 0;
         for (String linha : linhaCodigos) {

            if(lerLinhasCodigo(linha)){
                fimLinhasCodigo.add(linhaCodigoAtual, linha+"  - OK");
            }
            else{
                fimLinhasCodigo.add(linhaCodigoAtual, linha+"  - Inválido");
            }
            linhaCodigoAtual++;
        } 
        Files.write(arquivo, fimLinhasCodigo);
        
    }

    public static boolean lerLinhasCodigo(String s){
        Stack<Character> parentesesEsquerda = new Stack<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) =='(' || s.charAt(i) =='['|| 
            s.charAt(i)=='<'|| s.charAt(i)=='{'){
                parentesesEsquerda.push(s.charAt(i));
            }
            else{
                if(parentesesEsquerda.isEmpty())
                    return false;
                if(parentesesEsquerda.peek() == '(' && s.charAt(i)!=')'||
                parentesesEsquerda.peek() == '[' && s.charAt(i)!=']'||
                parentesesEsquerda.peek() == '<' && s.charAt(i)!='>'||
                parentesesEsquerda.peek() == '{' && s.charAt(i)!='}'){

                    return false;
                }
                parentesesEsquerda.pop();
            }
        }
        return parentesesEsquerda.isEmpty();
    }
}
