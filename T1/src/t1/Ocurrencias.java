/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

/**
 *
 * @author matiassebastianparra
 */
public class Ocurrencias {
    
    String texto;
    
    public Ocurrencias(String texto) {
        this.texto = texto;
        imprimirOcurrencias(texto);
    }              

    private void imprimirOcurrencias(String texto) {
        System.out.println("Texto: " + texto);
    }    
}
