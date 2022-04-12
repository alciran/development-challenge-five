 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Repository;

import br.com.registroDePaciente.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alciran
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
}
