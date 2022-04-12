/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import br.com.registroDePaciente.Model.Products.PacienteFeminino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author alciran
 */
@SpringBootTest
public class PacienteTest {    
       
    @Test
    public void testGeneroString(){
        PacienteFeminino paciente = new PacienteFeminino();
        paciente.setGenero('F');
        assertEquals("Feminino", paciente.getGeneroString());
    }
    
}
