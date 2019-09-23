package cl.poc.msc.bypass.launcher;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Rutinas camel
 * 
 * @author ccontrerasc
 *
 */
@Component
public class RutasCamel extends RouteBuilder {

	/**
	 * Configuracion de camel
	 */
	@Override
	public void configure() throws Exception {

		/*
		 * Ejemplo de rutina exponiendo un servicio GET
		 */
		rest().get("/Get").to("direct:get");
		from("direct:get").log(LoggingLevel.INFO, "Retornando un Hola mundo").transform().simple("Hello World");

	}
}
