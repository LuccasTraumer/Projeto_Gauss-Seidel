import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class LeituraArquivo {
    protected String nomeArquivo;

    public LeituraArquivo(String nomeArq) throws  Exception
    {
        if(nomeArq == null )
            throw new Exception("Valor NUlo!");

        this.nomeArquivo = nomeArq;
        this.leituraLinha();
    }

    private void leituraLinha()
    {
        try{
            BufferedReader arquivo =
                    new BufferedReader (
                            new FileReader(
                                    "~/Projetos/projetaoFinal/src/gauss.txt"));

            int qtdEquacoes = Integer.parseInt (arquivo.readLine());
            Verificar verificar = new Verificar(qtdEquacoes);
            int linhas = 0;
            Matriz armazena = new Matriz(verificar.getQtd());
            for (int i=0; i<qtdEquacoes; i++)
            {
                linhas++;
                StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
                verificar = new Verificar(qtdEquacoes,quebrador,linhas);
                while (quebrador.hasMoreTokens()) {
                    verificar.transformeToDouble(quebrador.nextToken());
                    armazena.inclua(verificar.getValor());
                    //System.out.println(verificar.getValor());
                }
            }



        }catch (Exception erro){}
    }
}
