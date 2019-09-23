package cl.poc.msc.bypass.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Inicializacion
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
		"cl.poc.msc.bypass.camel",
		"cl.poc.msc.bypass.launcher"
	}
)
public class RestCamelBypassApplication {

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestCamelBypassApplication.class, args);
	}

}
