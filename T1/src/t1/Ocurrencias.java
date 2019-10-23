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
        int i=0;
        Estado estado = this.afd.estado_inicial;
        
        while(i < this.texto.length()){
            if(estado.transiciones.get(this.texto.charAt(i)) == null){
                System.out.println("null");
                i++;
            }else{
                for(int j=0; j<estado.transiciones.get(this.texto.charAt(i)).size();j++){
                    Estado estadoAux = estado.transiciones.get(this.texto.charAt(i)).get(j);
                    System.out.println("estado q"+estadoAux.id);
                    System.out.println("letra "+this.texto.charAt(i));
                    if(estadoAux.fin){
                        this.indicesFinales.add(i);
                        System.out.println("FIN");
                    }                    
                }
                System.out.println("NO null");
                i++;
            }            
        }
    }

    private void imprimirOcurrencias(String texto) {
        System.out.println("Texto: " + texto);
        System.out.println("this.indicesFinales.size() "+this.indicesFinales.size());
        for(int i=0; i<this.indicesFinales.size();i++){
            System.out.printf("Ocurrencias: ");
            if(i<this.indicesFinales.size()-1){
                System.out.print(this.indicesFinales.get(i) + 1);
            }else{
                System.out.print(this.indicesFinales.get(i) + 1 + ", ");
            }            
        }
    }    
}
