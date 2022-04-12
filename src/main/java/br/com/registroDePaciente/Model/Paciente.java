/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Model;

import br.com.registroDePaciente.Model.Defaults.PacientePadrao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alciran
 */
@Getter
@Setter
@JsonDeserialize(as=PacientePadrao.class)
@Entity
@Table(name = "paciente")
public abstract class Paciente<T> implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Nome do Paciente não pode ser nulo")
    @Size(min = 3, max = 50)
    protected String nome;
    
    @NotNull(message = "Sobrenome do Paciente não pode ser nulo")
    @Size(min = 3, max = 100)
    protected String sobrenome; 
    
    @NotNull(message = "Data de Nascimento do paciente não pode ser nulo")    
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected Date dataDeNascimento;
    
    @NotNull(message = "Genero do Paciente não pode ser nulo")
    @Column(length = 1)
    protected char genero;
    
    @NotNull(message = "E-mail do paciente não pode ser nulo")
    @Column(unique = true)
    @Size(min = 3, max = 255)
    @Email(regexp = ".+@.+\\..+")
    protected String email;
    
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    protected Endereco endereco;

    public int getIdadeEmAnos(){
        return Period.between(
            Instant.ofEpochMilli(this.getDataDeNascimento().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate(),
            LocalDate.now()).getYears();
    }
    
    public String getIdadeCompleta(){
        LocalDate hoje = LocalDate.now();
        LocalDate dataNascimento = Instant.ofEpochMilli(this.getDataDeNascimento().getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        int ano = Period.between(dataNascimento, hoje).getYears();
        int mes = Period.between(dataNascimento, hoje).getMonths();
        int dia = Period.between(dataNascimento, hoje).getDays();
        
        String valorAno = ano == 1 ? ano + " ano, " : ano > 1 ? ano + " anos, " : "";
        String valorMes = mes > 1 ? mes + " meses e " : mes + " mês e ";
        String valoDia = dia > 1 ? dia + " dias" : dia + " dia";        
        
        return valorAno + valorMes + valoDia;                
    }
    
    public String getStatusFaixaEtaria(){
        return this.getIdadeEmAnos() <= 12 ? "Infantil" : 
               this.getIdadeEmAnos() <= 18 ? "Jovem" :
               this.getIdadeEmAnos() <= 60 ? "Adulto" : "Idoso";        
    }
    
    public String getGeneroString(){
        return this.getGenero() == 'F' ? "Feminino" : "Masculino";
    }

}
