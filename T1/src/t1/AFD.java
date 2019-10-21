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
public class AFD {
    
    Estado estado;
    Transicion transicion;
    
    ArrayList<Estado> listaEstados;
    ArrayList<Transicion> listaTransiciones;
    ArrayList<String> alfabeto;
    ArrayList<String> inicios;
    ArrayList<String> finales;
        
    public AFD() {
          
        //this.transicion = new Transicion();

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = new ArrayList();
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
    }

            
}
