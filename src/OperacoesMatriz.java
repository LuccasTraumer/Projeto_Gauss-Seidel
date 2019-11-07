public class OperacoesMatriz {
    private Matriz matrizOperacoes;
    private int[][] matrizAuxiliar;
    private int[] vetorIndiceCorreto;
    Verificar verificar;

    public OperacoesMatriz(Matriz matriz){
        this.matrizOperacoes = matriz;
        verificar = new Verificar();
        this.matrizDePossibilidades();
        if(verificar.temZerosDiagonal(matrizOperacoes.matriz))
           this.testarLinhas();
            //this.matrizDePossibilidades();

        validarEquacoesEquivalentes();

        // dividir os valores
    }

    private void testarLinhas()
    {
        this.vetorIndiceCorreto = new int[matrizOperacoes.matriz.length];
        int contador = 0;
        int colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.matriz.length);
        for(int j = 0; j < colunas ;j++)
        {
            if(vetorIndiceCorreto.length == contador)
                break;
            for(int i= 0; i <matrizOperacoes.matriz.length;i++)
            {
                if(verificar.temZeroDiagonal(matrizOperacoes.matriz[this.matrizAuxiliar[i][j]][i]))
                {
                    contador =0;
                    i = matrizOperacoes.matriz.length;
                }
                else {
                    vetorIndiceCorreto[i] = matrizAuxiliar[i][j];
                    contador++;
                }
                }
        }
        for(int i = 0; i < vetorIndiceCorreto.length;i++)
            System.out.println(vetorIndiceCorreto[i]);
    }

    public void validarEquacoesEquivalentes() {
        System.out.println("-----------------validarEquacoesEquivalentes");

        double[][] matrizTemp = new double[matrizOperacoes.qtdExpressao][matrizOperacoes.qtdExpressao];

        for (int j = 0; j < matrizOperacoes.qtdExpressao; j++) {
            int z = 0;
            for (int i = 0; i < matrizOperacoes.qtdExpressao; i++) {

                for (int ii = i + 1; ii < matrizOperacoes.qtdExpressao; ii++) {
                    System.out.println(matrizOperacoes.matriz[i][j] + " / " + (matrizOperacoes.matriz[ii][j]) + " = " + (matrizOperacoes.matriz[i][j] == 0 || matrizOperacoes.matriz[ii][j] == 0 ? 0 : matrizOperacoes.matriz[i][j] / matrizOperacoes.matriz[ii][j]) + " | i:" + i + " j:" + j + " ii:" + ii + " - Z:" + z);

                    if (matrizOperacoes.matriz[i][j] == 0 || matrizOperacoes.matriz[ii][j] == 0) {
                        matrizTemp[z][j] = 0;
                    } else {
                        matrizTemp[z][j] = matrizOperacoes.matriz[i][j] / matrizOperacoes.matriz[ii][j];
                    }
                    z++;
                }
            }
            System.out.println();
        }

        for(int i = 0; i<matrizTemp.length; i++)
        {
            for(int j=0;j<matrizTemp.length;j++)
                System.out.print( matrizTemp[i][j]  + " ");

            System.out.println();
        }

    }


        protected void matrizDePossibilidades()
        {
            int colunas = OperacoesMatematica.Fatoracao(matrizOperacoes.matriz.length);
            matrizAuxiliar = new int[matrizOperacoes.qtdExpressao][colunas];

            int qtdPossibilidades = colunas;
            int incrementador = 0, qual = 0, qtdInserida = 0;


            for (int i = 0; i < matrizOperacoes.matriz.length; i++) {
                qtdPossibilidades /= matrizOperacoes.matriz.length - i;
                for (int j = 0; j < colunas; j++) {
                    for (int y = 0; y < qtdInserida; y++) {
                        if (matrizAuxiliar[y][j] == qual) {
                            qual++;
                            y = -1;
                        }
                        if (qual > matrizOperacoes.matriz.length - 1) {
                            qual = 0;
                            y = -1;
                        }
                    }
                    matrizAuxiliar[i][j] = qual;
                    System.out.print(matrizAuxiliar[i][j] + " ");
                    incrementador++;

                    if (incrementador == qtdPossibilidades) {
                        incrementador = 0;

                        if (qual == matrizOperacoes.matriz.length - 1)
                            qual = 0;
                        else
                            qual++;
                    }
                }
                qual = 0;
                System.out.println();
                qtdInserida++;

            }

        }
    }

