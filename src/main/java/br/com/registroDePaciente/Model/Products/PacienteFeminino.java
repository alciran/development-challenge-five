/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Model.Products;

import br.com.registroDePaciente.Model.Endereco;
import br.com.registroDePaciente.Model.Paciente;
import java.util.Date;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author alciran
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PacienteFeminino extends Paciente {

    @ColumnDefault("0")
    private Boolean gestante = false;
    
    public PacienteFeminino(String nome, String sobrenome, Date dataDeNascimento, char genero, String email, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.email = email;
        this.endereco = endereco;
    }
    
    public PacienteFeminino(String nome, String sobrenome, Date dataDeNascimento, char genero, String email, Endereco endereco, boolean gestante) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.email = email;
        this.endereco = endereco;
        this.gestante = gestante;
    }
    
}
