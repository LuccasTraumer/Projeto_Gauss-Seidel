import java.io.*;


public class Programa {

    public static void main(String[] Args)
    {
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite o Nome do Arquivo a ser Lido: ");
            String nomeArquivo = teclado.readLine();
            LeituraArquivo ler = new LeituraArquivo(nomeArquivo);
            System.out.println(ler.resol);
            System.out.println(ler);

        }catch (Exception err){
            System.out.println(err.getMessage());
        }
    }
}
