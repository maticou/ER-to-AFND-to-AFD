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
        
    public AFD() {
          
        //this.transicion = new Transicion();

        this.listaEstados = new ArrayList();
        this.listaTransiciones = new ArrayList();
        this.alfabeto = new ArrayList();
        this.inicios = new ArrayList();
        this.finales = new ArrayList();
    }

    public Transicion getTransicion() {
        return transicion;
    }

    public void setTransicion(Transicion transicion) {
        this.transicion = transicion;
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

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<String> getInicios() {
        return inicios;
    }

    public void setInicios(ArrayList<String> inicios) {
        this.inicios = inicios;
    }

    public ArrayList<String> getFinales() {
        return finales;
    }

    public void setFinales(ArrayList<String> finales) {
        this.finales = finales;
    }
    
    
            
}
