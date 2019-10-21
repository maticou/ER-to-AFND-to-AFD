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
    
    ArrayList<Transicion> transiciones = new ArrayList();

    public Estado(int id) {
        
        this.id = id;                
    }   
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public boolean agregarTransiciones(Transicion tran){        
        return transiciones.add(tran);
    }
    
}
