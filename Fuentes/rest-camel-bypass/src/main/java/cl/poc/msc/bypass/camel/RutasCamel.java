package cl.poc.msc.bypass.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import cl.poc.msc.bypass.bean.Saludo;

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

		rest("Ejemplos").consumes("application/json").produces("application/json")

		/*
		 * Servicio Get de ejemplo, no recibe parametros y devuelve un objeto complejo
		 * de salida, realiza una implementacion hacia un servicio controlador externo
		 * 
		 * URL: URL: http://localhost:8081/CamelBypass/api/Ejemplos/Get
		 */
		.get("Get")
			.description("Servicio GET de saludo - Sin parametros de entrada y responde un objeto")
			.outType(Saludo.class)
		.to("direct:responseGET");

		from("direct:responseGET")
			.description("Servicio GET de saludo - Implementacion")
			/*
			 * streamCaching - Definicion: 
			 * While stream types (like StreamSource, InputStream and Reader) are commonly
			 * used in messaging for performance reasons, they also have an important
			 * drawback: they can only be read once. In order to be able to work with
			 * message content multiple times, the stream needs to be cached.
			 */
			.streamCaching()
		.to("bean:delegateService?method=salidaGet");
		/********************************************************/

	}
}
