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
public class Ocurrencias {
    
    AFD afd;
    ArrayList<Integer> indicesFinales;
    String texto;
    
    public Ocurrencias(String texto, AFD afd) {
        this.texto = texto;
        this.afd = afd;
        this.indicesFinales = new ArrayList<Integer>();
        
        recorrerAutomata();
        imprimirOcurrencias(texto);
    }         
    
    private void recorrerAutomata() {                
        for(int i=0;i<this.texto.length();i++){
            Estado estado = this.afd.estado_inicial;
            for(int k=i;k<this.texto.length();k++){
                if(estado.transiciones.get(this.texto.charAt(k)) != null){
                    Estado estadoAux = estado.transiciones.get(this.texto.charAt(k)).get(0);
                    if(estadoAux.fin){
                        if(!this.indicesFinales.contains(k)){
                            this.indicesFinales.add(k);
                        }                            
                    }  
                    estado = estadoAux;
                }
            }                
        }
    }

    private void imprimirOcurrencias(String texto) {   
        System.out.println("");        
        System.out.printf("Ocurrencias: ");
        for(int i=0; i<this.indicesFinales.size();i++){            
            if(i == this.indicesFinales.size()-1){
                System.out.print(this.indicesFinales.get(i) + 1 + " ");
            }else{
                System.out.print(this.indicesFinales.get(i) + 1 + ", ");
            }            
        }
        System.out.println("");
    }    
}
