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

    Estado inicio;
    ArrayList<Estado> finales;
    ArrayList<Estado> estados;
    
    public Automata() {        
        finales = new ArrayList<Estado>();
        estados = new ArrayList<Estado>();
    }    

    void setEstadoInicio(Estado inicio){
        this.inicio = inicio;
    }
    
    void agregarEstado(Estado estado){
        this.estados.add(estado);
    }
    
    void agregarEstadoFinal(Estado estado){
        this.finales.add(estado);
    }
}
