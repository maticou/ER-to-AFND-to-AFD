/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.Scanner;

/**
 *
 * @author matiassebastianparra
 */
public class T1 {
    static public AFND afnd;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una ER:");
        System.out.println("Se permiten las letras del alfabeto inglés en mayúsculas o minúsculas. Los operadores son .,|,*");                
        String expresion = scanner.nextLine(); 
        
        Parser parseo = new Parser(expresion);
        
        System.out.println("Expresion nueva: " + parseo.getAux());
        
        afnd = new AFND(parseo.getAux());
        afnd.imprimir_automata();
        
        AFD afd = new AFD(afnd.automata);
    }
    
    
    
    static public void concatenacion(String texto1, String texto2){
        System.out.println("Concatenación");
        System.out.println(texto1);
        System.out.println(".");
        System.out.println(texto2);
    }
    
    static public void union(String texto1, String texto2){
       System.out.println("Unión"); 
       System.out.println(texto1);
        System.out.println("|");
        System.out.println(texto2);
    }
    
    static public void estrella(String texto1, String texto2){
        System.out.println("Estrella de Kleene");
        System.out.println(texto1);
        System.out.println("*");
        System.out.println(texto2);
    }
    
}
