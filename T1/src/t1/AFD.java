/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author matiassebastianparra
 */
public class AFD {
    
    Automata automata;
    ArrayList<String> alfabeto;
    ArrayList<String> inicios;
    ArrayList<String> finales;
    Automata afnd;
    ArrayList<Estado> estados_epsilon;
    
    public AFD(Automata automata) {
          
    }

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = new ArrayList();
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
        
        this.afnd = automata;
        estados_epsilon =  new ArrayList<Estado>();
        obtener_eclosure();
    }

            
}
