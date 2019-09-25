package cl.poc.msc.bypass.bean;

/**
 * Dto de saludo
 * 
 * @author ccontrerasc
 *
 */
public class SaludoOutput {

	private String origen;
	private String mensaje;
	private String author;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Saludo [origen=" + origen + ", mensaje=" + mensaje + ", author=" + author + "]";
	}

}