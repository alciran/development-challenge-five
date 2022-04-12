/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Repository;

import br.com.registroDePaciente.Model.Paciente;
import br.com.registroDePaciente.projection.PacienteResumo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author alciran
 */
public interface PacienteRepository extends JpaRepository<Paciente, Integer> , PacienteRepositoryQuery{
    public Optional<Paciente> findByEmail(String email);
    
    @Query("select new br.com.registroDePaciente.projection.PacienteResumo(id, nome, sobrenome, dataDeNascimento, email)" 
            + "from Paciente order by nome asc")
    public List<PacienteResumo> findAllPacienteResumo();
    
}
