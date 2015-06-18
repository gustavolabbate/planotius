/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.exception;

/**
 *
 * @author ggodoy
 */
public class ElementNotFinded extends RuntimeException{

    public ElementNotFinded() {
    }

    public ElementNotFinded(String message) {
        super(message);
    }
    
    
    
}
