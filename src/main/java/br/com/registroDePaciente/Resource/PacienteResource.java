/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Resource;

import br.com.registroDePaciente.Model.Endereco;
import br.com.registroDePaciente.Model.Paciente;
import br.com.registroDePaciente.Repository.filter.PacienteFiltro;
import br.com.registroDePaciente.Service.PacienteService;
import br.com.registroDePaciente.event.RecursoCriadoEvent;
import br.com.registroDePaciente.projection.PacienteByEmail;
import br.com.registroDePaciente.projection.PacienteResumo;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alciran
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteResource {
    
    @Autowired
    private PacienteService pacientetService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public Page<Paciente> pesquisarPacientes(PacienteFiltro pacienteFiltro, Pageable pageable){
         return pacientetService.pesquisar(pacienteFiltro, pageable);
    }
    
    @GetMapping("/resumos")
    public List<PacienteResumo> buscarPacientesResumo(){
         return pacientetService.listarTodosResumo();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id){
        Paciente paciente = pacientetService.buscarPorId(id);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/email")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@RequestBody PacienteByEmail pacienteEmail, HttpServletResponse response){
        Paciente paciente = pacientetService.buscarPorEmail(pacienteEmail);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/endereco")
    public ResponseEntity<Endereco> buscarEnderecoPaciente(@PathVariable int id){
        Endereco endereco = pacientetService.buscarEnderecoPaciente(id);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@Valid @RequestBody Paciente paciente, HttpServletResponse response){
        Paciente pacienteSalvo = pacientetService.criar(paciente);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarCadastroPaciente(@PathVariable int id, @Valid @RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacientetService.atualizar(id, paciente);
        return ResponseEntity.ok(pacienteSalvo);
    }
       
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCadastroPaciente(@PathVariable int id){
        pacientetService.remover(id);
    }
    
}
