import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class LeituraArquivo implements Cloneable{
    private String nomeArquivo;
    protected Verificar verificar;
    protected Matriz armazena;
    public LeituraArquivo(String nomeArq) throws  IOException
    {
        if(nomeArq == null || nomeArq.equals("") || nomeArq.equals(" "))
            throw new IOException("Valor NUlo!");

        this.nomeArquivo = nomeArq;
        this.leituraLinhas();
        //this.validacoesMatriz();
    }
    private void validacoesMatriz() throws Exception
    {
        verificar.verificarZerosLinhas(armazena.getMatriz());
        verificar.verificarZerosColunas(armazena.getMatriz());
        OperacoesMatriz opMatriz =  new OperacoesMatriz(armazena);
    }
    private int contadorLinhasArquivo()
    {
        int contador = 0;
        try{
            BufferedReader ler;
            FileReader lerArq = new FileReader(this.nomeArquivo);
            ler = new BufferedReader(lerArq);

            while(ler.readLine() != null)
                contador++;
        }catch (IOException erro){}
        return contador-1; // menos 1 pois ele conta o 3 que seria a Qtd do Arquivo então tiramos esse valor
    }

    private void leituraLinhas()
    {
        // ALERTA CASO VÁ USAR SO WINDOWS USA DESSA MANEIRA O CAMINHO DO ARQUIVO
        //C:\Users\User\Documents\ProjetaoFinal\gauss.txt
        // ALERTA CASO VÁ USAR SO UBUNTU USA DESSA MANEIRA O CAMINHO DO ARQUIVO
        ///home/giovana.pinheiro/Documents/ProjetaoFinal/gauss.txt
        // MUDAR NOME DO CAMINHO
        BufferedReader arquivo ;
        try{
                arquivo =
                    new BufferedReader (
                            new FileReader(
                                    this.nomeArquivo));

            int qtdEquacoes = Integer.parseInt(arquivo.readLine());
            int linhas = this.contadorLinhasArquivo();

            verificar = new Verificar(qtdEquacoes);
            verificar.qtdLinhasValida(qtdEquacoes,linhas);

            armazena = new Matriz(verificar.getQtd());
            for (int i=0; i<qtdEquacoes; i++)
            {

                StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
                try {
                    verificar = new Verificar(qtdEquacoes, quebrador);
                }
                catch (Exception erro){ System.err.println(erro.getMessage());}
                while (quebrador.hasMoreTokens()) {
                    verificar.transformeToDouble(quebrador.nextToken());
                    armazena.inclua(verificar.getValor());
                }
            }
            arquivo.close();
            this.validacoesMatriz();
        }catch (Exception erro)
        {
            System.out.println(erro.getMessage());
        }

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
