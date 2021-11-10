
package Analizador;

import Main.main;
import Tokens.Simbolos;
import java.util.ArrayList;

public class AnalizadorLexico {
    VerificadorErroresLexicos errores= new VerificadorErroresLexicos();
    public static String Ayudador = "";
    private String texto;
    private Simbolos Simbolo= new Simbolos();
    private final String SALTO = "\n";
    private final String TABULADOR = "\t";
    private final char SIGNOIGUAL = '=';
    private final char DIAGONAL = '/';
    private final char GUIONMEDIO = '-';
    private final char GUIONBAJO = '_';
    private final char COMILLASDOBLES = '"';
    private int fila = 1;
    private int columna = 0;
    private int posicion = 0;
    private int estadoActual;
    private String lexemasReporte = "";
    public static ArrayList<Simbolos> tokenRecopilado = new ArrayList<>();
    private ArrayList<String> lexema = new ArrayList<>();
    private ArrayList<String> nombreToken = new ArrayList<>();
    private int estadoAnterior = 0;
    private int contador=0;
    
    // filas s0 --> 0, s1 -> 1, s2 -> 2, , s3 -> 3, s4 -> 4, s5 -> 5, s6 -> 6, s7 -> 7, s8 -> 8, s9 -> 9, s10 -> 10, s11 -> 11
        // \Letra --> 0
        // \Digito --> 1
        // \Diagonal "/" --> 2
        // \Signos de Puntuacio --> 3
        // \Signos de Agrupacion --> 4
        // \Operador Matemático --> 5
        // \Guion Medio --> 6
        // \Comillas --> 8
        // \Caracteres especiales --> 9
        // \Guion Bajo --> 10
        // \Signo Igual --> 11
        // error --> -1, -2, -3, -4
        // no pertenece a mi alfabeto -1
    int matrizTransicion[][] = new int[12][12];
    { 
        matrizTransicion[0][0] = 3;   matrizTransicion[1][0] = -1;   matrizTransicion[2][0] = 2;   matrizTransicion[3][0] = 3;    matrizTransicion[4][0] = 4;   matrizTransicion[7][1] = 7;
        matrizTransicion[0][1] = 7;   matrizTransicion[1][1] = -1;   matrizTransicion[2][1] = 2;   matrizTransicion[3][1] = 3;    matrizTransicion[4][1] = 4;   matrizTransicion[7][4] = -4;
        matrizTransicion[0][2] = 1;   matrizTransicion[1][2] = 2;    matrizTransicion[2][2] = 2;   matrizTransicion[3][2] = -1;   matrizTransicion[4][2] = 4;
        matrizTransicion[0][3] = -1;  matrizTransicion[1][3] = -1;   matrizTransicion[2][3] = 2;   matrizTransicion[3][3] = -1;   matrizTransicion[4][3] = 4;
        matrizTransicion[0][4] = 10;  matrizTransicion[1][4] = -1;   matrizTransicion[2][4] = 2;   matrizTransicion[3][4] = -4;   matrizTransicion[4][4] = 4;
        matrizTransicion[0][5] = 11;  matrizTransicion[1][5] = -1;   matrizTransicion[2][5] = 2;   matrizTransicion[3][5] = -1;   matrizTransicion[4][5] = 4; 
        matrizTransicion[0][6] = 7;   matrizTransicion[1][6] = -1;   matrizTransicion[2][6] = 2;   matrizTransicion[3][6] = 3;    matrizTransicion[4][6] = 4;
        matrizTransicion[0][7] = 5;   matrizTransicion[1][7] = -1;   matrizTransicion[2][7] = 2;   matrizTransicion[3][7] = 3;    matrizTransicion[4][7] = 4;
        matrizTransicion[0][8] = 4;   matrizTransicion[1][8] = -1;   matrizTransicion[2][8] = 2;   matrizTransicion[3][8] = -1;   matrizTransicion[4][8] = 6;
        matrizTransicion[0][9] = 3;   matrizTransicion[1][9] = 8;    matrizTransicion[2][9] = 2;   matrizTransicion[3][9] = -1;   matrizTransicion[4][9] = 4;
        matrizTransicion[0][10] = 3;  matrizTransicion[1][10] = -1;  matrizTransicion[2][10] = 2;  matrizTransicion[3][10] = 3;   matrizTransicion[4][10] = 4;
        matrizTransicion[0][11] = 9;  matrizTransicion[1][11] = -1;  matrizTransicion[2][11] = 2;  matrizTransicion[3][11] = -1;  matrizTransicion[4][11] = 4;
    }
    
