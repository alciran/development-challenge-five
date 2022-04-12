/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Service;

import br.com.registroDePaciente.Model.Endereco;
import br.com.registroDePaciente.Repository.EnderecoRepository;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author alciran
 */
@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco buscarPorId(int id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if(!endereco.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return endereco.get();
    }

    public Endereco criar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(int id, Endereco endereco){
        Endereco enderecoSalvo = buscarPorId(id);
        BeanUtils.copyProperties(endereco, enderecoSalvo, "id");
        return enderecoRepository.save(enderecoSalvo);
    }
    
    public void remover(int id){
        Endereco endereco = buscarPorId(id);
        enderecoRepository.delete(endereco);
    }
    
}
