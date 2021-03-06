import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
// Colocar o CompareTo
public class Matriz implements Cloneable
{
    protected double[][] matriz;
    private int linhas = 0;
    private int colunas = 0;
    protected Verificar verf;
    private int qtdExpressao;

    public Matriz (int qtdExpressao) {
        this.qtdExpressao = qtdExpressao;
        int colunas = qtdExpressao+1;
        matriz = new double [qtdExpressao][colunas];
    }

    protected void inclua(double valor) // .Inclui o Valor na Matriz
    {
        if(this.linhas < this.qtdExpressao && this.colunas < this.qtdExpressao+1)
        {
            matriz[linhas][colunas] = valor;
            this.colunas++;
            if(this.colunas >= this.qtdExpressao+1)
            {
                this.linhas++;
                this.colunas=0;
            }
        }
    }

    // Obrigatorios toString, equals, clone, construtor copia, hashCode
    public String toString(){
        String ret = "";
        for(int linhas = 0; linhas < matriz.length;linhas++)
        {
            for (int colunas = 0; colunas < matriz[linhas].length;colunas++)
            {
                ret += this.matriz[linhas][colunas]+" ";
            }
            ret+= "\n";
        }
        return ret;
    }

    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + new Integer(this.colunas).hashCode();
        ret = ret * 7 + new Integer(this.linhas).hashCode();
        ret = ret * 7 + verf.hashCode();
        ret = ret * 7 + new Integer(this.qtdExpressao).hashCode();
        for(int i=0; i < this.matriz.length;i++)
            for(int j=0; j < this.matriz[i].length;j++)
                ret = ret * 7 + new Double(this.matriz[i][j]).hashCode();

        if(ret < 0)
            ret =- ret;

        return ret;
    }
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        Matriz aux = (Matriz)obj;
        if(aux.colunas != this.colunas)
            return false;
        if(aux.linhas != this.linhas)
            return false;
        if(!aux.verf.equals(this.verf))
            return false;
        if(aux.qtdExpressao != this.qtdExpressao)
            return false;

        for(int linhas= 0; linhas < this.matriz.length; linhas++)
            for(int colunas=0; colunas < matriz[linhas].length;colunas++)
                if(aux.matriz[linhas][colunas] != this.matriz[linhas][colunas])
                    return false;

        return true;
    }
    public Object clone()
    {
        Matriz ret = null;
        try{
            ret = new Matriz(this);
        }catch(Exception erro){}
        return ret;
    }
    public Matriz(Matriz mold) throws Exception
    {
        if(mold == null)
            throw new Exception("Objeto Nulo!");

        this.qtdExpressao = mold.qtdExpressao;
        this.linhas = mold.linhas;
        this.colunas = mold.colunas;
        this.verf = mold.verf;

        for (int linhas=0; linhas < mold.matriz.length; linhas++)
            for(int colunas=0; colunas < mold.matriz[linhas].length; colunas++)
                this.matriz[linhas][colunas] = mold.matriz[linhas][colunas];

    }
    // Acessadores
    public int getLinhas() {return linhas;}

    public int getColunas() {return colunas;}

    public int getQtdExpressao() {return qtdExpressao;}

    public double[][] getMatriz() {return this.matriz;}

    public Verificar getVerf() { return verf; }
}
