public class Resolucao implements Cloneable{
    private double[][] resol;

    public Resolucao(double[][] resolt){
        this.resol = resolt;
    }

    // Obrigatorios Clone,Construtor copia,hashCode, toString, equals
    public String toString()
    {
        String ret = "";
        for(int linhas=0; linhas < resol.length;linhas++) {
            for (int colunas = 0; colunas < resol[linhas].length; colunas++) {
                ret += resol[linhas][colunas] + " ";
            }
            ret += "\n";
        }
        return ret;
    }

    public int hashCode()
    {
        int ret = 1;
        for(int i = 0; i < resol.length;i++)
            for(int j=0; j < resol[i].length;j++)
                ret = ret * 7 + new Double(resol[i][j]).hashCode();
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
        Resolucao aux = (Resolucao)obj;
        if(aux.resol.length != this.resol.length)
            return false;
        for(int i=0; i < this.resol.length;i++) {
            if (aux.resol[i].length != this.resol[i].length)
                return false;
            for (int j = 0; j < this.resol[i].length; j++)
                if (aux.resol[i][j] != this.resol[i][j])
                    return false;
        }
        return true;
    }
    public Resolucao(Resolucao mold) throws Exception
    {
        if(mold == null)
            throw new Exception("Objeto Nulo, o que torna impossivel Clonalo!");

        for(int i=0; i < mold.resol.length;i++)
            for(int j=0; j < mold.resol[i].length;j++)
                this.resol[i][j] = mold.resol[i][j];
    }
    public Object clone()
    {
        Resolucao ret = null;
        try{
            ret = new Resolucao(this);
        }catch (Exception erro){}
        return ret;
    }

    public double[][] getResol() {
        return resol;
    }
}
