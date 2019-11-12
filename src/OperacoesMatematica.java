public class OperacoesMatematica {
    private Matriz matrizOperacoes;

    public OperacoesMatematica(Matriz matriz)
    {
        this.matrizOperacoes = matriz;
    }

    protected static int Fatoracao (int qtd) // .Faz a Fatoração da Qtd, pra descobrir a qntd de Possibilidade
    {
        int valor = 1;
        for(int i=qtd; i>1; i--)
        {
            valor *= i;
        }
        return valor;
    }

    protected void multiplicadorColuna(int coluna) // Multiplica a Coluna depois da Divisão da Linha
    {
        for (int j = coluna; j == coluna; j++) {
            for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
                if (matrizOperacoes.matriz[i][j] != 0 && i != j) {
                    double multiplicador = -matrizOperacoes.matriz[i][j];
                    for (int z = 0; z < matrizOperacoes.getQtdExpressao() + 1; z++) {
                        matrizOperacoes.matriz[i][z] += matrizOperacoes.matriz[j][z] * multiplicador;
                        if(matrizOperacoes.matriz[i][z] == -0)
                        {
                            matrizOperacoes.matriz[i][z] = 0;
                        }
                    }
                }
            }
        }
    }

    protected void divisaoLinha() // faz a Divisão da Linha
    {
        for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
            for (int j = 0; j < matrizOperacoes.getQtdExpressao() + 1; j++) {
                if (i == j) {
                    for (int z = 0; z < matrizOperacoes.getQtdExpressao(); z++) {
                        double divisor = matrizOperacoes.matriz[z][z];
                        for (int y = 0; y < matrizOperacoes.getQtdExpressao() + 1; y++) {
                            matrizOperacoes.matriz[z][y] /= divisor;
                        }
                        multiplicadorColuna(z);
                    }
                }
            }
        }
    }

    // Obrigatorios Clone,Construtor copia,hashCode, equals
    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + matrizOperacoes.hashCode();

        if(ret < 0)
            ret -= ret;
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
        OperacoesMatematica aux = (OperacoesMatematica)obj;
        if(aux.matrizOperacoes != this.matrizOperacoes)
            return false;
        return true;
    }

    public OperacoesMatematica(OperacoesMatematica mold) throws Exception
    {
        if(mold == null)
            throw new Exception("Objeto Nulo, não Possivel Clona-lo");
        this.matrizOperacoes = mold.matrizOperacoes;
    }

    public Object clone()
    {
        OperacoesMatematica ret = null;
        try {
            ret = new OperacoesMatematica(this);
        }catch (Exception err){}
        return ret;
    }
    // Acessadores
    public Matriz getMatrizOperacoes() {
        return matrizOperacoes;
    }
}
