package cl.poc.msc.bypass.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import cl.poc.msc.bypass.bean.Employee;
import cl.poc.msc.bypass.bean.PersonaInput;
import cl.poc.msc.bypass.bean.SalidaGenerica;
import cl.poc.msc.bypass.bean.SaludoOutput;
import cl.poc.msc.bypass.bean.SoapRequest;
import cl.poc.msc.bypass.bean.SoapResponse;

/**
 * Expositor de servicio rest en camel
 * 
 * @author ccontrerasc
 *
 */
@Component
public class RestCamel extends RouteBuilder {

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
			.param().name("PersonaInput").type(RestParamType.body).description("Cliente a consultar")
			.required(true).dataType("Object").endParam()
			.responseMessage().code(200).responseModel(SalidaGenerica.class).endResponseMessage()
			.responseMessage().code(400).responseModel(SalidaGenerica.class).message("Unexpected body").endResponseMessage()
			.responseMessage().code(500).responseModel(SalidaGenerica.class).endResponseMessage()
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responsePost")
		
		/*******************************************************************************
		 * Servicio POST REST de ejemplo, que llama a otro servicio del tipo SOAP
		 * 
		 * URL: 
		 */
		.post("PostBypass")
		.type(SoapRequest.class)
		.outType(SoapResponse.class)
		.description("Servicio Post de ejemplo, permite llamar mediante camel y cxf un servicio SOAP")
			/*
			 * Documentacion de entrada
			 */
			.param().name("SoapRequest").type(RestParamType.body).description("Cantidad a transformar")
			.required(true).dataType("Object").endParam()
			.responseMessage().code(200).responseModel(SalidaGenerica.class).endResponseMessage()
			.responseMessage().code(400).responseModel(SalidaGenerica.class).message("Unexpected body").endResponseMessage()
			.responseMessage().code(500).responseModel(SalidaGenerica.class).endResponseMessage()
			/*
			 * Documentacion de salida
			 */
			.responseMessage().code(200).responseModel(SoapResponse.class).endResponseMessage()
		.to("direct:responsePostBypass")
		;
		
	}

}
