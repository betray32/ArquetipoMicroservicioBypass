package cl.poc.sb.bypass.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import cl.poc.sb.bypass.bean.Employee;

/**
 * Configuracion de las rutas de camel - POST
 * 
 * @author ccontrerasc
 *
 */
@Component
public class RutasCamelPost extends RouteBuilder {

	/**
	 * Endpoint hacia el endpoint post
	 */
	private static final String ENDPOINT_POST = "http://localhost:8080/employee";

	/**
	 * Se indican todas las rutinas camel que deseamos
	 */
	@Override
	public void configure() throws Exception {

		/*
		 * Ejemplo de invocacion hacia servicio que posee rutinas
		 */
		from("direct:consultarPost")
				/*
				 * Se inicia con un procesador que toma el bean de entrada a esta rutina (desde
				 * la clase que expone los clientes rest) y lo setea en la salida del exchange
				 */
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Employee empleado = exchange.getIn().getBody(Employee.class);
						exchange.getOut().setBody(empleado);
					}
				})
				/*
				 * Se indica mediante marshal que se empaquete el bean que estamos seteando
				 * hacia el tipo de dato deseado el cual enviaremos posteriormente hacia un
				 * nuevo servicio
				 */
				.marshal(new JacksonDataFormat(Employee.class))
				/*
				 * Se indica las cabeceras y tipos a consultar
				 */
				.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.POST.name()))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				/*
				 * Endpoint de destino a consultar
				 */
				.to(ENDPOINT_POST)
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
				.log("Salida Servicio POST > ${body}")
				/*
				 * Se finaliza esta rutina
				 */
				.end();

	}

}