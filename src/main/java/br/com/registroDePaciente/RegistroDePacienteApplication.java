package br.com.registroDePaciente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class RegistroDePacienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistroDePacienteApplication.class, args);
    }

}
