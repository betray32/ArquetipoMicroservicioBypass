package cl.poc.msc.bypass.mgr.impl;

import org.springframework.stereotype.Service;

import cl.poc.msc.bypass.bean.Greetings;
import cl.poc.msc.bypass.mgr.GreetingsService;

/**
 * GreetingsServiceImpl - Implementacion
 * 
 * @author ccontrerasc
 *
 */
@Service("greetingsService")
public class GreetingsServiceImpl implements GreetingsService {

	/**
	 * Saludar
	 */
	@Override
	public Greetings getGreetings() {
		return new Greetings("Que sucede realmente?");
	}

}