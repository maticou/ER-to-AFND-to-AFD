/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author matiassebastianparra
 */
public class Estado {
    int id;
    boolean inicio;
    boolean fin;
    //lista de estados que componen el nuevo estado del AFD
    ArrayList<Estado> estados;
    
    HashMap<Character, ArrayList<Estado>> transiciones;
    
    Estado(int id, boolean inicio, boolean fin){
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        transiciones = new HashMap<Character, ArrayList<Estado>>();
        estados = new ArrayList<Estado>();
    }
    
    void setID( int id){
        this.id = id;
    }
    
    void setInicio(boolean inicio){
        this.inicio = inicio;
    }
    
    void setFin(boolean fin){
        this.fin = fin;
    }
    
    void agregarTransicion(char c, Estado nuevo_estado){
        if(transiciones.containsKey(c) == false){
            ArrayList<Estado> estados = new ArrayList<Estado>();
            estados.add(nuevo_estado);
            this.transiciones.put(c, estados);
        }
        else{
            this.transiciones.get(c).add(nuevo_estado);
        }
    }
    
    void imprimir_transiciones(){
        for (Character i : transiciones.keySet()) {
            for(Estado estado: transiciones.get(i)){
                System.out.println("{"+" q"+ this.id+ ", " + i + "," + "q" +estado.id + " }");
            }
        }
    }
    
    //si el conjunto de estados que compone el estado del AFD
    //contiene un estado final del AFND, entonces el estado pasa a ser un
    //estado final.
    void verificar_estado_final(){
        for(Estado estado : this.estados){
            if(estado.fin == true){
                this.fin = true;
            }
        }
    }
}
