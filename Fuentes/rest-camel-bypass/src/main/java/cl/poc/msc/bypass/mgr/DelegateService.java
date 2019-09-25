package cl.poc.msc.bypass.mgr;

import cl.poc.msc.bypass.bean.PersonaInput;
import cl.poc.msc.bypass.bean.SaludoOutput;

/**
 * GreetingsService - Definicion
 * 
 * @author ccontrerasc
 *
 */
public interface DelegateService {

	/**
	 * Salida para el recurso GET
	 *
	 * @return a string greetings
	 */
	public SaludoOutput salidaGet();

	/**
	 * Salida para el recurso GET con parametro
	 * 
	 * @param param
	 * @return
	 */
	public SaludoOutput salidaGetParam(String param);

	/**
	 * Salida para el recurso POST
	 * 
	 * @param input
	 * @return
	 */
	public SaludoOutput salidaPost(PersonaInput input);

}
