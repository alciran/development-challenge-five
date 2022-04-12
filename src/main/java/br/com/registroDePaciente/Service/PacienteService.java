/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Service;

import br.com.registroDePaciente.Model.Endereco;
import br.com.registroDePaciente.Model.Paciente;
import br.com.registroDePaciente.Model.PacienteFactory;
import br.com.registroDePaciente.Repository.PacienteRepository;
import br.com.registroDePaciente.Repository.filter.PacienteFiltro;
import br.com.registroDePaciente.exception.CaractereGeneroInvalidoException;
import br.com.registroDePaciente.exception.DataDeNascimentoInvalidaException;
import br.com.registroDePaciente.exception.EnderecoEmailUnicoPacienteException;
import br.com.registroDePaciente.projection.PacienteByEmail;
import br.com.registroDePaciente.projection.PacienteResumo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author alciran
 */
@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public Page<Paciente> pesquisar(PacienteFiltro pacienteFiltro, Pageable pageable){
        return pacienteRepository.filtrar(pacienteFiltro, pageable);
    }
    
    public List<PacienteResumo> listarTodosResumo(){
        return pacienteRepository.findAllPacienteResumo();        
    }
    
    public Paciente buscarPorId(int id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(!paciente.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return paciente.get();
    }
    
    public Paciente buscarPorEmail(PacienteByEmail pacienteEmail) {        
        Optional<Paciente> paciente = pacienteRepository.findByEmail(pacienteEmail.getEmail());
        if(!paciente.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return paciente.get();
    }
    
    public Paciente criar(Paciente paciente){
        checarEnderecoEmail(paciente.getEmail());
        checarGeneroPaciente(paciente.getGenero());
        checarDataDeNascimento(paciente.getDataDeNascimento());        
      
        PacienteFactory pacienteFactory = new PacienteFactory();        

        return pacienteRepository.save(pacienteFactory.getPaciente(paciente.getNome(), paciente.getSobrenome(),
                paciente.getDataDeNascimento(), paciente.getGenero(), paciente.getEmail(), paciente.getEndereco()));
    }
    
    public Paciente atualizar(int id, Paciente paciente){
        Paciente pacienteSalvo = buscarPorId(id);
        BeanUtils.copyProperties(paciente, pacienteSalvo, "id");
        return pacienteRepository.save(pacienteSalvo);
    }
    
    public void remover(int id){
        Paciente paciente = buscarPorId(id);
        pacienteRepository.delete(paciente);
    }
    
    public Endereco buscarEnderecoPaciente(int id){
        Paciente paciente = buscarPorId(id);
        return paciente.getEndereco();
    }    
    
    private void checarEnderecoEmail(String email){
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if(paciente.isPresent()){            
            throw new EnderecoEmailUnicoPacienteException(email);
        }        
    }
    
    private void checarGeneroPaciente(char genero){
        if(genero != 'F' && genero != 'M')
            throw new CaractereGeneroInvalidoException(genero);
    }
    
    private void checarDataDeNascimento(Date dataDeNascimento){
        Date hoje = new Date();
        if(dataDeNascimento.after(hoje))
            throw new DataDeNascimentoInvalidaException();
    }

 }
