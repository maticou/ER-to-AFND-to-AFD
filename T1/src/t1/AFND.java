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
    Transicion transicion;
    Automata automata;
    String expresion;

    ArrayList<String> alfabeto;
    
    public AFND(String expresion){
        
        this.expresion = expresion;
        this.alfabeto = new ArrayList();
        
        inicioAFND();

    }

   public void inicioAFND(){
       
       Stack pila = new Stack();
       Automata auto1, auto2, autok;
       
       for(int i=0;i<this.expresion.length();i++){
           char c = this.expresion.charAt(i);           
           switch(c){
               case '*':
                   autok = estrellaKleene((Automata)pila.pop());
                   pila.push(autok);
                   this.automata = autok;
                   break;
               case '.':
                   auto1 = (Automata) pila.pop();
                   auto2 = (Automata) pila.pop();                   
                   Automata resultadoConcatenacion = concatenacion(auto1, auto2);
                   pila.push(resultadoConcatenacion);
                   this.automata = resultadoConcatenacion;
                   break;
               case '|':
                   auto1 = (Automata) pila.pop();
                   auto2 = (Automata) pila.pop();                   
                   Automata resultadoUnion = union(auto1, auto2);
                   pila.push(resultadoUnion);
                   this.automata = resultadoUnion;
                   break;               
               case '_':
                   Automata vacio = automataVacio();
                   pila.push(vacio);
                   this.automata = vacio;
                   break;
               default:
                   Automata autoLetra = automataLetra(c);
                   pila.push(autoLetra);
                   this.automata = autoLetra;
                   break;
           }
       }       
   }

    private Automata concatenacion(Automata auto1, Automata auto2) {
        
        Automata ac = new Automata();
        int i;
        for(i=0;i<auto2.getListaEstados().size();i++){
            Estado estado1 = auto2.getListaEstados().get(i);
            estado1.setId(i);
            if(i == 0){
                ac.setEstadoInicial(estado1);
            }
            if(i == auto2.getListaEstados().size()-1){
                for(int j=0;j<auto2.getListaEstadosFinales().size();j++){
                    Transicion tran = new Transicion(auto2.getListaEstadosFinales().get(j), auto1.getEstadoInicial(), "_");
                    estado1.agregarTransiciones(tran);
                }
            }                        
            ac.setListaEstados(estado1);
        }
        
        for(int k=0;k<auto1.getListaEstados().size();k++){
            Estado estado2 = auto1.getListaEstados().get(k);
            estado2.setId(i);
            
            if(auto1.getListaEstados().size()-1 == k){
                ac.setListaEstadosFinales(estado2);
            }
            ac.setListaEstados(estado2);
            i++;
        }
        return ac;
    }

    private Automata automataLetra(char c) {
        
        Automata al = new Automata();
        Estado inicio = new Estado(0);
        Estado fin = new Estado(1);
        Transicion trans = new Transicion(inicio, fin, String.valueOf(c));
        inicio.agregarTransiciones(trans);
        al.setListaEstados(inicio);
        al.setListaEstados(fin);
        al.setEstadoInicial(inicio);
        al.setListaEstadosFinales(fin);
        return al;
    }

    private Automata union(Automata auto1, Automata auto2) {
        Automata au = new Automata();
        Estado inicio = new Estado(0);
        Transicion trans = new Transicion(inicio, auto2.getEstadoInicial(), "_");
        inicio.agregarTransiciones(trans);
        au.setListaEstados(inicio);
        au.setEstadoInicial(inicio);
        
        int cont;
        for(cont=0;cont<auto1.getListaEstados().size();cont++){
            Estado estado1 = auto1.getListaEstados().get(cont);
            estado1.setId(cont+1);
            au.setListaEstados(estado1);            
        }
        for(int i=0;i<auto2.getListaEstados().size();i++){
            Estado estado2 = auto2.getListaEstados().get(i);
            estado2.setId(cont+1);
            au.setListaEstados(estado2);
            cont++;
        }
        Estado fin = new Estado(auto1.getListaEstados().size() + auto2.getListaEstados().size() + 1);
        au.setListaEstados(fin);
        au.setListaEstadosFinales(fin);
        Estado inicioPrevio = auto1.getEstadoInicial();
        ArrayList<Estado> finalAnterior1 = auto1.getListaEstadosFinales();
        ArrayList<Estado> finalAnterior2 = auto2.getListaEstadosFinales();
        Transicion trans2 = new Transicion(inicio, inicioPrevio, "_");
        inicio.transiciones.add(trans2);
        
        for(int j=0;j<finalAnterior1.size();j++){
            trans2 = new Transicion(finalAnterior1.get(j), fin, "_");
            finalAnterior1.get(j).transiciones.add(trans2);
        }
        for(int k=0;k<finalAnterior2.size();k++){
            trans2 = new Transicion(finalAnterior2.get(k), fin, "_");
            finalAnterior2.get(k).transiciones.add(trans2);
        }
        
        return au;
    }

    private Automata estrellaKleene(Automata automata) {
        Automata ak = new Automata();
        Estado inicio = new Estado(0);        
        ak.setListaEstados(inicio);
        ak.setEstadoInicial(inicio);
        for(int i=0;i<automata.getListaEstados().size();i++){
            Estado estado3 = automata.getListaEstados().get(i);
            estado3.setId(i+1);
            ak.setListaEstados(estado3);
        }
        Estado fin = new Estado(automata.getListaEstados().size()+1);
        ak.setListaEstados(fin);
        ak.setListaEstadosFinales(fin);
        Estado inicioPrevio = automata.getEstadoInicial();
        ArrayList<Estado> finalPrevio = automata.getListaEstadosFinales();
        inicio.transiciones.add(new Transicion(inicio, inicioPrevio, "_"));
        inicio.transiciones.add(new Transicion(inicio, fin, "_"));
        
        for(int i=0;i<finalPrevio.size();i++){
            finalPrevio.get(i).transiciones.add(new Transicion(finalPrevio.get(i), inicioPrevio, "_"));
            finalPrevio.get(i).transiciones.add(new Transicion(finalPrevio.get(i), fin, "_"));
        }
        
        return ak;
    }

    private Automata automataVacio() {
        Automata auto5 = new Automata();
        Estado inicio = new Estado(0);
        Estado fin = new Estado(1);
        auto5.setListaEstados(inicio);
        auto5.setListaEstados(fin);
        auto5.setEstadoInicial(inicio);
        auto5.setListaEstadosFinales(fin);
        return auto5;
    }
    
    public ArrayList<String> getAlfabeto() {
        for (Character c: this.expresion.toCharArray()){           
            if (c != '|' && c != '*' && c != '.' && c != '_' && c!='0'){
                if(!this.alfabeto.contains(Character.toString(c))){
                    this.alfabeto.add(Character.toString(c));
                }             
            }
        }
        return this.alfabeto;
    }
    
    public void imprimirAFND(){
        System.out.println("AFND:");
        System.out.printf("K = {");
        for(int i= 0; i<this.automata.getListaEstados().size();i++){
            if(i == this.automata.getListaEstados().size()-1){
                System.out.printf("q"+this.automata.getListaEstados().get(i).getId() + "}");  
            }else{
                System.out.printf("q" + this.automata.getListaEstados().get(i).getId() + ", ");                
            }            
        }
        System.out.println("");
        System.out.printf("Sigma = ");
        System.out.println(getAlfabeto());
        System.out.println("Delta: ");
        for (int i =0 ; i<this.automata.getListaEstados().size();i++){
            Estado estado6 = this.automata.getListaEstados().get(i);
            
            for (int j = 0; j < estado6.transiciones.size(); j++){
                System.out.println("(q" + estado6.transiciones.get(j).estadoInicio.getId() + ", " + estado6.transiciones.get(j).elemento + ", q" + estado6.transiciones.get(j).estadoFin.getId() + ")");                
            }             
        }
        System.out.println("s={q" + this.automata.estadoInicial.getId() + "}");
        
        System.out.printf("F={");
        for(int i= 0; i<this.automata.listaEstadosFinales.size();i++){
            if(i == this.automata.listaEstadosFinales.size()-1){
                System.out.printf("q" + this.automata.listaEstadosFinales.get(i).getId() + "}");                
            }else{
                System.out.printf("q" + this.automata.listaEstadosFinales.get(i).getId() + ", ");                
            }            
        }        
    }    
}
