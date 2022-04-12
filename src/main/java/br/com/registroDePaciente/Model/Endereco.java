/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.registroDePaciente.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alciran
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "Logradouro não pode ser nulo")
    @Size(min = 3, max = 255)
    private String logradouro; 
    
    @NotNull(message = "Bairro não pode ser nulo")
    @Size(min = 3, max = 255)
    private String bairro;

    @Column(nullable = true)
    private String complemento;
          
//    @ManyToOne
//    @JoinColumn(name = "estado_id")
//    private Estado uf;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;      
        
    @NotNull(message = "Número de CEP não pode ser nulo")
    @Size(min = 3, max = 255)
    private String cep;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
