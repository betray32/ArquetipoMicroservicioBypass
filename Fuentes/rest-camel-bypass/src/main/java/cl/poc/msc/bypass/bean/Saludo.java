package cl.poc.msc.bypass.bean;

/**
 * Dto de saludo
 * 
 * @author ccontrerasc
 *
 */
public class Saludo {

	private String origen;
	private String mensaje;

	/** GET Y SET **/
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Saludo [origen=" + origen + ", mensaje=" + mensaje + "]";
	}

}