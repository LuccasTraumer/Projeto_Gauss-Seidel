import java.util.StringTokenizer;
public class Verificar implements Cloneable{
    private double valor;
    private int qtd;

    public Verificar()
    {
        this.qtd = 0;
        this.valor = 0.0;
    }

    public Verificar(int qtdEquacoes, StringTokenizer contador) throws Exception
    {
        this.qtd = qtdEquacoes;
        if(contador.countTokens() != this.qtd+1)
            throw new Exception("Quantidade de Valores na Coluna é INVALIDO!");

    }
    protected void qtdLinhasValida(int qtdExpressoes, int qtdLinhas) //throws Exception
    {
        try {
            if (qtdExpressoes != qtdLinhas)
                throw new Exception("Quantidade de Linhas passadas é DIFERENTE da quantidade de Linhas no Arquivo de Texto!");
            else
                this.qtd = qtdExpressoes;
        }catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }
    }
    public Verificar (int qtdExpressao) throws Exception
    {
        if(this.verificaQtd(qtdExpressao) != true)
            throw new Exception("Quantidade de Expressões passada é Invalida!");
        this.qtd = qtdExpressao;
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
    protected void verificarZerosLinhas(double[][] matriz) throws Exception
    {
        int contZero = 0;
        for(int i=0; i < matriz.length;i++)
        {
            for(int j=0; j < matriz.length;j++)
            {
                if(matriz[j][i] == 0)
                    contZero++;

            }
            if(contZero == matriz.length)
                throw new Exception("Existe uma ou mais linhas preenchidas somente com 0.");
            contZero = 0;
        }
    }
    protected void verificarZerosColunas(double[][] matriz) throws Exception
    {
        int contZero = 0;
        for(int i=0; i < matriz.length;i++)// linhas
        {
            for(int j=0; j < matriz.length;j++) {
                if(matriz[i][j] == 0)
                    contZero++;
            }
            if(contZero == matriz.length)
               throw new Exception("Existe uma ou mais colunas preenchidas somente com 0.");
            contZero = 0;

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
    public double getValor(){return this.valor;}
    public int getQtd (){ return this.qtd;}

}
