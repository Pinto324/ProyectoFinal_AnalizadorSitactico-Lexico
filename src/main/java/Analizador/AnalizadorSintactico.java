/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import PilaYHerramientas.AutomataDePila;
import PilaYHerramientas.Constructor;
import PilaYHerramientas.Terminales;
import Tokens.Simbolos;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author branp
 */
public class AnalizadorSintactico {
    private AutomataDePila automataPila = new AutomataDePila();
    private Tipo Estados;
    private final Terminales alfabeto = new Terminales();
    private Simbolos token;
    private ArrayList<Simbolos> tokens = new ArrayList<>();
    private Constructor funciones = new Constructor();
    private final ArrayList<Simbolos> tokensError = new ArrayList<>();
    private String matrizEstadosSintacticos[][] = new String[13][16];
    {
        matrizEstadosSintacticos[0][0] = "ES";  matrizEstadosSintacticos[1][0] = "ESCRIBIR L FIN E";  matrizEstadosSintacticos[2][2] = "Literal";
        matrizEstadosSintacticos[0][4] = "AS";  matrizEstadosSintacticos[1][1] = "Ɛ";                 matrizEstadosSintacticos[2][3] = "Numero";
        matrizEstadosSintacticos[0][5] = "RS";  matrizEstadosSintacticos[1][4] = "Ɛ";                 matrizEstadosSintacticos[2][4] = "id";
        matrizEstadosSintacticos[0][7] = "CS";  matrizEstadosSintacticos[1][5] = "Ɛ";
        matrizEstadosSintacticos[0][15] = "Ɛ";  matrizEstadosSintacticos[1][7] = "Ɛ";
                                                matrizEstadosSintacticos[1][15] = "Ɛ";
       
        matrizEstadosSintacticos[3][0] = "Ɛ";                          matrizEstadosSintacticos[4][3] = "Numero";
        matrizEstadosSintacticos[3][4] = "Ɛ";                          matrizEstadosSintacticos[4][4] = "id";
        matrizEstadosSintacticos[3][5] = "REPETIR H INICIAR E FIN R";
        matrizEstadosSintacticos[3][7] = "Ɛ";
        matrizEstadosSintacticos[3][15] = "Ɛ";

        matrizEstadosSintacticos[5][0] = "Ɛ";                      matrizEstadosSintacticos[6][9] = "VERDADERO";
        matrizEstadosSintacticos[5][4] = "Ɛ";                      matrizEstadosSintacticos[6][10] = "FALSO";
        matrizEstadosSintacticos[5][5] = "Ɛ";
        matrizEstadosSintacticos[5][7] = "Si B ENTONCES E FIN C";
        matrizEstadosSintacticos[5][15] = "Ɛ";
        
        matrizEstadosSintacticos[7][3] = "TX’";             matrizEstadosSintacticos[8][1] = "Ɛ";
        matrizEstadosSintacticos[7][4] = "TX’";             matrizEstadosSintacticos[8][11] = "+TX’";
        matrizEstadosSintacticos[7][13] = "TX’";            matrizEstadosSintacticos[8][14] = "Ɛ";
                                                            matrizEstadosSintacticos[8][15] = "Ɛ";
                                                            matrizEstadosSintacticos[9][3] = "FT’";
        
        matrizEstadosSintacticos[9][4] = "FT’";        matrizEstadosSintacticos[11][3] = "Numero";
        matrizEstadosSintacticos[9][13] = "FT’";       matrizEstadosSintacticos[11][4] = "id";
                                                       matrizEstadosSintacticos[11][13] = "(X)";
        
        matrizEstadosSintacticos[10][1] = "Ɛ";         matrizEstadosSintacticos[12][0] = "Ɛ";
        matrizEstadosSintacticos[10][11] = "Ɛ";        matrizEstadosSintacticos[12][4] = "id = X FIN A";
        matrizEstadosSintacticos[10][12] = "*FT’";     matrizEstadosSintacticos[12][5] = "Ɛ";
        matrizEstadosSintacticos[10][14] = "Ɛ";        matrizEstadosSintacticos[12][6] = "Ɛ";
        matrizEstadosSintacticos[10][15] = "Ɛ";        matrizEstadosSintacticos[12][15] = "Ɛ";
    }

    public void analisisSintactico(ArrayList<Simbolos> tokens) {
        this.tokens = this.limpiarTokens(tokens);
        int index = 0;
        while (!this.automataPila.getPila().empty() && index < this.tokens.size()) {
            if (this.token == null) {
                this.token = this.tokens.get(index);
                index++;
            }
            while (comprobarEnum(this.automataPila.getPila().peek())) {
                this.Estados = (Tipo) this.automataPila.getPila().peek();
                String valorMatriz = matrizEstadosSintacticos[this.alfabeto.getEstado(Estados)][this.alfabeto.getValorTerminal(this.token.getTipoToken())];
                if (valorMatriz == null) {
                    String descripciondeError = "El Analizador Sintactico esperaba un tipo de Token " + Estados.getEsperarEstados();
                    capturaErrorSintactico(token, descripciondeError);
                    token = null;
                    break;
                } else {
                    this.automataPila.llenarPila(valorMatriz, this.token.getTipoToken());
                }
            }
            if (!comprobarEnum(this.automataPila.getPila().peek()) && this.token != null) {
                if (token.getTipoToken().equalsIgnoreCase((String) this.automataPila.getPila().peek())) {
                    this.funciones.recolectorFucionesSalida(Estados, token);
                    this.automataPila.getPila().pop();
                    this.token = null;
                } else {
                    String descriondeError = "El Analizador Sintactico esperaba un tipo de Token " + this.automataPila.getPila().peek();
                    capturaErrorSintactico(token, descriondeError);
                    this.token = null;
                }
            }
        }
    }
      public ArrayList<Simbolos> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Simbolos> tokens) {
        this.tokens = tokens;
    }
    
    public boolean existenciaError() {
        return !this.tokensError.isEmpty();
    }

    public Constructor getFunciones() {
        return funciones;
    }
    
    public void capturaErrorSintactico(Simbolos token, String descripcion){
        token.setDescripcion(descripcion);
        this.tokensError.add(token);
     }
    
    private ArrayList<Simbolos> limpiarTokens(ArrayList<Simbolos> tokens) {
        ArrayList<Simbolos> tokenporAnallizar = new ArrayList<>();
        for (Simbolos token : tokens) {
            if (!token.getTipoToken().equalsIgnoreCase("Comentario") && !token.getTipoToken().equalsIgnoreCase("Especial")) {
                tokenporAnallizar.add(token);
            }
        }
        return tokenporAnallizar;
    }

    private boolean comprobarEnum(Object object) {
        boolean esEnum = false;
        Tipo[] produccion = Estados.values();
        for (Tipo producciones : produccion) {
            esEnum = producciones.equals(object);
            if (esEnum) {
                break;
            }
        }
        return esEnum;
    }

    public void enlistarErrores(JTextArea area){
        area.setText("");
        for (Simbolos token : tokensError) {
            area.append("Error sintactico en el lexema: "+ token.getLexema()+ " .El cual esta ubicado en la posicion: Fila "+ token.getFila() +" Columna: "+ token.getColumna()+"\n");
            area.append("Descripcion Error: "+ token.getDescripcion()+"\n");
        }
    }
}
