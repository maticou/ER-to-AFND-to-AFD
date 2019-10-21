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
public class Transicion {

    Estado estadoInicio; 
    Estado estadoFin;    
    String elemento;     
    
    public Transicion(int id1, int id2, String elemento) {
        
        this.estadoInicio = new Estado(id1);
        this.estadoFin = new Estado(id2);
        this.elemento = elemento;        
    }

    public Estado getEstadoInicio() {
        return estadoInicio;
    }

    public void setEstadoInicio(Estado estadoInicio) {
        this.estadoInicio = estadoInicio;
    }

    public Estado getEstadoFin() {
        return estadoFin;
    }

    public void setEstadoFin(Estado estadoFin) {
        this.estadoFin = estadoFin;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }        
            
}
