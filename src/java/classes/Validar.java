/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Jeremías
 */
public class Validar {
    
    public boolean isUsernameOrPasswordValid(String $cadena1) {
        
        char[] cadena = $cadena1.toLowerCase().toCharArray();
        
        for (int i = 0; i < cadena.length; i++) {
            
            if (cadena[i] == ' '
                    || cadena[i] == '='
                    || cadena[i] == '?'
                    || cadena[i] == '+'
                    || cadena[i] == '*'
                    || cadena[i] == '/'
                    || cadena[i] == '.'
                    || cadena[i] == ','
                    || cadena[i] == ';'
                    || cadena[i] == '!'
                    || cadena[i] == '>'
                    || cadena[i] == '<'
                    || cadena[i] == ':'
                    || cadena[i] == '\'') {
                
                return false;
                
            }
        }
        
        return true;
        
    }
    
}
