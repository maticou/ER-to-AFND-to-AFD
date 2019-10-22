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
    
    Estado estado;
    Transicion transicion;

    ArrayList<Estado> listaEstados;
    ArrayList<Transicion> listaTransiciones;
    ArrayList<String> alfabeto;
    ArrayList<String> inicios;
    ArrayList<String> finales;
    Automata afnd;
    Stack<Estado> pila_estados;
    Estado estado_inicial;
    Estado sumidero;
    
    public AFD(Automata automata, ArrayList<String> alfabeto) {
        
        //this.transicion = new Transicion();

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = alfabeto;
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
        this.pila_estados = new Stack();
        
        this.afnd = automata;
        obtener_eclosure();
        
        //se crea el estado inicial para el afd y un estado sumidero
        estado_inicial = new Estado(0, true, false);
        sumidero = new Estado(1, false, false);
        
        estado_inicial.estados.addAll(pila_estados);
        pila_estados.clear();
        
        this.listaEstados.add(estado_inicial);
        this.listaEstados.add(sumidero);
        
        this.pila_estados.push(estado_inicial);
        
        while(this.pila_estados.empty() != true){
            this.generar_estados_afd(this.pila_estados.pop());
        }
        
        for(int i = 0; i< listaEstados.size(); i++){
            listaEstados.get(i).id = i;
        }
        
        imprimir_afd();
    }          
    
    void imprimir_afd(){
        System.out.println("AFD");
        System.out.printf("K = {");
        for(int i=0;i<this.listaEstados.size();i++){            
            if(this.listaEstados.size()-1 == i){
                System.out.printf("q"+listaEstados.get(i));
            }else{
                System.out.printf("q"+listaEstados.get(i)+" ,");
            }            
        }
        System.out.printf("}");
        System.out.println(" ");
        System.out.printf("Sigma = ");       
        System.out.println("Delta");
        
        for(Estado estado: this.listaEstados){
            estado.imprimir_transiciones();
        }
    }
    
    void generar_estados_afd(Estado estado){
        for(String string : this.alfabeto){
            ArrayList<Estado> transiciones =  new ArrayList<Estado>();
            
            for(Estado s : estado.estados ){
                if(s.transiciones.containsKey(string.charAt(0)) == true){
                    for(Estado aux: s.transiciones.get(string.charAt(0))){
                        obtener_transiciones_estado(aux, transiciones);
                    }
                }
            }
            
            if(transiciones.isEmpty() == false){
                Estado nuevo_estado = new Estado(0, false, false);
                nuevo_estado.estados.addAll(transiciones);
                
                estado.agregarTransicion(string.charAt(0), nuevo_estado);
                
                if(verificar_existencia_estado(nuevo_estado) == false){
                    this.listaEstados.add(nuevo_estado);
                    this.pila_estados.push(nuevo_estado);
                }
                
                transiciones.clear();
            }
            else{
                estado.agregarTransicion(string.charAt(0), this.sumidero);
            }
        }
    }
    
    void obtener_transiciones_estado(Estado estado, ArrayList<Estado> transiciones){
        if(transiciones.contains(estado) == false){
            transiciones.add(estado);
            
            if(estado.transiciones.containsKey('Ɛ') == true){
                for(Estado s : estado.transiciones.get('Ɛ')){
                    obtener_transiciones_estado(s, transiciones);
                }
            }   
        }
    }
    
    boolean verificar_existencia_estado(Estado estado){
        for(Estado s: this.listaEstados){
            if(s.estados.containsAll(estado.estados) == true){
                return true;
            }
        }
        
        return false;
    }

    void obtener_eclosure(){
        transicion_epsilon(afnd.inicio);
    }

    void transicion_epsilon(Estado estado){

        if(pila_estados.contains(estado) == false){
            this.pila_estados.push(estado);

            if(estado.transiciones.get('Ɛ') != null){
                for(Estado s: estado.transiciones.get('Ɛ')){
                    transicion_epsilon(s);
                }
            }
        }
    }
}
