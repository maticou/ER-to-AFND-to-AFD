/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Mn_go
 */
public class Parser {
    
    private Map<Character, Integer> precedencia;
    public String aux;
   
    Parser(String expresion){
        Map<Character, Integer> map = new HashMap<>();        
        
        map.put('(', 1);
        map.put('|', 2);
        map.put('.', 3);
        map.put('*', 4);
        precedencia = Collections.unmodifiableMap(map);
        
        setAux(convertir_expresion(expresion));                        
    }
    
    String convertir_expresion(String expresion){
        Stack<Character> stack = new Stack<>();
        String postfix = "";
        
        for(char c : expresion.toCharArray()){
            switch(c){
                case '(':
                   stack.push(c);
                   break;

                case ')':
                   while (!stack.peek().equals('(')) 
                   {
                       postfix += stack.pop();
                   }
                   stack.pop();
                   break;

                default:
                   while (stack.size() > 0) 
                   {
                       Character peekedChar = stack.peek();
                       Integer peekedCharPrecedence = obtenerPrecedencia(peekedChar);
                       Integer currentCharPrecedence = obtenerPrecedencia(c);
                       if (peekedCharPrecedence >= currentCharPrecedence) 
                       {
                           postfix += stack.pop();

                       } 
                       else 
                       {
                           break;
                       }
                   }
                   stack.push(c);
                   break;
            }
        }
        
        while (stack.size() > 0)
        {
            postfix += stack.pop();
        }
        
        return postfix;

    }
    
    int obtenerPrecedencia(char c){
        Integer precedencia_char = precedencia.get(c);
        
        if(precedencia_char == null)
        { 
            precedencia_char = 6;
        }
        
        return precedencia_char;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
        
}
