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
    
    public Transicion(Estado estadoInicio, Estado estadoFin, String elemento) {
        
        this.estadoInicio = estadoInicio;
        this.estadoFin = estadoFin;
        this.elemento = elemento;        
    }

}
