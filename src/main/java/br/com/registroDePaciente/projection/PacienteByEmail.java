/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.projection;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alciran
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteByEmail implements Serializable {
    
    private String email;
    
}
