package cl.poc.msc.bypass.mgr.impl;

import org.springframework.stereotype.Service;

import cl.poc.msc.bypass.bean.SaludoOutput;
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
	 * Salida para el recurso GET
	 */
	@Override
	public SaludoOutput salidaGet() {

		SaludoOutput salida = new SaludoOutput();
		salida.setOrigen("CAMEL REST API");
		salida.setMensaje("Invocando desde rest api camel");
		salida.setAuthor("SPRING BOOT");

		return salida;
	}

	/**
	 * Salida para el recurso GET con parametro
	 */
	@Override
	public SaludoOutput salidaGetParam(String param) {
		SaludoOutput salida = new SaludoOutput();
		salida.setOrigen("CAMEL REST API");
		salida.setMensaje("Invocando desde rest api camel con parametro");
		salida.setAuthor(param);

		return salida;
	}

}