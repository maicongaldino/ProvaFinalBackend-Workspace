package br.com.loja.produtows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProdutowsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProdutowsApplication.class, args);
	}
}