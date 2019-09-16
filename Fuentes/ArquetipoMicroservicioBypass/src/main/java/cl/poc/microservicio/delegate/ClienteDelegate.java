package cl.poc.microservicio.delegate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import cl.poc.microservicio.bean.ClienteBean;
import cl.poc.microservicio.bean.ClienteInputPost;
import cl.poc.microservicio.helper.Utiles;

/**
 * 
 * @author ccontrerasc
 *
 */
@Service
public class ClienteDelegate {

	/**
	 * LOG
	 */
	private static final Log log = LogFactory.getLog(ClienteDelegate.class);
	
	/**
	 * Realizar logica de negocio sobre el cliente
	 * 
	 * @param input
	 * @return
	 */
	public ClienteBean obtenerCliente(ClienteInputPost input) {

		log.info("Inicializando Delegate");

		if (Utiles.validarRut(input.getRut())) {

			log.info("Rut Valido, Generando Salida...");
			String rutValidado = input.getRut();

			ClienteBean cliente = new ClienteBean();
			cliente.setRut(rutValidado);
			cliente.setNombre(input.getNombre());

			return cliente;
		} else {
			// ERROR
			log.error("Rut Invalido");
		}

		return null;

	}

	/**
	 * Realizar logica de negocio sobre el cliente
	 * 
	 * @param input
	 * @return
	 */
	public ClienteBean obtenerCliente(String rut) {

		log.info("Inicializando Delegate");

		if (Utiles.validarRut(rut)) {

			log.info("Rut Valido, Generando Salida...");
			String rutValidado = rut;

			ClienteBean cliente = new ClienteBean();
			cliente.setRut(rutValidado);
			cliente.setNombre("Aquiles Brinco");

			return cliente;
		} else {
			// ERROR
			log.error("Rut Invalido");
		}

		return null;

	}

	/**
	 * Realizar logica de negocio sobre el cliente
	 * 
	 * @param input
	 * @return
	 */
	public ClienteBean obtenerCliente(String rut, String nombre) {
		log.info("Inicializando Delegate");

		if (Utiles.validarRut(rut)) {

			log.info("Rut Valido, Generando Salida...");
			String rutValidado = rut;

			ClienteBean cliente = new ClienteBean();
			cliente.setRut(rutValidado);
			cliente.setNombre(nombre);

			return cliente;
		} else {
			// ERROR
			log.error("Rut Invalido");
		}

		return null;
	}

}
