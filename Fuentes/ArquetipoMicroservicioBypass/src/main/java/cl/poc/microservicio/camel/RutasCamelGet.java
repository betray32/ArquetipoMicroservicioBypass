package cl.poc.microservicio.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import cl.poc.microservicio.bean.Employee;

/**
 * Configuracion de las rutas de camel - GET
 * 
 * @author ccontrerasc
 *
 */
@Component
public class RutasCamelGet extends RouteBuilder {

	/**
	 * Endpoint para el servicio GET
	 */
	private static final String ENDPOINT_GET = "http://localhost:8080/employee?id=5";

	/**
	 * Se indican todas las rutinas camel que deseamos
	 */
	@Override
	public void configure() throws Exception {

		/*
		 * Ejemplo de invocacion hacia servicio GET con rutinas tipicas de camel
		 */
		from("direct:consultarGET")
				/*
				 * Se indica las cabeceras y tipos a consultar
				 */
				.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.GET.name()))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				/*
				 * Endpoint de destino a consultar
				 */
				.to(ENDPOINT_GET)
				/*
				 * Se especifica que se desea desempaquetar el mensaje (la salida) desde el
				 * servicio invocado indicandole el tipo de bean correspondiente
				 */
				.unmarshal(new JacksonDataFormat(Employee.class))
				/*
				 * Se toma el bean de salida de la rutina y se setea en la salida de esta rutina
				 */
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Employee salida = exchange.getIn().getBody(Employee.class);
						exchange.getOut().setBody(salida);
					}
				})
				/*
				 * Se despliega el contenido obtenido del mensaje
				 */
				.log("Salida Servicio GET > ${body}")
				/*
				 * Se finaliza esta rutina
				 */
				.end();

	}

}