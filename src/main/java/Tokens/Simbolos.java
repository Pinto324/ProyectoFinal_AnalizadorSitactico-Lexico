/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class Simbolos {    
    private String Lexema;
    private String TipoDeToken;
    private String descripcion;
    private ArrayList<Character> SignosAgrupacion;
    private ArrayList<Character> Operadores;
    private ArrayList<Character> CaracterEspecial;
    private ArrayList<Character> SignosPuntuacion;
    private int fila;
    private int columna;

    public Simbolos() {
        this.agrupacionTokens();
    }
    /**
    *
    * Metodos Get & Set de los distintos Tipos de Tokens
    */
    public ArrayList<Character> getSignosAgrupacion() {
        return SignosAgrupacion;
    }

    public void setSignosAgrupacion(ArrayList<Character> signosAgrupacion) {
        this.SignosAgrupacion = signosAgrupacion;
    }

    public ArrayList<Character> getOperadoresMatematicos() {
        return Operadores;
    }

    public void setOperadoresMatematicos(ArrayList<Character> operadoresMatematicos) {
        this.Operadores = operadoresMatematicos;
    }

    public ArrayList<Character> getCaracteresEspeciales() {
        return CaracterEspecial;
    }

    public void setCaracteresEspeciales(ArrayList<Character> caracteresEspeciales) {
        this.CaracterEspecial = caracteresEspeciales;
    }

    public ArrayList<Character> getSignosPuntuacion() {
        return SignosPuntuacion;
    }

    public void setSignosPuntuacion(ArrayList<Character> signosPuntuacion) {
        this.SignosPuntuacion = signosPuntuacion;
    }

    public String getLexema() {
        return Lexema;
    }

    public String getTipoToken() {
        return TipoDeToken;
    }
    
    public void setTipoToken(String tipoToken) {
        this.TipoDeToken = tipoToken;
    }
    public int getFila() {
        return fila;
    }

   public int getColumna() {
        return columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     /**
    *
    * Constructor de simbolos
    */
      public Simbolos(String tipoToken, String lexema, int fila, int columna) {
        this.Lexema = lexema;
        this.TipoDeToken = tipoToken;
        this.fila = fila;
        this.columna = columna;
    }
    
    private void agrupacionTokens() {
        tiposSignosAgrupacion();
        tiposOperadoresMatematicos();
        tiposSignosPuntuacion();
        tiposCaractereesEspeciales();
    }
     
     /**
    *
    * Metodos de Tokens
    */
    public void tiposCaractereesEspeciales() {
        this.CaracterEspecial = new ArrayList<>();
        this.CaracterEspecial.add('t');
        this.CaracterEspecial.add('f');
        this.CaracterEspecial.add('n');
        this.CaracterEspecial.add('r');    
    }

    private void tiposSignosAgrupacion() {
        SignosAgrupacion = new ArrayList<>();
        this.SignosAgrupacion.add(')');
        this.SignosAgrupacion.add('(');       
    }

    private void tiposSignosPuntuacion() {
        SignosPuntuacion = new ArrayList<>();
        this.SignosPuntuacion.add('.');
        this.SignosPuntuacion.add(':');
        this.SignosPuntuacion.add(';');
        this.SignosPuntuacion.add(',');
        this.SignosPuntuacion.add('<');
        this.SignosPuntuacion.add('<');
        this.SignosPuntuacion.add('‘');
        this.SignosPuntuacion.add('\'');
        this.SignosPuntuacion.add('>');
    }

    private void tiposOperadoresMatematicos() {
        Operadores = new ArrayList<>();
        this.Operadores.add('*');
        this.Operadores.add('%');
        this.Operadores.add('+');
    }
}
