package cl.poc.msc.bypass.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import cl.poc.msc.bypass.bean.Employee;
import cl.poc.msc.bypass.bean.PersonaInput;
import cl.poc.msc.bypass.bean.SalidaGenerica;
import cl.poc.msc.bypass.bean.SaludoOutput;

/**
 * Rutinas camel
 * 
 * @author ccontrerasc
 *
 */
@Component
public class RutasCamel extends RouteBuilder {
	
	/**
	 * Endpoint para el servicio GET
	 */
	private static final String ENDPOINT_GETBYPASS = "http://localhost:8080/employee?id=5";

	/**
	 * Configuracion de camel
	 */
	@Override
	public void configure() throws Exception {

		rest("Ejemplos").consumes("application/json").produces("application/json")

		/*******************************************************************************
		 * Servicio Get de ejemplo, no recibe parametros y devuelve un objeto complejo
		 * de salida, realiza una implementacion hacia un servicio controlador externo
		 * 
		 * URL: localhost:8081/CamelBypass/api/Ejemplos/Get
		 */
		.get("Get")
		.outType(SaludoOutput.class)
		.description("Servicio GET Ejemplo- Sin parametros de entrada y responde un objeto")
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responseGET")
		
		/*******************************************************************************
		 * Servicio Get de ejemplo, recibe un parametro de entrada y devuelve un objeto 
		 * complejo de salida
		 * 
		 * URL: localhost:8081/CamelBypass/api/Ejemplos/GetParam/Camilo
		 */
		.get("GetParam/{nombre}")
		.outType(SaludoOutput.class)
		.description("Servicio GET Ejemplo - Un parametro de entrada y un objeto de salida")
			/*
			 * Documentacion de entrada
			 */
			.param().name("nombre").type(RestParamType.path).description("Nombre del usuario, ejemplo")
			.required(true).dataType("String").endParam()
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responseGETParam")
		
		/*******************************************************************************
		 * Servicio Get de ejemplo, ejemplo de bypass para otro servicio rest
		 * 
		 * URL: http://localhost:8081/CamelBypass/api/Ejemplos/GetBypass
		 */
		.get("GetBypass")
		.outType(Employee.class)
		.description("Servicio GET para bypass - permite encapsular la llamada hacia otro recurso rest")
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(Employee.class).endResponseMessage()
		.to("direct:responseGetBypass")
		
		/*******************************************************************************
		 * Servicio POST de ejemplo, recibe un objeto complejo de entrada y devuelve
		 * otro de salida
		 * 
		 * URL: http://localhost:8081/CamelBypass/api/Ejemplos/Post
		 */
		.post("Post")
		.type(PersonaInput.class)
		.outType(SaludoOutput.class)
		.description("Servicio POST Ejemplo - Recibe un objeto y devuelve otro")
			/*
			 * Documentacion de entrada
			 */
			.param().name("Cliente").type(RestParamType.body).description("Cliente a consultar")
			.required(true).dataType("Object").endParam()
			.responseMessage().code(200).responseModel(SalidaGenerica.class).endResponseMessage()
			.responseMessage().code(400).responseModel(SalidaGenerica.class).message("Unexpected body").endResponseMessage()
			.responseMessage().code(500).responseModel(SalidaGenerica.class).endResponseMessage()
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responsePOST")
		;

		/**
		 * Interceptores, desde aca entran los flujos desde los distintos endpoints y se realiza
		 * la logica de negocio respectiva de salida
		 */
		
		/**************************************************************************
		 * Rutina para el servicio GET
		 */
		from("direct:responseGET")
			.description("Servicio GET - Implementacion")
		.to("bean:delegateService?method=salidaGet");
		
		/**************************************************************************
		 * Rutina para el servicio GET con parametro
		 */
		from("direct:responseGETParam")
			.description("Servicio GET con parametro - Implementacion")
			.to("bean:delegateService?method=salidaGetParam(${header.nombre})");
		
		/**************************************************************************
		 * Rutina para el servicio POST
		 */
		from("direct:responsePOST")
			.description("Servicio POST - Implementacion")
			.to("bean:delegateService?method=salidaPost(${body})");
		
		/**************************************************************************
		 *  Rutina para el servicio Get de bypass
		 */
		from("direct:responseGetBypass")
			/*
			 * Al llamar a otra api en modo de bypass, se deben de eliminar los 
			 * headers caso contrario no funcionara la rutina
			 */
		 	.removeHeaders("CamelHttp*")
			/*
			 * Se indica las cabeceras y tipos a consultar
			 */
			.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.GET.name()))
			.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
			/*
			 * Endpoint de destino a consultar
			 */
			.to(ENDPOINT_GETBYPASS)
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
			.end()

		;


	}
}
