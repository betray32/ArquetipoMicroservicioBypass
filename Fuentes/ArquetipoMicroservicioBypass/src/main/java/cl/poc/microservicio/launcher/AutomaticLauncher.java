package cl.poc.microservicio.launcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Rutina de ejemplo que permite ejecutarse al inicializar el componente
 * 
 * @author ccontrerasc
 *
 */
@Component
public class AutomaticLauncher implements CommandLineRunner {

	/**
	 * LOG
	 */
	private static final Log log = LogFactory.getLog(AutomaticLauncher.class);

	/**
	 * Se ejecuta de manera automatica
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info("Run Automatico desde [CommandLineRunner]");
	}

}
