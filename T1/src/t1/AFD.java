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
    ArrayList<Integer> listaEstados;
    ArrayList<Integer> listaTransiciones1;
    ArrayList<String> listaTransiciones2;
    ArrayList<Integer> listaTransiciones3;
    Integer[][] tabla;
        
    public AFD(ArrayList<String> alfabeto, ArrayList<Integer> listaEstados) {
        this.automata = new Automata();
        this.alfabeto = alfabeto;
        this.alfabeto.add("Ɛ");
        this.listaEstados = listaEstados; 
        this.listaTransiciones1 = new ArrayList();
        this.listaTransiciones2 = new ArrayList();
        this.listaTransiciones3 = new ArrayList();
        this.tabla = new Integer[this.listaEstados.size()][this.alfabeto.size()];
        
        for(int t=0;t<this.tabla.length;t++){
            for(int a=0;a<this.tabla[t].length;a++){
                this.tabla[t][a] = this.tabla.length;
            }
        }
          
    }

    public void transformarAFND_AFD(Automata afnd){    
        int contadorEstados = 1;
        for(int i=0;i<afnd.estados.size();i++){
            for (Character c : afnd.estados.get(i).transiciones.keySet()) {
                for(Estado estado: afnd.estados.get(i).transiciones.get(c)){
                    listaTransiciones1.add(afnd.estados.get(i).id);                    
                    listaTransiciones2.add(String.valueOf(c));                    
                    listaTransiciones3.add(estado.id);                    
                }
            }           
        }    
        
        for(int t=0;t<this.tabla.length;t++){
            for(int a=0;a<this.tabla[t].length;a++){
                String letra = this.alfabeto.get(a);
                if(this.listaTransiciones1.indexOf(t) == this.listaTransiciones2.indexOf(letra) ){
                    this.tabla[t][a] = contadorEstados;                   
                    contadorEstados++;
                }
            }
        }
        System.out.println("Matriz a transformar");
        System.out.println("");
        System.out.printf("  ");
        for(int w=0;w<this.alfabeto.size();w++){
            System.out.printf(this.alfabeto.get(w) + " ");
        }
        System.out.println("");
        for (int i = 0; i < tabla.length; i++) {
            System.out.printf(i + " ");
            for (int j = 0; j < tabla[i].length; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
