public class OperacoesMatriz {
    private Matriz matrizOperacoes;
    private short[][] matrizAuxiliar;
    private int[] vetorIndiceCorreto;
    Verificar verificar;

    public OperacoesMatriz(Matriz matriz){
        this.matrizOperacoes = matriz;
        verificar = new Verificar();
        this.matrizDePossibilidades();
        if(verificar.temZerosDiagonal(matrizOperacoes.matriz))
           this.testarLinhas();
            //this.matrizDePossibilidades();

        // dividir os valores
    }

    private void testarLinhas()
    {
        this.vetorIndiceCorreto = new long[matrizOperacoes.matriz.length];
        //int contIndice = 0;
        long colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.matriz.length);
        for(int j = 0; j < colunas ;j++)
        {
            for(int i= 0; i <matrizOperacoes.matriz.length;i++)
            {
                if(verificar.temZeroDiagonal(matrizOperacoes.matriz[(int)this.matrizAuxiliar[i][j]][i]))
                    i = matrizOperacoes.matriz.length;
                else
                    vetorIndiceCorreto[i] = matrizAuxiliar[i][j];
            }
        }
        for(int i = 0; i < vetorIndiceCorreto.length;i++)
            System.out.println(vetorIndiceCorreto[i]);
    }
    protected void matrizDePossibilidades()
    {
        long colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.matriz.length);
        matrizAuxiliar = new long[matrizOperacoes.qtdExpressao][(int)colunas];

        long qtdPossibilidades = colunas;
        int incrementador = 0, qual = 0, qtdInserida = 0;


        for(int i=0; i<matrizOperacoes.matriz.length;i++) {
            qtdPossibilidades /= matrizOperacoes.matriz.length - i;
            for (int j = 0; j < colunas; j++)
            {
                for(int y = 0; y<qtdInserida; y++) {
                    if (matrizAuxiliar[y][j] == qual) {
                        qual++;
                        y=-1;
                    }
                    if(qual > matrizOperacoes.matriz.length-1) {
                        qual = 0;
                        y=-1;
                    }
                }
                matrizAuxiliar[i][j] = qual;
                //System.out.print(matrizAuxiliar[i][j] + " ");
                incrementador++;

                if(incrementador == qtdPossibilidades)
                {
                    incrementador = 0;

                    if(qual == matrizOperacoes.matriz.length - 1)
                        qual = 0;
                    else
                        qual++;
                }
            }
            qual = 0;
            //System.out.println();
            qtdInserida++;

        }

    }

}
