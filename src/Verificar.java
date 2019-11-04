import java.util.StringTokenizer;
public class Verificar {
    private double valor;
    private int qtd;
    public Verificar(int qtdEquacoes, StringTokenizer contador,int linhas) throws Exception
    {
        this.qtd = qtdEquacoes;
        if(contador.countTokens() != this.qtd+1)
            throw new Exception("Valores Invalidos");

        if(linhas > this.qtd)
            throw new Exception("Linha a mais");

    }

    public Verificar (int qtdExpressao) throws Exception
    {
        if(this.verificaQtd(qtdExpressao) != true)
            throw new Exception("Valor invalido");

        this.qtd = qtdExpressao;
    }

    public int getQtd (){
        return this.qtd;
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
/*
    protected  boolean verificaQtd ()
    {

        if(this.qtd <= 0)
            return false;

        return true;
    }
*/
    protected  boolean verificaQtd (int qtd)
    {
        if(qtd <= 0)
            return false;

        return true;
    }


}
