/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matiassebastianparra
 */
public class T1 {
    static public AFND afnd;
    static public AFD afd;
    static public Ocurrencias ocurrencias;
    static ArrayList<String> alfabeto;
    static ArrayList<Integer> listaEstados;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        alfabeto = new ArrayList();
        listaEstados = new ArrayList();
        Scanner scanner = new Scanner(System.in);       
        String expresion = scanner.nextLine(); 
        String texto = scanner.nextLine(); 
        
        Parser parseo = new Parser(expresion);

        afnd = new AFND(parseo.getAux());        
        afnd.imprimir_automata();

        alfabeto = afnd.getAlfabeto();
        listaEstados = afnd.getListaEstados();

        afd = new AFD(afnd.automata, alfabeto);

        ocurrencias = new Ocurrencias(texto, afd);                      
    }    
}
