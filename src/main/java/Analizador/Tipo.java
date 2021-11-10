
package Analizador;

public enum Tipo{
    //Los tipo representan todos los Resultados, estructuras y terminales posibles
    INICIAR("ESCRIBIR, SI, REPETIR, O ID"),
    ESCRITURA("ESCRIBIR"),
    LEXEMA("LITERAL, ID, O NUMERO"),
    REPETIR("REPETIR"),
    TERMINALH("NUMERO O ID"),
    CONDICINAL("SI"),
    VALORBOOLEANO("VERDADERO O FALSO"),
    PRODUCCIONX("NUMERO, ID, +, * (, ), O FIN"),
    PRODUCCIONXP("NUMERO, ID, +, * (, ), O FIN"),
    PRODUCCIONT("NUMERO, ID, +, * (, ), FIN"),
    PRODUCCIONTP("NUMERO, ID, +, * (, ), FIN"),
    PRODUCCIONF("NUMERO, ID, +, * (, ), O FIN"),
    ASIGNACION("ID"),
    FINALIZAR("FINALIZAR"),
    EPSILON("NUMERO, ID, +, * (, ), O FIN");
   
   private String EEstados;
   
   private Tipo(String EEstados){
       this.EEstados = EEstados;
   }

    public String getEsperarEstados() {
        return EEstados;
    }

    public void setEsperarEstados(String EEstados) {
        this.EEstados = EEstados;
    } 
}
