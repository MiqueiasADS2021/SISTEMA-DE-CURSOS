package br.edu.cursosifpb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
		//(exclude = { DataSourceAutoConfiguration.class })
public class CursosifpbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosifpbApplication.class, args);
	}

}
