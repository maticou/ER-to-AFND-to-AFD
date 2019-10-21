/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.ArrayList;

/**
 *
 * @author matiassebastianparra
 */
public class Automata {
    
    String identificador;
    Estado estadoInicial;
    Estado estadoFin;  
    ArrayList<Transicion> listaTransiciones;

    public Automata(String identificador, int estadoInicial, int estadoFin, ArrayList<Transicion> listaTransiciones) {
        
        this.identificador = identificador;        
        this.estadoInicial = new Estado(estadoInicial);
        this.estadoFin = new Estado(estadoFin);
        this.listaTransiciones = new ArrayList();
    }
    
    
}
