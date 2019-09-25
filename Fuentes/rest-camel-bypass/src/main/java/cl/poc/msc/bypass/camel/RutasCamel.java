package cl.poc.msc.bypass.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

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
		.get("Get").outType(SaludoOutput.class)
			.description("Servicio GET Ejemplo- Sin parametros de entrada y responde un objeto")
			/*
			 * Documentacion del servicio, se documenta la salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responseGET")
		
		/*******************************************************************************
		 * Servicio Get de ejemplo, recibe un parametro de entrada y devuelve un objeto 
		 * complejo de salida
		 * 
		 * URL: localhost:8081/CamelBypass/api/Ejemplos/GetParam/Camilo
		 */
		.get("GetParam/{nombre}").outType(SaludoOutput.class)
			/*
			 * Documentacion del servicio, se documenta la entrada y la salida
			 */
			.description("Servicio GET Ejemplo - Un parametro de entrada y un objeto de salida")
			.param().name("nombre").type(RestParamType.path).description("Nombre del usuario, ejemplo")
			.required(true).dataType("String").endParam()
			/*
			 * Salida
			 */
			.responseMessage().code(200).responseModel(SaludoOutput.class).endResponseMessage()
		.to("direct:responseGETParam")
		
		/*******************************************************************************
		 * Servicio POST de ejemplo, recibe un objeto complejo de entrada y devuelve
		 * otro de salida
		 * 
		 * URL:
		 */
		
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
		;


	}
}
