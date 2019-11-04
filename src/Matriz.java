public class Matriz {
    private double[][] matriz;
    private int linhas = 0;
    private int colunas = 0;
    private Verificar verf;
    private int qtdExpressao;

    public Matriz (int qtdExpressao) {
        this.qtdExpressao = qtdExpressao;
        int valor = qtdExpressao+1;
        matriz = new double [qtdExpressao][valor];

    }
    protected void inclua(double valor){
        if(this.linhas < this.qtdExpressao && this.colunas < this.qtdExpressao+1)
        {
            matriz[linhas][colunas] = valor;

            //System.out.print(matriz[linhas][colunas]+" ");
            this.colunas++;
            if(this.colunas >= this.qtdExpressao+1)
            {
                this.linhas++;
                this.colunas=0;
                //System.out.println();
            }
        }

    }
    protected  void Mat (){
        for(int i=0; i < qtdExpressao;i++)
        {
            for(int j=0;j < qtdExpressao+1;j++)
                System.out.println(matriz[i][j] + " ");
        }

    }

    public double[][] getMatriz()
    {
        return this.matriz;
    }

}
