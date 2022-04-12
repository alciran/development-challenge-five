/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Repository;

import br.com.registroDePaciente.Model.Paciente;
import br.com.registroDePaciente.Repository.filter.PacienteFiltro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

/**
 *
 * @author alciran
 */
public class PacienteRepositoryImpl implements PacienteRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public Page<Paciente> filtrar(PacienteFiltro pacienteFiltro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
        
        Root<Paciente> root = criteria.from(Paciente.class);
     
        Predicate[] predicates = criarRestricoes(pacienteFiltro, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Paciente> query = manager.createQuery(criteria);
        
        adicionarRestricoesDePaginacao(query, pageable);
        
        return new PageImpl<>(query.getResultList(), pageable, total(pacienteFiltro));
    }
    
    private Predicate[] criarRestricoes(PacienteFiltro pacienteFiltro, CriteriaBuilder builder, Root<Paciente> root){
        
        List<Predicate> predicates = new ArrayList<>();
        
        if(!StringUtils.isEmpty(pacienteFiltro.getNome())){
            predicates.add(builder.like(
                    builder.lower(root.get("nome")), "%" + pacienteFiltro.getNome().toLowerCase() + "%"
            ));
        }
        
        if(!StringUtils.isEmpty(pacienteFiltro.getSobrenome())){
            predicates.add(builder.like(
                builder.lower(root.get("sobrenome")), "%" + pacienteFiltro.getSobrenome().toLowerCase() + "%"
            ));
        }
        
        if(!StringUtils.isEmpty(pacienteFiltro.getEmail())){
            predicates.add(builder.like(
                builder.lower(root.get("email")), "%" + pacienteFiltro.getEmail().toLowerCase() + "%"
            ));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);        
        
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Paciente> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }
    
    private Long total(PacienteFiltro pacienteFiltro){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Paciente> root = criteria.from(Paciente.class);
        
        Predicate[] predicates = criarRestricoes(pacienteFiltro, builder, root);        
        criteria.where(predicates);
        
        criteria.select(builder.count(root));        
        return manager.createQuery(criteria).getSingleResult();
    }
    
}
