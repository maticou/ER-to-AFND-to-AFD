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
public class AFND {
    Automata automata;
    Stack<Automata> pila = new Stack();
    String expresion;
    
    AFND(String expresion){
        this.expresion = expresion;
        this.generar_automata();
    }
    
    void generar_automata(){
        for(char c: expresion.toCharArray()){
            switch(c){
                case '.':
                    this.generar_automata_concatenacion();
                    break;
                case '|':
                    this.generar_automata_union();
                    break;
                case '*':
                    this.generar_automata_kleene();
                    break;
                default:
                    generar_automata_basico(c);
                    break;
            }
        }
        
        this.automata = this.pila.pop();
    }
    
    void imprimir_automata(){
        System.out.println("AFND");
        System.out.printf("K = {");
        for(Estado estado: this.automata.estados){
            System.out.printf("q"+estado.id+" ,");
        }
        System.out.printf("}");
        System.out.println(" ");
        System.out.println("Delta");
        
        for(Estado estado: this.automata.estados){
            estado.imprimir_transiciones();
        }
    }
    
    void generar_automata_basico(char c){
        Automata basico =  new Automata();
        Estado estado1 =  new Estado(0, true, false);
        Estado estado2 =  new Estado(1, false, true);
        
        estado1.agregarTransicion(c, estado2);
        
        basico.agregarEstado(estado1);
        basico.agregarEstado(estado2);
        
        basico.setEstadoInicio(estado1);
        basico.agregarEstadoFinal(estado2);
        
        this.pila.push(basico);
    }
    
    void generar_automata_union(){
        Automata automata2 = this.pila.pop();
        Automata automata1 = this.pila.pop();
        Automata union =  new Automata();
        
        Estado inicio = new Estado(0, true, false);
        Estado fin = new Estado(0, false, true);
        
        inicio.agregarTransicion('Ɛ', automata1.inicio);
        inicio.agregarTransicion('Ɛ', automata2.inicio);
        
        automata1.finales.get(0).agregarTransicion('Ɛ', fin);
        automata2.finales.get(0).agregarTransicion('Ɛ', fin);
        
        //se agregan los estados que conformaran el nuevo automata 
        //para luego modificar sus id
        union.agregarEstado(inicio);
        
        for(Estado estado: automata1.estados){
            union.agregarEstado(estado);
        }
        
        for(Estado estado: automata2.estados){
            union.agregarEstado(estado);
        }
        
        union.agregarEstado(fin);
        
        //se actualizan los id
        for(int i = 0; i < union.estados.size(); i++){
            union.estados.get(i).id = i;
        }
        
        this.pila.push(union);
    }
    
    void generar_automata_concatenacion(){
        Automata automata1;
        Automata automata2;
        Automata concatenacion =  new Automata();
        
        automata2 = this.pila.pop();
        automata1 = this.pila.pop();
        
        //se crea la transicion entre los dos automatas.
        automata1.finales.get(0).agregarTransicion('Ɛ', automata2.inicio);
        
        //se agregan todos los estados al automata de concatenacion.
        for(Estado estado: automata1.estados){
            concatenacion.agregarEstado(estado);
        }
        
        for(Estado estado: automata2.estados){
            concatenacion.agregarEstado(estado);
        }
        
        //se definen los estados de inicio y fin del nuevo automata
        concatenacion.setEstadoInicio(automata1.inicio);
        concatenacion.finales.addAll(automata2.finales);
        
        //se actualizan los id de los estados para el nuevo automata generado
        for(int i = 0; i < concatenacion.estados.size(); i++){
            concatenacion.estados.get(i).id = i;
        }
        
        this.pila.push(concatenacion);
    }
    
    void generar_automata_kleene(){
        Automata automata = this.pila.pop();
        Automata kleene = new Automata();
        
        Estado inicial = new Estado(0, true, false);
        Estado fin = new Estado(0, false, true);
        
        inicial.agregarTransicion('Ɛ', automata.inicio);
        inicial.agregarTransicion('Ɛ', fin);
        
        automata.finales.get(0).agregarTransicion('Ɛ', fin);
        
        kleene.setEstadoInicio(inicial);
        kleene.agregarEstadoFinal(fin);
        
        kleene.agregarEstado(inicial);
        
        for(Estado estado: automata.estados){
            kleene.agregarEstado(estado);
        }
        
        kleene.agregarEstado(fin);
        
        for(int i = 0; i < kleene.estados.size(); i++){
            kleene.estados.get(i).id = i;
        }
        
        this.pila.push(kleene);
    }
}
