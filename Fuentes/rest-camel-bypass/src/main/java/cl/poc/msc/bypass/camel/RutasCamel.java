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

		/*
		 * Ejemplo de rutina exponiendo un servicio GET
		 */
		rest().description("Servicio GET de saludo  - Definicion")
		.consumes("application/json")
		.produces("application/json")
		
		.get("/Saludo").outType(Greetings.class)
        .responseMessage().code(200).endResponseMessage()
		.to("direct:saludoImpl");
		
		
		 from("direct:saludoImpl").description("Servicio GET de saludo - Implementacion")
		 .streamCaching()
		 .to("bean:greetingsService?method=getGreetings");     
		 

	}
}
