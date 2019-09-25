package cl.poc.msc.bypass.bean;

/**
 * Salida encapsulada para el servicio SOAP
 * 
 * @author ccontrerasc
 *
 */
public class SoapResponse {

	String valorEnDolares;

	public SoapResponse(String valorEnDolares) {
		super();
		this.valorEnDolares = valorEnDolares;
	}

	/** GET Y SET **/
	public String getValorEnDolares() {
		return valorEnDolares;
	}

	public void setValorEnDolares(String valorEnDolares) {
		this.valorEnDolares = valorEnDolares;
	}

	@Override
	public String toString() {
		return "SoapResponse [valorEnDolares=" + valorEnDolares + "]";
	}

}
