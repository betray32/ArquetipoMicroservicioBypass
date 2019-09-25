package cl.poc.msc.bypass.bean;

/**
 * Salida de ejemplo generica
 */
public class SalidaGenerica {

	private String codigo;
	private String mensaje;

	/** GET Y SET **/
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "SalidaGenerica [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}

}
