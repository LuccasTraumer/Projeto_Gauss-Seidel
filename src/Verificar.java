import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class Verificar implements Cloneable{
    private double valor;
    private int qtd;

    public Verificar()
    {}

    public Verificar(int qtdEquacoes, StringTokenizer contador) throws Exception
    {
        try {
            this.qtd = qtdEquacoes;
            if (contador.countTokens() != this.qtd + 1)
                throw new Exception("Não é uma matriz válida.");
        }catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }

    }

    protected void qtdLinhasValida(int qtdExpressoes, int qtdLinhas)
    {
        try {
            if (qtdExpressoes != qtdLinhas)
                throw new Exception("Quantidade de Linhas Invalidas!");
            else
                this.qtd = qtdExpressoes;
        }catch (Exception erro)
        {
            System.out.println(erro.getMessage());
        }


    }

    public Verificar (int qtdExpressao)
    {
        try {
            if (this.verificaQtd(qtdExpressao) != true)
                throw new Exception("Valor invalido");

            this.qtd = qtdExpressao;
        }catch (Exception erro){
            System.err.println(erro.getMessage());
        }
    }
    protected boolean temZeroDiagonal(double valor)
    {
        if(valor == 0)
            return true;

        return false;
    }

    protected void transformeToDouble(String valor)
    {
        try{
            this.valor = Double.parseDouble(valor);
        }catch(NumberFormatException err){
            this.valor = 0.0;
        }
    }
    public double getValor(){return this.valor;}
    public int getQtd (){ return this.qtd;}

    protected  boolean verificaQtd ()
    {

        if(this.qtd <= 0)
            return false;

        return true;
    }

    protected boolean temZerosDiagonal(double[][] matriz)
    {

        for(int i=0;i<matriz.length;i++){
            if(matriz[i][i] == 0) {
                return true;
            }
        }
        return false;
    }

    protected  boolean verificaQtd (int qtd)
    {
        if(qtd <= 0)
            return false;

        return true;
    }
    protected void verificarZerosLinhas(double[][] matriz)
    {
        try{
        int contZero = 0;
        for(int i=0; i < matriz.length;i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[j][i] == 0)
                    contZero++;

            }
            if (contZero == matriz.length)
                throw new Exception("Existe uma ou mais linhas preenchidas somente com 0.");
            //System.out.println(contZero);
            contZero = 0;
        }
        }catch (Exception erro)
        {
            System.out.println(erro.getMessage());
        }
    }
    protected void verificarZerosColunas(double[][] matriz) throws Exception
    {
        // colunas vai até "resultado" ?
        try {
        int contZero = 0;
        for(int i=0; i < matriz.length-1;i++)// linhas
        {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == 0)
                    contZero++;
            }
            if (contZero == matriz.length)
                throw new Exception("Existe uma ou mais colunas preenchidas somente com 0.");
            //System.out.println(contZero);
            contZero = 0;
        }
        }catch (Exception erro)
        {
            System.err.println(erro.getMessage());
            erro.printStackTrace();
        }

    }
    // Obrigatorios toString, equals, clone, construtor copia, hashCode
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        Verificar aux = (Verificar)obj ;
        if(aux.qtd != this.qtd)
                return false;
        if(aux.valor != this.valor)
            return false;

        return true;
    }
    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + new Integer(this.qtd).hashCode();
        ret = ret * 7 + new Double(this.valor).hashCode();
        if(ret < 0)
            ret =- ret;

        return ret;
    }
    public String toString()
    {
        return "Valor que esta sendo Armazenado na Variavel Valor: "+ this.valor +
                "\n Quantidade de Expressoes: "+ this.qtd;
    }
    public Object clone()
    {
        Verificar ret = null;
        try {
            ret = new Verificar(this);
        }catch(Exception erro){}
        return ret;
    }
    public Verificar(Verificar obj) throws Exception
    {
        if(obj == null)
            throw new Exception("Não Possivel Clonar, pois Objeto é nulo!");
        this.qtd = obj.qtd;
        this.valor = obj.valor;
    }

}
