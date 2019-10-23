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

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = alfabeto;
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
        this.pila_estados = new Stack();
        
        this.afnd = automata;
        //Se obtienen todos los estados a los que se puede llegar desde el estado 0
        //usando solo transiciones epsilon
        obtener_eclosure();
        
        //se crea el estado inicial para el afd y un estado sumidero
        estado_inicial = new Estado(0, true, false);
        sumidero = new Estado(1, false, false);
        
        estado_inicial.estados.addAll(pila_estados);
        pila_estados.clear();
        
        //se agregan los estados creados prevamente a la lista de estados que representara nuestro AFD
        this.listaEstados.add(estado_inicial);
        this.listaEstados.add(sumidero);
        
        //se agrega el estado inicial a la pila de la cual obtendremos los estados
        //necesarios para calcular los nuevos estados del AFD
        this.pila_estados.push(estado_inicial);
        
        
        while(this.pila_estados.empty() != true){
            this.generar_estados_afd(this.pila_estados.pop());
        }
        
        //se modifican los id de cada estado creado, para que 
        //la funcion delta que se imprimira sea mas entendible.
        for(int i = 0; i< listaEstados.size(); i++){
            listaEstados.get(i).id = i;
        }
        
        imprimir_afd();
    }          
    
    //imprime de manera estandar el AFD
    void imprimir_afd(){
        System.out.println("");
        System.out.println("AFD");
        System.out.printf("K = {");
        for(int i=0;i<this.listaEstados.size();i++){            
            if(this.listaEstados.size()-1 == i){
                System.out.printf("q"+listaEstados.get(i).id);
            }else{
                System.out.printf("q"+listaEstados.get(i).id+" ,");
            }            
        }
        System.out.printf("}");
        System.out.println(" ");
        System.out.printf("Sigma = [ ");           
        for(int n=0;n<this.alfabeto.size();n++){
            if(this.alfabeto.size()-1 == n){
                System.out.println(this.alfabeto.get(n) + " ]");
            }else{
                System.out.printf(this.alfabeto.get(n) + ", ");
            }            
        }
        System.out.println("Delta :");
        
        for(Estado estado: this.listaEstados){
            estado.imprimir_transiciones();
        }
        System.out.println("s = { q" + this.estado_inicial.id + " }");
        System.out.printf("F = { ");
        for(int i=0;i<this.listaEstados.size();i++){
            if(this.listaEstados.get(i).fin){
                if(this.finales.size()-1 == i){
                    System.out.printf("q" + this.finales.get(i));
                }else{
                    System.out.printf("q" + this.finales.get(i) + ",");
                }
            }             
        }
        System.out.printf("}");
        System.out.println("");
    }
    
    //convierte las transiciones de un estado a los estados y transiciones que 
    //se utilizaran para armar el AFD.
    void generar_estados_afd(Estado estado){
        for(String string : this.alfabeto){
            ArrayList<Estado> transiciones =  new ArrayList<Estado>();
            
            //se obtienen todos los estados a los que accede un 
            //estado especifico usando una letra del alfabeto y
            //transiciones epsilon.
            for(Estado s : estado.estados ){
                if(s.transiciones.containsKey(string.charAt(0)) == true){
                    for(Estado aux: s.transiciones.get(string.charAt(0))){
                        obtener_transiciones_estado(aux, transiciones);
                    }
                }
            }
            
            //si el estado tiene transiciones con el caracter especificado
            //se agrega a la lista de estados del AFD y a la pila para que se generen 
            // nuevos estados y trancisiones a traves de este nuevo estado.
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
                //si el estado no tiene transiciones con algun caracter del alfabeto
                // entonces se le agregan transiciones a un estado sumidero usando 
                //el caracter especificado.
                estado.agregarTransicion(string.charAt(0), this.sumidero);
            }
        }
    }
    
    //obtiene todas las transiciones epsilon de un estado especifico y las almacena en 
    //un arraylist que contiene todos los estados que componen un estado del AFD
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
    
    //verifica si es que ya se ha analizado un estado previamente y se agrega 
    // a la lista de estados en caso de que no se haya analizado
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

    //funcion que obtiene los estados a los que se puede llegar desde el estado 0
    //usando solo transiciones epsilon
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
