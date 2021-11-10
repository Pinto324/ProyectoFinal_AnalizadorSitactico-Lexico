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
        RellenarArreglos();
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
    
     /**
    *
    * rellenar arreglos
    */
    public void RellenarArreglos() {
        //rellenando caracteres especiales
        CaracterEspecial = new ArrayList<>();
        CaracterEspecial.add('t');
        CaracterEspecial.add('f');
        CaracterEspecial.add('n');
        CaracterEspecial.add('r');    
        //Rellenando Signos de agrupacion
        SignosAgrupacion = new ArrayList<>();
        SignosAgrupacion.add(')');
        SignosAgrupacion.add('(');  
        //rellenando Signos de puntuacion:
        SignosPuntuacion = new ArrayList<>();
        SignosPuntuacion.add('.');
        SignosPuntuacion.add(':');
        SignosPuntuacion.add(';');
        SignosPuntuacion.add(',');
        SignosPuntuacion.add('<');
        SignosPuntuacion.add('<');
        SignosPuntuacion.add('‘');
        SignosPuntuacion.add('\'');
        SignosPuntuacion.add('>');
        //rellenando operadores matematicos:
        Operadores = new ArrayList<>();
        Operadores.add('*');
        Operadores.add('%');
        Operadores.add('+');
        
    }
}
