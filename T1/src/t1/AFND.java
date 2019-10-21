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
public class AFND {
    
    int id;
    Estado estado;
    Transicion transicion;
    Automata automata;
    String expresion;
    ArrayList<Estado> listaEstados;
    ArrayList<Transicion> listaTransiciones;
    ArrayList<Automata> listaAutomatas;
    ArrayList<Integer> posicionOperadores;
    ArrayList<String> alfabeto;
    ArrayList<String> inicios;
    ArrayList<String> finales;
    
    public AFND(String expresion){
        
        this.expresion = expresion;
        
        //this.transicion = new Transicion();        
        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.listaAutomatas = new ArrayList();
        this.posicionOperadores = new ArrayList();
        this.alfabeto = new ArrayList();
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
        
        setAlfabeto();   
        setTransicion();
        setInicios();
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }        

    public Transicion getTransicion() {
        return transicion;
    }

    public void setTransicion() {                
        for (int i=0; i<getExpresion().length();i++){           
            if (getExpresion().charAt(i) != '|' && getExpresion().charAt(i) != '*' && getExpresion().charAt(i) != '.' && getExpresion().charAt(i)!='0'){
                this.transicion = new Transicion(this.id, (this.id) + 1, Character.toString(getExpresion().charAt(i)));
                this.listaTransiciones.add(transicion);
                this.automata = new Automata(String.valueOf(getExpresion().charAt(i)), this.id, (this.id) + 1, this.listaTransiciones);  
                this.listaAutomatas.add(automata);                
            }else{
                switch(getExpresion().charAt(i)){
                    case '.':
                        String texto = String.valueOf(getExpresion().charAt(i-2))+String.valueOf(getExpresion().charAt(i))+String.valueOf(getExpresion().charAt(i-1));
                        this.automata = new Automata(texto, i-2, i, this.listaTransiciones);
                        this.listaAutomatas.add(automata);    
                        break;
                }
            }
        }         
    }

    public ArrayList<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(ArrayList<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public ArrayList<Transicion> getListaTransiciones() {
        return listaTransiciones;
    }

    public void setListaTransiciones(ArrayList<Transicion> listaTransiciones) {
        this.listaTransiciones = listaTransiciones;
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

    public ArrayList<String> getInicios() {
        return inicios;
    }

    public void setInicios() {
        for(int i=0;i<getListaTransiciones().size();i++){
            if(this.listaTransiciones.get(i).getEstadoInicio().isEstadoInicial()){
                this.inicios.add("q"+this.listaTransiciones.get(i).getEstadoInicio().getId());
            }
        }        
    }

    public ArrayList<String> getFinales() {
        return finales;
    }

    public void setFinales(ArrayList<String> finales) {
        this.finales = finales;
    }      
    
    public void imprimirAFND(){
        System.out.println("AFND:");
        System.out.printf("K = ");
        System.out.println("");
        System.out.printf("Sigma = ");
        System.out.println(getAlfabeto());
        System.out.println("Delta: ");
        
        for(int i=0;i<this.listaAutomatas.size();i++){            
            System.out.println("(q"+this.listaAutomatas.get(i).estadoInicial.getId()+","+this.listaAutomatas.get(i).identificador+",q"+this.listaAutomatas.get(i).estadoFin.getId()+")");            
        }
        for(int i=0;i<this.getInicios().size();i++){

            System.out.println("s = "+this.inicios.get(i));
            
        }
        
        System.out.println("F = ");
    }
    
}
