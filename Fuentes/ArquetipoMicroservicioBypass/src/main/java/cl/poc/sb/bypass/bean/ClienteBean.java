package cl.poc.sb.bypass.bean;

/**
 * Pojo para el cliente
 * 
 * @author ccontrerasc
 *
 */
public class ClienteBean {

	private String rut;
	private String nombre;

	/** GET Y SET **/
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ClienteBean [rut=" + rut + ", nombre=" + nombre + "]";
	}

}
