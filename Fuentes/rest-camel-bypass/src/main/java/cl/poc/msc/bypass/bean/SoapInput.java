package cl.poc.msc.bypass.bean;

import java.math.BigDecimal;

import com.dataaccess.webservicesserver.NumberToDollars;

/**
 * Genera un request para solicitud del servicio soap, se referencia hacia el
 * bean generado automaticamente mediante maven
 * 
 * @author ccontrerasc
 *
 */
public class SoapInput {

	/**
	 * Obtener la entrada para el servicio soap
	 * 
	 * @param numero
	 * @return
	 */
	public NumberToDollars build(String numero) {

		NumberToDollars request = new NumberToDollars();
		request.setDNum(new BigDecimal(numero));
		return request;
	}

}