    public AnalizadorLexico() {
        this.asignacionErroresSintacticos();
        this.tipoToken();
        this.analisisLexico();
    }
    
     public static ArrayList<Simbolos> getTokenRecopilado() {
        return tokenRecopilado;
    }
    
    private void tipoToken() {
        this.nombreToken.add("id");
        this.nombreToken.add("Numero");
        this.nombreToken.add("Reservada");
        this.nombreToken.add("Literal");
        this.nombreToken.add("Comentario");
        this.nombreToken.add("Literal");
        this.nombreToken.add("Especial");
        this.nombreToken.add("Igual");
        this.nombreToken.add("Agrupacion");
        this.nombreToken.add("Operador");
        this.nombreToken.add("Error");
    }

    public void pabrasReservadas() {
        for (Simbolos token : tokenRecopilado) {
            for (PalabrasReservadas value : PalabrasReservadas.values()) {
                if (token.getLexema().equals(value.name())) {
                    Simbolo.setTipoToken(value.name());
                }
            }
        }
    }
    private void asignacionErroresSintacticos(){
         for (int i = 0; i < matrizTransicion.length; i++) {
            for (int j = 0; j < matrizTransicion[i].length; j++) {
                if (matrizTransicion[i][j] == 0) {
                    matrizTransicion[i][j] = -1;
                }
            }
            
        }
    }
    
    private int getSiguienteEstado(int estadoActual, int caracter) {
        int resultado = -1;
        if (caracter >= 0 && caracter <= 11) {
            resultado= matrizTransicion[estadoActual][caracter];
        } else if (caracter == -2) {
            resultado = -2;
        } else if (caracter == -3) {
            resultado = -3;
        } else if (caracter == -4) {
            resultado = -4;
        }
        return resultado;
    }

    private boolean esNoAceptcion(char caracter, int estado) {
        boolean esNoAceptacion = false;
        if (estado == 4 || estado == 1) {
            errores.recopilarErroesAnalizador(caracter, -1);
            esNoAceptacion = true;
        }
        return esNoAceptacion;
    }
    
    private boolean getNextToken(int estadoActual, char caracter) {
        boolean seguiLeyendo = true;
        if (Character.isSpaceChar(caracter)) {
            seguiLeyendo = estadoActual == 4 || estadoActual == 2;
        } else if (Character.compare(caracter, SALTO.charAt(0)) == 0) {
            seguiLeyendo = false;
        }
        if (estadoActual == 10 || estadoActual == -4) {
            seguiLeyendo = false;
        }
        return seguiLeyendo;
    }
    
    private boolean reiniciar(int estadoActual) {
        boolean reiniciar = false;
        if (estadoActual == -1 || estadoActual == -2 || estadoActual == -3) {
            reiniciar = true;
        }
        return reiniciar;
    }
    
     public void analisisLexico() {
        this.lexema.clear();
        texto = Ayudador;
        Ayudador = "";
        this.estadoActual = 0;
        char temporal;
        while (posicion < texto.length()) {
            temporal = texto.charAt(posicion);
            int estadoTemporal = getSiguienteEstado(estadoActual, this.transicionesMatriz(estadoActual, temporal));
            errores.recopilarErroesAnalizador(temporal, estadoTemporal);
            this.construccionReporteTokens(temporal, estadoTemporal, texto.length());
            if (estadoTemporal == 10) {
                estadoTemporal = 0;
            }
            this.estadoActual = estadoTemporal;
            if (!getNextToken(estadoActual, temporal) || reiniciar(estadoActual)) {
                estadoActual = 0;
            }
            posicion++;
        }
        this.pabrasReservadas();
        Simbolos tokens = new Simbolos("FINALIZAR", "FINALIZAR", 0, 0);
        tokenRecopilado.add(tokens);
        main.MenuInicial.getAreaDeTexto().setText(main.MenuInicial.getAreaDeTexto().getText());
    }
 
