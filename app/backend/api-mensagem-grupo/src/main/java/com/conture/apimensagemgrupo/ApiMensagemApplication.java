package com.conture.apimensagemgrupo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiMensagemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiMensagemApplication.class, args);
	}
}
