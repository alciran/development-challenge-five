/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alciran
 */
@Getter
@Setter
@AllArgsConstructor
public class PacienteResumo implements Serializable {
    
    private int id;    
    private String nome;
    private String sobrenome;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataDeNascimento;
    
    private String email;
    
}
