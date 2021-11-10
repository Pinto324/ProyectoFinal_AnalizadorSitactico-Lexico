/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PilaYHerramientas;

import Analizador.Tipo;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author branp
 */
public class AutomataDePila {
        private Stack automataPila = new Stack();

    public AutomataDePila() {
        this.automataPila.push(Tipo.FINALIZAR.name());
        this.automataPila.push(Tipo.INICIAR);
    }

    private boolean terminalExistenteEnPila(String tokenEvaluando) {
        return this.automataPila.contains(tokenEvaluando);
    }
    
    public Stack getPila() {
        return automataPila;
    }

    public void setPila(Stack automataPila) {
        this.automataPila = automataPila;
    }
    
    public boolean comprobarExistenciaProduccion(Object object) {
        boolean includidoEnumProduccion = false;
        Tipo[] produccion = Tipo.values();
        for (Tipo producciones : produccion) {
            includidoEnumProduccion = producciones.equals(object);
            if (includidoEnumProduccion) {
                break;
            }
        }
        return includidoEnumProduccion;
    }
    
    public void llenarPila(String palabra, String token) {
        if (palabra != null) {
            if (!palabra.equals("Ɛ")) {
                automataPila.pop();
                switch (palabra) {
                    case "ES":
                        automataPila.push(Tipo.INICIAR);
                        automataPila.push(Tipo.ESCRITURA);
                        break;
                    case "AS":
                        automataPila.push(Tipo.INICIAR);
                        automataPila.push(Tipo.ASIGNACION);
                        break;
                    case "RS":
                        automataPila.push(Tipo.INICIAR);
                        automataPila.push(Tipo.REPETIR);
                        break;
                    case "CS":
                        automataPila.push(Tipo.INICIAR);
                        automataPila.push(Tipo.CONDICINAL);
                        break;
                    case "$":
                        automataPila.push(Tipo.EPSILON);
                        break;
                    case "ESCRIBIR L FIN E":
                        automataPila.push(Tipo.ESCRITURA);
                        automataPila.push("FIN");
                        automataPila.push(Tipo.LEXEMA);
                        automataPila.push("ESCRIBIR");
                        break;   
                    case "Literal":
                        automataPila.push("Literal");
                        break;
                    case "Numero":
                        automataPila.push("Numero");
                        break;
                    case "id":
                        automataPila.push("id");
                        break;
                    case "REPETIR H INICIAR E FIN R":
                        automataPila.push(Tipo.REPETIR);
                        automataPila.push("FIN");
                        automataPila.push(Tipo.ESCRITURA);
                        automataPila.push("INICIAR");
                        automataPila.push(Tipo.TERMINALH);
                        automataPila.push("REPETIR");
                        break;    
                    case "Si B ENTONCES E FIN C":
                        automataPila.push(Tipo.CONDICINAL);
                        automataPila.push("FIN");
                        automataPila.push(Tipo.ESCRITURA);
                        automataPila.push("ENTONCES");
                        automataPila.push(Tipo.VALORBOOLEANO);
                        automataPila.push("SI");
                        break;
                    case "VERDADERO":
                        automataPila.push("VERDADERO");
                        break;
                    case "FALSO":
                        automataPila.push("FALSO");
                        break;
                    case "TX’":
                        automataPila.push(Tipo.PRODUCCIONXP);
                        automataPila.push(Tipo.PRODUCCIONT);
                        break;
                    case "+TX’":
                        automataPila.push(Tipo.PRODUCCIONXP);
                        automataPila.push(Tipo.PRODUCCIONT);
                        automataPila.push("+");
                        break;    
                    case "FT’":
                        automataPila.push(Tipo.PRODUCCIONTP);
                        automataPila.push(Tipo.PRODUCCIONF);
                        break;    
                    case "*FT’":
                        automataPila.push(Tipo.PRODUCCIONTP);
                        automataPila.push(Tipo.PRODUCCIONF);
                        automataPila.push("*");
                        break;
                    case "(X)":
                        automataPila.push(")");
                        automataPila.push(Tipo.PRODUCCIONX);
                        automataPila.push("(");
                        break;    
                    case "id = X FIN A":
                        automataPila.push(Tipo.ASIGNACION);
                        automataPila.push("FIN");
                        automataPila.push(Tipo.PRODUCCIONX);
                        automataPila.push("=");
                        automataPila.push("id");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Estructuras Semanticas no reconocidas por el automata de pila", "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } else {
                if (terminalExistenteEnPila(token)) {
                    while (comprobarExistenciaProduccion(this.automataPila.peek())) {
                        this.automataPila.pop();
                    }
                } else {
                    if (this.automataPila.contains(Tipo.PRODUCCIONXP)) {
                        while (!Tipo.PRODUCCIONXP.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    } else {
                        while (!Tipo.INICIAR.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    }
                }
            }
        } 
    }
}
