package br.com.system.dothours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Exemplo", version = "1.0", description = "Documentação da API"))
public class DothoursApplication {

	public static void main(String[] args) {
		SpringApplication.run(DothoursApplication.class, args);
	}

}


