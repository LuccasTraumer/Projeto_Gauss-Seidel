import java.util.StringTokenizer;
public class Verificar implements Cloneable{
    private double valor; // Vai armazenar os valores que vai vir do Arquivo txt
    private int qtd; // Vai armazenar a Quantidade de Linhas do Arquivo de texto

    public Verificar()
    {
        this.qtd = 0;
        this.valor = 0.0;
    }

    public Verificar(int qtdEquacoes, StringTokenizer contador) throws Exception
    {
        this.qtd = qtdEquacoes;
        if(contador.countTokens() != this.qtd+1)
            throw new Exception("Quantidade de Valores na Linha é INVALIDO!.");
    }

    protected void qtdLinhasValida(int qtdExpressoes, int qtdLinhas) throws Exception
    {
            if (qtdExpressoes != qtdLinhas)
                throw new Exception("Quantidade de Linhas passadas é DIFERENTE da quantidade de Linhas no Arquivo de Texto!");
            else
                this.qtd = qtdExpressoes;
    }

    public Verificar (int qtdExpressao) throws Exception // Verifica se a Quantidade de Linhas é valida
    {
        if(this.verificaQtd(qtdExpressao) != true)
            throw new Exception("Quantidade de Expressões passada é Invalida!");
        this.qtd = qtdExpressao;
    }

    protected boolean temZeroDiagonal(double valor) // Verifica se nas Diagonais Principais tem Zero, Verificando por Valor(que esta vindo como Parametro)
    {
        if(valor == 0)
            return true;
        return false;
    }

    protected void transformeToDouble(String valor) throws Exception// Transforma o texto do Arquivo de txt em um valor Double
    {
        try{
            this.valor = Double.parseDouble(valor);
        }catch(NumberFormatException err){
            throw new Exception("Existe algum valor não válido na matriz passada.");
            //this.valor = 0.0; opção para transformar valor inválido em 0
        }
    }

    protected boolean temZerosDiagonal(double[][] matriz) // Verifica na Matriz se tem Zero na Diagonal a partir da matriz
    {
        for(int i=0;i<matriz.length;i++){
            if(matriz[i][i] == 0) {
                return true;
            }
        }
        return false;
    }

    protected  boolean verificaQtd (int qtd) // Valida o Qtd Expressao
    {
        if(qtd <= 0)
            return false;
        return true;
    }

    protected void verificarZerosColunas(double[][] matriz) throws Exception // Verifica se tem Zero só Zeros em uma Coluna
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
                throw new Exception("Existe uma ou mais Colunas preenchidas somente com 0.");
            contZero = 0;
        }
    }

    protected void verificarZerosLinhas(double[][] matriz) throws Exception // Verifica se tem Zero só Zeros em uma Linhas
    {
        int contZero = 0;
        for(int i=0; i < matriz.length;i++)// linhas
        {
            for(int j=0; j < matriz.length;j++) {
                if(matriz[i][j] == 0)
                    contZero++;
            }
            if(contZero == matriz.length)
               throw new Exception("Existe uma ou mais Linhas preenchidas somente com 0.");
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

    // Acessadores
    public double getValor(){return this.valor;}
    public int getQtd (){ return this.qtd;}
}
