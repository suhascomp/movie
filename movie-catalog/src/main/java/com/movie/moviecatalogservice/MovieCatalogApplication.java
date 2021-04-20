package com.movie.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getTemplate() {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(httpComponentsClientHttpRequestFactory);
	}

//	@Bean
//	public WebClient.Builder getBuilder() {
//		return WebClient.builder();
//	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApplication.class, args);
	}

}
