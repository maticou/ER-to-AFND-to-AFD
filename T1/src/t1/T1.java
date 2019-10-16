/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matiassebastianparra
 */
public class T1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una ER:");
        System.out.println("Se permiten las letras del alfabeto inglés en mayúsculas o minúsculas. Los operadores son .,|,*");                
        String expresion = scanner.nextLine(); 
       
        String[] lista = expresion.split("[.|*]");
        
        if(expresion.contains(".")){                        
            concatenacion(lista);
        }
        else if(expresion.contains("|")){                        
            union(lista);
        }
        else if(expresion.contains("*")){                        
            estrella(lista);
        }
        /*for (String a : lista){ 
            System.out.println(a);
        }*/
    }
    
    static public void concatenacion(String[] lista){
        System.out.println("Concatenación");
        System.out.printf(lista[0]);
        System.out.printf(".");
        System.out.println(lista[1]);
    }
    
    static public void union(String[] lista){
       System.out.println("Unión");
       System.out.printf(lista[0]);
       System.out.printf("|");
       System.out.println(lista[1]); 
    }
    
    static public void estrella(String[] lista){
        System.out.println("Estrella de Kleene");
        System.out.printf(lista[0]);
        System.out.printf("*");
        System.out.println(lista[1]);
    }
    
}
