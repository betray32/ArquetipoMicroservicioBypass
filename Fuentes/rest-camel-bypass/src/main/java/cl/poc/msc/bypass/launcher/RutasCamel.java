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

	@Override
	public void configure() throws Exception {

		rest().get("/hello")
		.to("direct:hello");

		from("direct:hello")
		.log(LoggingLevel.INFO, "Logging :: route hello. returning Hello World")
		.transform()
		.simple("Hello World");

	}
}
