package com.viniciuskenji.ff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;

@SpringBootApplication
public class FfApplication {

	public static void main(String[] args) {
		SpringApplication.run(FfApplication.class, args);
	}

	@Bean
	public Unleash unleash() {
		UnleashConfig config = UnleashConfig.builder().appName("FfApplication").instanceId("instance-1")
				.unleashAPI("http://ff:4242/api/").build();

		return new DefaultUnleash(config);
	}

}
