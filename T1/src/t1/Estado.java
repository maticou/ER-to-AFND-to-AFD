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
public class Estado {
    
    int id;
    boolean estadoInicial;
    boolean estadoFinal;
    
    ArrayList<Transicion> transiciones;

    public Estado(int id) {
        
        this.id = id;
        if(id == 0){
            this.estadoInicial = true;
        }else{
            this.estadoInicial = false;
        }
        
        this.estadoFinal = false;
    }        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }        
    
}
