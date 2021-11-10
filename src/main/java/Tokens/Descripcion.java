/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

/**
 *
 * @author branp
 */
public class Descripcion {
    private String Nombre;
    private int Valor;
    
    /**
    *
    * Gets y setds
    */
    public int getValor() {
        return Valor;
    }
    
    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Descripción de Simbolo{" + "Valor=" + Valor + ", Nombre=" + Nombre + '}';
    }
}
