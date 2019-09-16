package cl.poc.microservicio.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import cl.poc.microservicio.bean.Employee;

/**
 * Ejemplo de clase que implementa un procesador de manera externa, al momento
 * de definir las rutinas se puede instanciar una clase processor o bien
 * externalizarla como es este ejemplo e indicarla al momento de definir la
 * rutina camel
 * 
 * @author ccontrerasc
 *
 */
@Component
public class EmployeeProcessor implements Processor {

	/**
	 * Procesamiento, lo que se hace aca es obtener la salida desde el endpoint
	 * consultado y setearlo como salida de la rutina (el objeto complejo ahora es
	 * salida)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		Employee salida = exchange.getIn().getBody(Employee.class);
		exchange.getOut().setBody(salida);
	}

}
