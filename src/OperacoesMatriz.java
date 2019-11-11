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
        //this.blocoDePossibilidades();

        if (verificar.temZerosDiagonal(matrizOperacoes.matriz))
            this.testarLinhas();
        validarEquacoesEquivalentes();
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
    private void testarLinhas()
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
    }
<<<<<<< HEAD
    public void validarEquacoesEquivalentes() // Verifica se tem linhas Equivalentes
=======
    public void validarEquacoesEquivalentes() throws Exception
>>>>>>> 52bec6c590ecfe04067deae006c9e5b751554f30
    {
        double[][] matrizTemp = new double[matrizOperacoes.getQtdExpressao()][matrizOperacoes.getQtdExpressao()];

        for (int j = 0; j < matrizOperacoes.getQtdExpressao(); j++) {
            int z = 0;
            for (int i = 0; i < matrizOperacoes.getQtdExpressao(); i++) {

                for (int ii = i + 1; ii < matrizOperacoes.getQtdExpressao(); ii++) {
                    //System.out.println(matrizOperacoes.matriz[i][j] + " / " + (matrizOperacoes.matriz[ii][j]) + " = " + (matrizOperacoes.matriz[i][j] == 0 || matrizOperacoes.matriz[ii][j] == 0 ? 0 : matrizOperacoes.matriz[i][j] / matrizOperacoes.matriz[ii][j]) + " | i:" + i + " j:" + j + " ii:" + ii + " - Z:" + z);
                    if (matrizOperacoes.matriz[i][j] == 0 || matrizOperacoes.matriz[ii][j] == 0) {
                      matrizTemp[z][j] = 0;
                    } else {
                    matrizTemp[z][j] = matrizOperacoes.matriz[i][j] / matrizOperacoes.matriz[ii][j];
                    }
                    z++;
                }
            }
        }

      /*  for (int i = 0; i < matrizTemp.length; i++) {
            for (int j = 0; j < matrizTemp.length; j++)

                System.out.print(matrizTemp[i][j] + " ");
            System.out.println();
        }*/

        for (int i = 0; i < matrizTemp.length; i++) {
            int ocorrencia = 0;
            double aux = matrizTemp[i][0];
            for (int j = 0; j < matrizTemp.length; j++) {
                if (aux != matrizTemp[i][j]) {
                    break;
                }
                ocorrencia++;
            }
            if (ocorrencia == matrizTemp.length) {
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
    protected void blocoDePossibilidades()// Seria o Bloco de Possibolidades,mas não conseguimos Terminar
    {
        long qtdColunas = OperacoesMatematica.Fatoracao(matrizOperacoes.getQtdExpressao());
        long colunasBlocos = qtdColunas / matrizOperacoes.getQtdExpressao();
        int tamanhoBloco = (int) colunasBlocos;
        int[][] blocoAuxiliar = new int[matrizOperacoes.getQtdExpressao()][tamanhoBloco];
        int qualValor = 0, qtdInserida = 0;

        for(int i=0;i<matrizOperacoes.getQtdExpressao();i++){
            for(int j=0; j< tamanhoBloco;j++){
                if(tamanhoBloco == matrizOperacoes.getQtdExpressao()-1)
                {
                    qualValor++;
                }
                blocoAuxiliar[i][j] = qualValor;
            }
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







