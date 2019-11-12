import java.awt.font.NumericShaper;
import java.io.*;
import java.util.StringTokenizer;

public class LeituraArquivo implements Cloneable
{
    private String nomeArquivo;
    private Verificar verificar;
    private Matriz armazena;
    private BufferedReader arquivo;
    protected Resolucao resol;

    public LeituraArquivo(String nomeArq) throws  Exception // .Construtor que recebe como Parametro o nome do Arquivo e Verifica se o nome é valido
    {
        if(nomeArq == null || nomeArq.equals("") || nomeArq.equals(" "))
            throw new Exception("Nome de Arquivo Invalido!");
       nomeArq += ".txt";

        this.nomeArquivo = nomeArq;
        this.leituraLinha();
        this.validacoesMatriz();
        OperacoesMatriz opMatriz =  new OperacoesMatriz(armazena);
        resol = new Resolucao(armazena.getMatriz());
    }
    private int contadorLinhasArquivo() // Faz z contagem de Linhas para fazer a comparação com Qtd
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

    private void validacoesMatriz() throws Exception // Chama outro dois metodos Pra ve se ela Tem uma ou Mais Linhas ou Colunas somente com Zeros
    {
        verificar.verificarZerosLinhas(armazena.getMatriz());
        verificar.verificarZerosColunas(armazena.getMatriz());
    }

    private void leituraLinha() throws Exception// Faz a Leitura da Linha, Passa para Transformar em um Valor Numerico e Armazena na Matriz
    {
            arquivo = new BufferedReader (new FileReader(this.nomeArquivo));

            int qtdEquacoes = 0;
            try
            {
                qtdEquacoes = Integer.parseInt(arquivo.readLine());
            }
            catch(NumberFormatException erro)
            {
                throw new Exception("Não Foi Possivel Converter!");
            }

            verificar = new Verificar(qtdEquacoes);
            int linhas = this.contadorLinhasArquivo();
            verificar.qtdLinhasValida(qtdEquacoes,linhas);

            armazena = new Matriz(verificar.getQtd());
            for (int i=0; i<qtdEquacoes; i++)
            {
                StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
                verificar = new Verificar(qtdEquacoes,quebrador);
                while (quebrador.hasMoreTokens()) {
                    verificar.transformeToDouble(quebrador.nextToken());
                    armazena.inclua(verificar.getValor());
                }
            }
            arquivo.close();
    }

    // Obrigatorios Clone
    public String getNomeArquivo() { return this.nomeArquivo; }
    public String toString(){
        String ret = "";
        try {
            BufferedReader ler;
            FileReader lerArq = new FileReader(this.nomeArquivo);
            ler = new BufferedReader(lerArq);

            int contadorLinhas = 0;
            while (contadorLinhas != armazena.getQtdExpressao()+1) {
                ret += ler.readLine();
                ret += "\n";
                contadorLinhas++;
            }ler.close();
        }catch(IOException erro){}
        return ret;
    }
    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + nomeArquivo.hashCode();
        ret = ret * 7 + verificar.hashCode();
        ret = ret * 7 + armazena.hashCode();
        ret = ret * 7 + arquivo.hashCode();
        ret = ret * 7 + resol.hashCode();
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

        this.verificar = mold.verificar;
        this.armazena = mold.armazena;
        this.arquivo = mold.arquivo;
        this.resol = mold.resol;
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
        if(aux.resol != this.resol)
            return false;
        if(aux.arquivo != this.arquivo)
            return false;
        if(aux.armazena != this.armazena)
            return false;
        if(aux.verificar != this.verificar)
            return false;
        return true;
    }

// Os Acessadores, Getters
    public Verificar getVerificar() {
        return verificar;
    }

    public Matriz getArmazena() {
        return armazena;
    }

    public BufferedReader getArquivo() {
        return arquivo;
    }

    public Resolucao getResol() {
        return resol;
    }
}
