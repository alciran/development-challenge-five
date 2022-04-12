/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Resource;

import br.com.registroDePaciente.Model.Endereco;
import br.com.registroDePaciente.Service.EnderecoService;
import br.com.registroDePaciente.event.RecursoCriadoEvent;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
@RequestMapping("/enderecos")
public class EnderecoResource {
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable int id){
        Endereco endereco = enderecoService.buscarPorId(id);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@Valid @RequestBody Endereco endereco, HttpServletResponse response){
        Endereco enderecoSalvo = enderecoService.criar(endereco);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
    }
    
    @PutMapping("/{id}")   
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable int id, @Valid @RequestBody Endereco endereco){
        Endereco enderecoSalvo = enderecoService.atualizar(id, endereco);
        return ResponseEntity.ok(enderecoSalvo);
    }
       
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEndereco(@PathVariable int id){
        enderecoService.remover(id);
    }
    
}
