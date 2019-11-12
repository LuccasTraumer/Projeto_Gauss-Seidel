import java.io.*;


public class Programa {

    public static void main(String[] Args)
    {
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite o caminho do arquivo a ser Lido: ");
            String nomeArquivo = teclado.readLine();
            LeituraArquivo ler = new LeituraArquivo(nomeArquivo);
            System.out.println(ler.resol);
            System.out.println(ler);

        }catch (Exception err){
            System.err.println("Erros ocorridos.:");
            System.err.println(err.getMessage());
        }
    }
}
