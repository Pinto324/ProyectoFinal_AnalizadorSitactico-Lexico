/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Analizador.AnalizadorLexico;
import Interfaz.SimbolosYTrans;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author branp
 */
public class ManejadorTablaSimbolos {
    private DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    private DefaultTableModel modelo = new DefaultTableModel();
    
    public void llenarTabla(SimbolosYTrans ventana){
        JTable tabla= ventana.getTablaDeSimbolos();
        this.setModelo(tabla);
        this.setDatos(tabla);
    }
    /**
     *
     * Metodos Set para Llenado de la Tabla de Tokens y llenado de Datos
     */
    public void setModelo(JTable tabla){
        String[] columna= {"Tipo de Token","Lexema","Fila","Columna"};
        modelo.setColumnIdentifiers(columna);
        tabla.setModel(modelo);
    }

    public void setDatos(JTable tabla){
        Object[] datos= new Object[modelo.getColumnCount()];     
        int i=1;
        modelo.setRowCount(0);
        for(int x=0; x< AnalizadorLexico.tokenRecopilado.size()-1;x++){
            datos[0]= AnalizadorLexico.tokenRecopilado.get(x).getLexema();
            datos[1]= nombreParaListado(AnalizadorLexico.tokenRecopilado.get(x).getTipoToken());
            datos[2]= AnalizadorLexico.tokenRecopilado.get(x).getFila();
            datos[3]= AnalizadorLexico.tokenRecopilado.get(x).getColumna();
            JOptionPane.showMessageDialog(null, AnalizadorLexico.tokenRecopilado.get(x).getLexema());
            modelo.addRow(datos);
        }
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(1).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(2).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(3).setCellRenderer(alinear);
        tabla.setModel(modelo);
    }
    /**
     *
     * Tal y Como dice su Nombre, es el Listado identificando las variables
     */
    public String nombreParaListado(String tipoToken) {
        switch (tipoToken) {
            case "id":
                tipoToken = "IDENTIFICADOR";
                break;
            case "ESCRIBIR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "FIN":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "REPETIR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "INICIAR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "SI":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "VERDADERO":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "FALSO":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "ENTONCES":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "=":
                tipoToken = "SIGNO IGUAL";
                break;
            case "+":
                tipoToken = "OPERADOR MATEMÁTICO";
                break;
            case "*":
                tipoToken = "OPERADOR MATEMÁTICO";
                break;
            case "(":
                tipoToken = "AGRUPACION MATEMÁTICO";
                break;
            case ")":
                tipoToken = "AGRUPACION MATEMÁTICO";
                break;
            case "Numero":
                tipoToken = "NUMERO DECIMAL";
                break;    
            case "Comentario":
                tipoToken = "COMENTARIO";
                break;    
            case "Literal":
                tipoToken = "LITERAL";
                break;    
        }
        return tipoToken;
    }
}
