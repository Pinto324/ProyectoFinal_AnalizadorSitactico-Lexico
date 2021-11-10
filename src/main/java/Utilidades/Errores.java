
package Utilidades;

public class Errores {
    private String Mensaje;
    private String Lexema;
    private Ubicacion Cord;

    public Errores(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getLexema() {
        return Lexema;
    }

    public void setLexema(String Lexema) {
        this.Lexema = Lexema;
    }

    public Ubicacion getCord() {
        return Cord;
    }

    public void setCord(Ubicacion Cord) {
        this.Cord = Cord;
    }    
}
