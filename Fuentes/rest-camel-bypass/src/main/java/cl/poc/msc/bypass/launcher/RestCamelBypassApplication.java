package cl.poc.msc.bypass.launcher;

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
		"cl.poc.msc.bypass.launcher",
		"cl.poc.msc.bypass.mgr",
		"cl.poc.msc.bypass.mgr.impl",
		"cl.poc.msc.bypass.bean",
		
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
