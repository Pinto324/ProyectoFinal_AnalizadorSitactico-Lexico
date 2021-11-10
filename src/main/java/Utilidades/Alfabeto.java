
package Utilidades;


public class Alfabeto {
    private final char[][] Simbolos;

    public Alfabeto(char[][] Simbolos) {
        this.Simbolos = Simbolos;
    }
    public int getIndex(char ch) {
        for (int i = 0; i < this.Simbolos.length; i++)
            for (int j = 0; j < this.Simbolos[i].length / 2; j++)
                if (ch >= this.Simbolos[i][j * 2] && ch <= this.Simbolos[i][j * 2 + 1])
                    return i;
        return -1;
    }
    public int Tamaño(){
        return Simbolos.length;
    }
}
