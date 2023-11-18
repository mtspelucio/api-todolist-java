package br.com.mtspelucio.todolist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Rest TodoList",
				version = "1.0.0",
				description = "API Rest com Java Spring Boot para gerenciamento e controle de tarefas, utilizando Swagger para gerar a documentação da aplicação.",
				termsOfService = "MtsPelucio",
				contact = @Contact(
						name = "Matheus Pelucio",
						email = "mts.rangel@hotmail.com"
				),
				license = @License(
						name = "licenca",
						url = "MtsPelucio"
				)
		)
)
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
