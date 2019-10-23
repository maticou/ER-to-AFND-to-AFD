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
    ArrayList<String> alfabeto;
    ArrayList<Integer> listaEstados;
    
    AFND(String expresion){
        this.alfabeto = new ArrayList();
        this.listaEstados = new ArrayList();        
        this.expresion = expresion;
        setAlfabeto();        
        this.generar_automata();
        setListaEstados();
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
        for(int i=0;i<this.listaEstados.size();i++){            
            if(this.listaEstados.size()-1 == i){
                System.out.printf("q"+getListaEstados().get(i));
            }else{
                System.out.printf("q"+getListaEstados().get(i)+" ,");
            }            
        }
        System.out.printf("}");
        System.out.println(" ");
        System.out.printf("Sigma = ");
        System.out.println(getAlfabeto());        
        System.out.println("Delta");        
        for(Estado estado: this.automata.estados){
            estado.imprimir_transiciones();
        }        
        System.out.println("s = { q" + this.automata.inicio.id + " }");
        System.out.printf("F = { ");
        for(int i=0;i<this.automata.finales.size();i++){
            if(this.automata.finales.size()-1 == i){
                System.out.printf("q" + this.automata.finales.get(i).id);
            }else{
                System.out.printf("q" + this.automata.finales.get(i).id + ",");
            }             
        }
        System.out.printf("}");
        System.out.println("");
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
        
        union.inicio = inicio;
        union.agregarEstadoFinal(fin);
        
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
        automata.finales.get(0).agregarTransicion('Ɛ', automata.inicio);
        
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

    public Automata getAutomata() {
        return this.automata;
    }   
    
    public String getExpresion() {
        return expresion;
    }
    
    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto() {        
        for (Character c: getExpresion().toCharArray()){                             
            if (c != '|' && c != '*' && c != '.' && c != '_' && c!='0'){
                if(!this.alfabeto.contains(Character.toString(c))){
                    this.alfabeto.add(Character.toString(c));
                }             
            }
        }        
    }
    
    public ArrayList<Integer> getListaEstados(){
        return this.listaEstados;
    }
    
    private void setListaEstados() {
        for(int i=0;i<this.automata.estados.size();i++){
            Estado estado = this.automata.estados.get(i);            
            this.listaEstados.add(estado.id);
        }
    }
}
