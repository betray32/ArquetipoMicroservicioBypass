package cl.poc.microservicio.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cl.poc.microservicio.bean.ClienteBean;
import cl.poc.microservicio.bean.ClienteInputPost;
import cl.poc.microservicio.delegate.ClienteDelegate;

/**
 * Clase que contiene ejemplos de como exponer endpoints rest
 * 
 * @author ccontrerasc
 *
 */
@RestController
public class ClienteRestExposer {

	/**
	 * LOG
	 */
	private static final Log log = LogFactory.getLog(ClienteRestExposer.class);

	@Autowired
	private ClienteDelegate delegate;
	
	/**
	 * Ejemplo de endpoint con metodo POST
	 * 
	 * @param input
	 * @return
	 */
	@PostMapping(value = "ConsultarClientePost")
	@ResponseBody
	public ClienteBean consultarClientePost(@RequestBody ClienteInputPost input) {
		
		log.info("------------------------------------------------------------");
		log.info("Se ha iniciado una solicitud hacia el metodo [consultarClientePost]");
		log.info("[REQUEST] = " + new Gson().toJson(input));

		ClienteBean salida = delegate.obtenerCliente(input);

		log.info("Se ha finalizado una solicitud hacia el metodo [consultarClientePost]");
		log.info("[RESPONSE] = " + new Gson().toJson(salida));
		log.info("------------------------------------------------------------");

		return salida;
		
	}

	/**
	 * Ejemplo de endpoint con metodo GET, con parametro por path
	 * 
	 * Se consume: curl http://localhost:8081/ArquetipoMicroservicio/ConsultarCliente/51266633
	 * 
	 * @param rut
	 * @return
	 */
	@GetMapping(value = "ConsultarCliente/{rut}")
	@ResponseBody
	public ClienteBean consultarCliente(@PathVariable String rut) {

		log.info("------------------------------------------------------------");
		log.info("Se ha iniciado una solicitud hacia el metodo [consultarCliente]");
		log.info("[REQUEST] RUT = " + rut);

		ClienteBean salida = delegate.obtenerCliente(rut);

		log.info("Se ha finalizado una solicitud hacia el metodo [consultarCliente]");
		log.info("[RESPONSE] = " + new Gson().toJson(salida));
		log.info("------------------------------------------------------------");

		return salida;
	}
	
	/**
	 * Ejemplo de endpoint con metodo GET con parametro por path y por request
	 * 
	 * Se consume: curl http://localhost:8081/ArquetipoMicroservicio/ConsultarClienteParam/51266633?nombre=EJEMPLO_NOMBRE
	 * 
	 * @param rut
	 * @return
	 */
	@GetMapping(value = "ConsultarClienteParam/{rut}")
	@ResponseBody
	public ClienteBean consultarClienteParam
	(
			@PathVariable String rut, 
			@RequestParam(value="nombre", defaultValue="Fredy Mercuris") String nombre
	) {

		log.info("------------------------------------------------------------");
		log.info("Se ha iniciado una solicitud hacia el metodo [consultarClienteParam]");
		log.info("[REQUEST] RUT = " + rut + " NOMBRE = " + nombre);

		ClienteBean salida = delegate.obtenerCliente(rut, nombre);

		log.info("Se ha finalizado una solicitud hacia el metodo [consultarClienteParam]");
		log.info("[RESPONSE] = " + new Gson().toJson(salida));
		log.info("------------------------------------------------------------");

		return salida;
	}

}
