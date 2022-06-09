package com.conture.apimensagemdireta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiMensagemDiretaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiMensagemDiretaApplication.class, args);
	}
}
