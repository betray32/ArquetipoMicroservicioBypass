package cl.poc.microservicio.bean;

/**
 * Ejemplo de estructura de entrada para un endpoint post
 * 
 * @author ccontrerasc
 *
 */
public class ClienteInputPost {

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
		return "ClienteInputPost [rut=" + rut + ", nombre=" + nombre + "]";
	}

}
