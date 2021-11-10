
package Analizador;

import Utilidades.Ubicacion;

public class Token {
    private final String lexema;
    private final Ubicacion cord;
    private final Tipo tipo;
 

    public Token(Tipo tipo, String lexema, Ubicacion coor) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.cord = coor;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getLexema() {
        return this.lexema;
    }

    public Ubicacion getCoordenas() {
        return this.cord;
    }

    @Override
    public String toString() {
        return this.lexema;
    }
}
