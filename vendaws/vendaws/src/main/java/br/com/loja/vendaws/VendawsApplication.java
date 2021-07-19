package br.com.loja.vendaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VendawsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendawsApplication.class, args);
	}

}
