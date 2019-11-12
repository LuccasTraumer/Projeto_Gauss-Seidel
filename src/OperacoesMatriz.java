public class OperacoesMatriz
{
    protected Matriz matrizOperacoes;
    private int[][] matrizAuxiliar;
    private int[] vetorIndiceCorreto;
    private Verificar verificar;
    private OperacoesMatematica calculos;

    public OperacoesMatriz(Matriz matriz) throws Exception {
        this.matrizOperacoes = matriz;
        verificar = new Verificar();
        this.matrizDePossibilidades();

        if (verificar.temZerosDiagonal(matrizOperacoes.matriz))
            this.testarLinhas();
        validarEquacoesEquivalentes();
        testarLinhas();
        ordenaMatriz();
        calculos = new OperacoesMatematica(matrizOperacoes);
        calculos.divisaoLinha();
    }

    private void ordenaMatriz() // .Ordena na Ordem Correta com as Diagonais Diferente de Zero
    {
        double[][] matrizAuxz = new double[matrizOperacoes.getQtdExpressao()][matrizOperacoes.getQtdExpressao() + 1];
        for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
            int aux = vetorIndiceCorreto[i];
            for (int z = 0; z < matrizOperacoes.getQtdExpressao() + 1; z++) {
                matrizAuxz[i][z] = matrizOperacoes.matriz[aux][z];
            }
        }
        matrizOperacoes.matriz = matrizAuxz;
    }
    private void testarLinhas() throws Exception
    {
        this.vetorIndiceCorreto = new int[matrizOperacoes.getQtdExpressao()];
        int contador = 0;
        int colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.getQtdExpressao());
        for (int j = 0; j < colunas; j++) {
            if (vetorIndiceCorreto.length == contador)
                break;
            for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
                if (verificar.temZeroDiagonal(matrizOperacoes.matriz[this.matrizAuxiliar[i][j]][i])) {
                    contador = 0;
                    i = matrizOperacoes.matriz.length;
                } else {
                    vetorIndiceCorreto[i] = matrizAuxiliar[i][j];
                    contador++;
                }
            }
        }
        for(int i = 0; i<vetorIndiceCorreto.length;i++)
        {
            for(int j=i+1; j<vetorIndiceCorreto.length-1; j++)
            {
                if(vetorIndiceCorreto[i] == vetorIndiceCorreto[j])
                {
                    throw new Exception("Matriz inválida. Existe uma equação que é a única correspondente para duas ou mais linhas.");
                }
            }
        }
    }
    public void validarEquacoesEquivalentes() throws Exception
    {
        int qtdLinhas = 0;
        for(int i =0; i<matrizOperacoes.getQtdExpressao();i++) {
            qtdLinhas += i;
        }
        double[][] matrizTemp = new double[qtdLinhas][matrizOperacoes.getQtdExpressao()];
        for (int j = 0; j < matrizOperacoes.getQtdExpressao(); j++) {
            int z = 0;
            for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
                for (int ii = i + 1; ii < matrizOperacoes.getQtdExpressao(); ii++) {
                    if (matrizOperacoes.getMatriz()[i][j] == 0 || matrizOperacoes.getMatriz()[ii][j] == 0) {
                        matrizTemp[z][j] = 0;
                    } else {
                        matrizTemp[z][j] = matrizOperacoes.getMatriz()[i][j]/matrizOperacoes.getMatriz()[ii][j];
                    }
                    z++;
                }
            }
            System.out.println();
        }
        for (int i = 0; i < matrizTemp.length; i++) {
            int ocorrencia = 0;
            double aux = matrizTemp[i][0];
            for (int j = 0; j < matrizOperacoes.getMatriz().length; j++) {
                if (aux != matrizTemp[i][j]) {
                    break;
                }
                ocorrencia++;
            }
            if (ocorrencia == matrizOperacoes.matriz.length) {
                throw new Exception("Matriz inválida, possui equação equivalente.");
            }
        }
    }

    protected void matrizDePossibilidades() // Cria a Matriz de Possibilidades
    {
        long colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.getQtdExpressao());
        matrizAuxiliar = new int[matrizOperacoes.getQtdExpressao()][(int) colunas];
        long qtdPossibilidades = colunas;
        int incrementador = 0, qual = 0, qtdInserida = 0;

        for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {
            qtdPossibilidades /= matrizOperacoes.getQtdExpressao() - i;
            for (int j = 0; j < colunas; j++) {
                for (int y = 0; y < qtdInserida; y++) {
                    if (matrizAuxiliar[y][j] == qual) {
                        qual++;
                        y = -1;
                    }
                    if (qual > matrizOperacoes.getQtdExpressao() - 1) {
                        qual = 0;
                        y = -1;
                    }
                }
                matrizAuxiliar[i][j] = qual;
                incrementador++;

                if (incrementador == qtdPossibilidades) {
                    incrementador = 0;

                    if (qual == matrizOperacoes.getQtdExpressao() - 1)
                        qual = 0;
                    else
                        qual++;
                }
            }
            qual = 0;
            qtdInserida++;
        }
    }

    // Obrigatorios Clone,Construtor copia,hashCode, toString, equals
    public int hashCode()
    {
        int ret = 1;
        ret = ret * 7 + matrizOperacoes.hashCode();
        ret = ret * 7 + matrizAuxiliar.hashCode();
        ret = ret * 7 + vetorIndiceCorreto.hashCode();
        ret = ret * 7 + verificar.hashCode();
        ret = ret * 7 + calculos.hashCode();

        if(ret < 0)
            ret -= ret;
        return ret;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        OperacoesMatriz aux = (OperacoesMatriz)obj;
        if(aux.matrizOperacoes != this.matrizOperacoes)
            return false;
        if(aux.matrizAuxiliar != this.matrizAuxiliar)
            return false;
        if(aux.vetorIndiceCorreto != this.vetorIndiceCorreto)
            return true;
        if(aux.verificar != this.verificar)
            return false;
        if(aux.calculos != this.calculos)
            return false;
        return true;
    }

    public OperacoesMatriz(OperacoesMatriz mold) throws Exception
    {
        if(mold == null)
            throw new Exception("Objeto nule, não possivel Clonar!");
        this.matrizOperacoes = mold.matrizOperacoes;
        this.matrizAuxiliar = mold.matrizAuxiliar;
        this.vetorIndiceCorreto = mold.vetorIndiceCorreto;
        this.verificar = mold.verificar;
        this.calculos = mold.calculos;
    }

    public Object clone(){
        OperacoesMatriz ret = null;
        try{
            ret = new OperacoesMatriz(this);
        }catch (Exception erro){}
        return ret;
    }

    public Matriz getMatrizOperacoes() {
        return matrizOperacoes;
    }

    public int[][] getMatrizAuxiliar() {
        return matrizAuxiliar;
    }

    public int[] getVetorIndiceCorreto() {
        return vetorIndiceCorreto;
    }

    public Verificar getVerificar() {
        return verificar;
    }

    public OperacoesMatematica getCalculos() {
        return calculos;
    }
}







