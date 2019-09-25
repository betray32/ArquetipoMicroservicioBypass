package cl.poc.msc.bypass.bean;

/**
 * Request para el servicio post que encapsula al soap
 * 
 * @author ccontrerasc
 *
 */
public class SoapRequest {

	private String numero;

	/** GET Y SET **/
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "SoapRequest [numero=" + numero + "]";
	}

}
