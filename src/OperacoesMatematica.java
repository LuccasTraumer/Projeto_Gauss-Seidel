public class OperacoesMatematica {


    public static int Fatoracao (int qtd)
    {
        int valor = 1;
        for(int i=qtd; i>1; i--)
        {
            valor *= i;
        }
        return valor;
    }

}
