import java.io.*;
import java.util.StringTokenizer;

public class Programa {

    public static void main(String[] Args)
    {
        try {
            LeituraArquivo ler = new LeituraArquivo("gauss.txt");
            System.out.println(ler.nomeArquivo);
            //Matriz mat = new Matriz(4);

        }catch (Exception err){}
    }
}
