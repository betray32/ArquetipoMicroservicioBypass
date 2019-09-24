package cl.poc.msc.bypass.mgr;

import cl.poc.msc.bypass.bean.Saludo;

/**
 * GreetingsService - Definicion
 * 
 * @author ccontrerasc
 *
 */
public interface DelegateService {

	/**
	 * Generate Greetings
	 *
	 * @return a string greetings
	 */
	public Saludo salidaGet();

}