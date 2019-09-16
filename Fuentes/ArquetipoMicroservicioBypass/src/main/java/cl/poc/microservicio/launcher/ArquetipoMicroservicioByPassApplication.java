package cl.poc.microservicio.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Punto de inicio para la aplicacion
 * 
 * @author ccontrerasc
 *
 */
@SpringBootApplication

/*
 * Se debe de indicar todos los packages que contiene esta aplicacion
 */
@ComponentScan
(
	{ 
		"cl.poc.microservicio.bean",
		"cl.poc.microservicio.helper",
		"cl.poc.microservicio.launcher",
		"cl.poc.microservicio.rest",
		"cl.poc.microservicio.delegate",
		"cl.poc.microservicio.camel"
	}
)
public class ArquetipoMicroservicioByPassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquetipoMicroservicioByPassApplication.class, args);
	}

}
