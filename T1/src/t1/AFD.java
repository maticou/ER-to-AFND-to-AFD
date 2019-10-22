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
    Automata afnd;
    ArrayList<Estado> estados_epsilon;
    
    public AFD(Automata automata) {
          
        //this.transicion = new Transicion();

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = new ArrayList();
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
        
        this.afnd = automata;
        estados_epsilon =  new ArrayList<Estado>();
        obtener_eclosure();
    }
    
    void convertir_automata(){
        
    }
    
    void obtener_eclosure(){
        transicion_epsilon(afnd.inicio);
    }
    
    void transicion_epsilon(Estado estado){
        
        if(estados_epsilon.contains(estado) == false){
            this.estados_epsilon.add(estado);
            
            if(estado.transiciones.get('Ɛ') != null){
                for(Estado s: estado.transiciones.get('Ɛ')){
                    transicion_epsilon(s);
                }
            }
        }
    }

            
}
