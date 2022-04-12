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
public class EnderecoEmailUnicoPacienteException extends RuntimeException {
    
    private String email;

    public EnderecoEmailUnicoPacienteException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
}
