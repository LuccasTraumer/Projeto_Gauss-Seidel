import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class LeituraArquivo implements Cloneable{
    private String nomeArquivo;

    public LeituraArquivo(String nomeArq) throws  Exception
    {
        if(nomeArq == null )
            throw new Exception("Valor NUlo!");

        this.nomeArquivo = nomeArq;
        this.leituraLinha();
    }

    private void leituraLinha()
    {
        // ALERTA CASO VÁ USAR SO WINDOWS USA DESSA MANEIRA O CAMINHO DO ARQUIVO
        //C:\Users\User\Documents\ProjetaoFinal\gauss.txt
        // ALERTA CASO VÁ USAR SO UBUNTU USA DESSA MANEIRA O CAMINHO DO ARQUIVO
        ///home/giovana.pinheiro/Documents/ProjetaoFinal/gauss.txt
        // MUDAR NOME DO CAMINHO
        try{
            BufferedReader arquivo =
                    new BufferedReader (
                            new FileReader(
                                    "/home/giovana.pinheiro/Documents/ProjetaoFinal/gauss.txt"));

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
            System.out.println("Linhas");
            verificar.verificarZerosLinhas(armazena.getMatriz());
            System.out.println("Colunas");
            verificar.verificarZerosColunas(armazena.getMatriz());



        }catch (Exception erro){}
    }
    // Obrigatorios Clone
    public String getNomeArquivo() { return this.nomeArquivo; }
    public String toString(){return this.nomeArquivo;}
    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + nomeArquivo.hashCode();
        if(ret < 0)
            ret =- ret;

        return ret;
    }
    public Object clone()
    {
        LeituraArquivo ret = null;
        try{
            ret = new LeituraArquivo(this);
        }catch(Exception erro){}
        return  ret;
    }
    public LeituraArquivo(LeituraArquivo mold) throws Exception
    {
        if(mold == null)
            throw new Exception("Objeto Nulo!");
        this.nomeArquivo = mold.nomeArquivo;
    }
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        LeituraArquivo aux = (LeituraArquivo)obj;
        if(aux.nomeArquivo != this.nomeArquivo)
            return false;

        return true;
    }

}
