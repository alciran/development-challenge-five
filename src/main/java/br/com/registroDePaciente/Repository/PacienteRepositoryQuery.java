/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Repository;

import br.com.registroDePaciente.Model.Paciente;
import br.com.registroDePaciente.Repository.filter.PacienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author alciran
 */
public interface PacienteRepositoryQuery {
    
    public Page<Paciente> filtrar(PacienteFiltro pacienteFiltro, Pageable pageable);
    
}