    public void construccionReporteTokens(char caracter, int estado, int lenght) {
        switch (estado) {
            case -3:
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    Simbolos tokens = new Simbolos(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }
                this.fila++;
                this.columna = 0;
                this.lexemasReporte = "";
                break;
            case -2:
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    Simbolos tokens = new Simbolos(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }
                this.columna++;
                this.lexemasReporte = "";
                break;
            case -4:
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    Simbolos tokens = new Simbolos(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                    this.columna++;
                    this.lexemasReporte = "";
                    Simbolos token1 = new Simbolos(nombreToken(10, caracter + ""), caracter + "", fila, columna);
                    tokenRecopilado.add(token1);
                    this.columna++;
                    this.lexemasReporte = "";
                }
                break;
            case 10:
                if (!this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    Simbolos tokens = new Simbolos(nombreToken(10, caracter + ""), caracter + "", fila, columna);
                    tokenRecopilado.add(tokens);
                    this.columna++;
                    this.lexemasReporte = "";
                }
                break;
            default:
                this.columna++;
                this.lexemasReporte += "" + caracter;
                if (this.contador == lenght - 1 && !this.esNoAceptcion(caracter, estado)) {
                    Simbolos tokens = new Simbolos(nombreToken(estado, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }
                break;
        }
        this.contador++;
        this.estadoAnterior = estado;
    }

    private String nombreToken(int estado, String lexemaEncontrado) {
        String token = "";
        switch (estado) {
            case 2:
                token = nombreToken.get(4);
                break;
            case 3:
                token = nombreToken.get(0);
                break;
            case 6:
                token = nombreToken.get(5);
                break;
            case 7:
                token = nombreToken.get(1);
                break;
            case 8:
                token = nombreToken.get(6);
                break;
            case 9:
                token = lexemaEncontrado;
                break;
            case 10:
                token = lexemaEncontrado;
                break;
            case 11:
                token = lexemaEncontrado;
                break;
            default:
                token = nombreToken.get(10);
                break;
        }

        return token;
    }
    
    public int transicionesMatriz(int estadoActual, char caracter) {
        int posicion = -1;
        if (Simbolo.getCaracteresEspeciales().contains(caracter)) {
            if (estadoActual == 3) {
                posicion = 0;
            } else {
                posicion = 9;
            } 
        } else if (Character.isDigit(caracter)) {
            posicion = 1;    
        } else if (Character.compare(caracter, DIAGONAL) == 0) {
            posicion = 2;     
        } else if (Simbolo.getSignosAgrupacion().contains(caracter)) {
            posicion = 4; 
        } else if (Simbolo.getOperadoresMatematicos().contains(caracter)) {
            posicion = 5;
        } else if (Simbolo.getSignosPuntuacion().contains(caracter)) {
            posicion = 3;
        } else if (Character.isSpaceChar(caracter) || Character.compare(caracter, TABULADOR.charAt(0)) == 0) {
            switch (estadoActual) {
                case 4:
                    posicion = 4;
                    break;
                case 2:
                    posicion = 2;
                    break;
                default:
                    posicion = -2;
                    break;
            }    
        } else if (Character.compare(caracter, SALTO.charAt(0)) == 0) {
            posicion = -3;
        } else if (Character.compare(caracter, GUIONMEDIO) == 0) {
            posicion = 6;    
        } else if (Character.compare(caracter, COMILLASDOBLES) == 0) {
            posicion = 8;  
        } else if (Character.isLetter(caracter)) {
            posicion = 0;    
        } else if (Character.compare(caracter, GUIONBAJO) == 0) {
            posicion = 10;
        } else if (Character.compare(caracter, SIGNOIGUAL) == 0) {
            posicion = 11;
        }
        return posicion;
    }
}
