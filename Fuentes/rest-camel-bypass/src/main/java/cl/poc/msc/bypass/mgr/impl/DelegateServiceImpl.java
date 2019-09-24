package cl.poc.msc.bypass.mgr.impl;

import org.springframework.stereotype.Service;

import cl.poc.msc.bypass.bean.Saludo;
import cl.poc.msc.bypass.mgr.DelegateService;

/**
 * GreetingsServiceImpl - Implementacion
 * 
 * @author ccontrerasc
 *
 */
@Service("delegateService")
public class DelegateServiceImpl implements DelegateService {

	/**
	 * Saludar
	 */
	@Override
	public Saludo salidaGet() {

		Saludo salida = new Saludo();
		salida.setOrigen("CAMEL REST API");
		salida.setMensaje("Invocando desde rest api camel");

		return salida;
	}

}