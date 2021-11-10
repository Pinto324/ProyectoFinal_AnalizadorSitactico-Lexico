/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class VerificadorErroresLexicos {
    public static ArrayList<String> cadena = new ArrayList<>();
    public static ArrayList<Character> caracter = new ArrayList<>();
    public static ArrayList<Integer> fila = new ArrayList<>();
    public static ArrayList<Integer> columna = new ArrayList<>();
    private int filaActual = 1;
    private int columnaActual = 0;
    private String cadenaActual = "";
    public static boolean existenciaErrores = false;
    
    /**
     *
     * Metodo Utilizado para la Recopilacion y Verificacion si hay errores en el Analizis
     */
    public void recopilarErroesAnalizador(char caracter, int estado) {
        if (estado == -3) {
            this.filaActual++;
            this.columnaActual = 0;
            this.cadenaActual = "";
        } else {
            if (estado == -1) {
                this.existenciaErrores = true;
                this.columnaActual++;
                this.cadenaActual += caracter;
                this.caracter.add(caracter);
                this.cadena.add(cadenaActual);
                this.fila.add(this.filaActual);
                this.columna.add(this.columnaActual);
                this.cadenaActual = "";
            } else {
                this.columnaActual++;
                if (estado == -2) {
                    this.cadenaActual = "";
                } else {
                    this.cadenaActual += caracter;
                }
            }
        }       
    }

    public boolean existenciaErrores() {
        return existenciaErrores;
    }
}
