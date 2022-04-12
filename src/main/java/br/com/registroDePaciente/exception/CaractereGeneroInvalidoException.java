/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.exception;

/**
 *
 * @author alciran
 */
public class CaractereGeneroInvalidoException extends RuntimeException {
    
    private char caracterInvalido;

    public CaractereGeneroInvalidoException(char caracterInvalido) {
        this.caracterInvalido = caracterInvalido;
    }

    public String getCaracterInvalido() {
        return "" + caracterInvalido;
    }
}
