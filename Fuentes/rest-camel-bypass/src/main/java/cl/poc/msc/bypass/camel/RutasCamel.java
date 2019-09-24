package cl.poc.msc.bypass.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import cl.poc.msc.bypass.bean.Greetings;

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

		rest("Ejemplos")
			.consumes("application/json")
			.produces("application/json")
		
		.get("Get").description("Servicio GET de saludo  - Definicion")
			.outType(Greetings.class)
        	.to("direct:saludoImpl");
		
		 from("direct:saludoImpl").description("Servicio GET de saludo - Implementacion")
		 .streamCaching()
		 .to("bean:greetingsService?method=getGreetings");     
		 

	}
}
