package com.app.topo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopoApplication {

	public static final String root = "v1";

	public static void main(String[] args) {
		SpringApplication.run(TopoApplication.class, args);
	}
}
