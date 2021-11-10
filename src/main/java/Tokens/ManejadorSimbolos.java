
package Tokens;

import Analizador.Tipo;
import java.util.ArrayList;


public class ManejadorSimbolos {
    private ArrayList<Descripcion> DescripcionDeSimbolos = new ArrayList<>();
    private final ArrayList<Simbolos> Simbolos = new ArrayList<>();
    private boolean recolector = false;
    /**
     *
     * Metodos para la Obtencion y Creacion de la Tabla
     */
    public int valorDeSimbolo(String token) {
        int valor = 0;
        try {
            valor = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            for (Descripcion Descripcion : DescripcionDeSimbolos) {
                if (Descripcion.getNombre().equals(token)) {
                    valor = Descripcion.getValor();
                    break;
                }
            }
        }
        return valor;         
    }

    private void LlenarSimbolo(Simbolos Nuevo) {
        Simbolos.add(Nuevo);
    }
    
    private ArrayList<String> Reasignar(ArrayList<String> valores, int I, int F) {
        ArrayList<String> auxiliar = new ArrayList<>();
        for (int i = 0; i < I + 1; i++) {
            auxiliar.add(valores.get(i));
        }
        for (int i = F + 1; i < valores.size(); i++) {
            auxiliar.add(valores.get(i));
        }
        return auxiliar;
    }
    
    private ArrayList<String> LexemasDeTokens() {
        int index = 0;
        ArrayList<String> lexemas = new ArrayList<>();
        for (Simbolos token : Simbolos) {
            if (!token.getLexema().equalsIgnoreCase("=") && index != 0) {
                lexemas.add(token.getLexema());
            }
            index++;
        }
        return lexemas;
    }
    
    public void recolectorAsignaciones(Tipo produccion, Simbolos token) {
        if (produccion.equals(produccion.ASIGNACION) && token.getTipoToken().equalsIgnoreCase("id")) {
            recolector = true;
        }
        if (recolector) {
            if (token.getTipoToken().equalsIgnoreCase("FIN")) {
                calcularValorSimbolo();
                recolector = false;
            } else {
                LlenarSimbolo(token);
            }
        }
    }
    private ArrayList<String> operacionLinealMultiplicidad(ArrayList<String> valores) {
        int producto = 0;
        while (valores.contains("*")) {
            for (int i = 0; i < valores.size(); i++) {
                if (valores.get(i).equalsIgnoreCase("*")) {
                    producto = valorDeSimbolo(valores.get(i + 1)) * valorDeSimbolo(valores.get(i - 1));
                    valores.set(i, producto + "");
                    valores.remove(i + 1);
                    valores.remove(i - 1);
                    break;
                }
            }
        }
       return valores;
    }

    private ArrayList<String> operacionlinealSuma(ArrayList<String> valores) {
        int suma = 0;
        for (String valor : valores) {
            suma += valorDeSimbolo(valor);
        }
        valores.clear();
        valores.add(suma + "");
        return valores;
    }
    
    private ArrayList<String> operacionesconParentesis(ArrayList<String> valores) {
        if (valores.contains("(")) {
            while (valores.contains("(")) {
                int temI = 0;
                int temF = 0;
                ArrayList<String> auxiliar = new ArrayList<>();
                for (int i = 0; i < valores.size(); i++) {
                    if (valores.get(i).equalsIgnoreCase("(")) {
                        temI = i;
                    }
                }
                for (int i = temI; i < valores.size(); i++) {
                    if (valores.get(i).equalsIgnoreCase(")")) {
                        temF = i;
                        break;
                    }
                }
                for (int i = temI + 1; i < temF; i++) {
                    auxiliar.add(valores.get(i));
                }
                auxiliar = operacionLinealMultiplicidad(auxiliar);
                auxiliar = operacionlinealSuma(auxiliar);
                valores.set(temI, auxiliar.get(0));
                valores = this.Reasignar(valores, temI, temF);
            }
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        } else {
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        }
        return valores;
    }
    
    
    public void calcularValorSimbolo() {
        ArrayList<String> valores = LexemasDeTokens();
        valores = this.operacionesconParentesis(valores);    
        Descripcion DesSimbolos = new Descripcion();
        DesSimbolos.setNombre(Simbolos.get(0).getLexema());
        DesSimbolos.setValor(Integer.parseInt(valores.get(0)));
        DescripcionDeSimbolos.add(DesSimbolos);
        Simbolos.clear();
    }
}
