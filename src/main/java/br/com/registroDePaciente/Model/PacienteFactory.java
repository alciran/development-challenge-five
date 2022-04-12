/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Model;

import br.com.registroDePaciente.Model.Products.PacienteFeminino;
import br.com.registroDePaciente.Model.Products.PacienteMasculino;
import java.util.Date;

/**
 *
 * @author alciran
 */
public class PacienteFactory {
    
    public Paciente getPaciente(String nome, String sobrenome, Date dataDeNascimento, char genero, String email, Endereco endereco) {
        if (genero == 'M')
             return new PacienteMasculino(nome, sobrenome, dataDeNascimento, genero, email, endereco);
        else if (genero == 'F')
            return new PacienteFeminino(nome, sobrenome, dataDeNascimento, genero, email, endereco);        
        return null;
    }
    
}
