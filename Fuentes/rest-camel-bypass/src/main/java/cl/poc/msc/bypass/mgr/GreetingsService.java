package cl.poc.msc.bypass.mgr;

import cl.poc.msc.bypass.bean.Greetings;

/**
 * GreetingsService - Definicion
 * 
 * @author ccontrerasc
 *
 */
public interface GreetingsService {

	/**
	 * Generate Greetings
	 *
	 * @return a string greetings
	 */
	public Greetings getGreetings();

}