
package Utilidades;

import Analizador.Token;

public class ManejadorDeErrores extends Exception {
    private final Errores TokenInvalido;
    private final Token TokenValido;

    public ManejadorDeErrores(Errores TokenInvalido) {
        this.TokenInvalido = TokenInvalido;
        this.TokenValido = null;
    }

    public ManejadorDeErrores(Token TokenValido) {
        this.TokenValido = TokenValido;
        this.TokenInvalido = null;
    }

    public Errores getTokenInvalido() {
        return TokenInvalido;
    }

    public Token getTokenValido() {
        return TokenValido;
    }
    public String Mensaje() {
        String Motivo = this.TokenInvalido.getMensaje();
        int Col = this.TokenInvalido.getCord().getCol();
        int Fila = this.TokenInvalido.getCord().getRow();
        return Motivo + "Error Encontrado en la columna " + Col + ",en la fila " + Fila + ".";
    }
}
