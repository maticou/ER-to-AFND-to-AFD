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
public class Automata {
    
    Estado estadoInicial;
 
    ArrayList<Estado> listaEstados;
    ArrayList<Estado> listaEstadosFinales;

    public Automata() {        
        this.listaEstados = new ArrayList();
        this.listaEstadosFinales = new ArrayList();
    }    

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(Estado estados) {
        this.listaEstados.add(estados);
    }

    public ArrayList<Estado> getListaEstadosFinales() {
        return listaEstadosFinales;
    }

    public void setListaEstadosFinales(Estado estadosFinales) {
        this.listaEstadosFinales.add(estadoInicial);
    }
    
    
}
