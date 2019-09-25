package cl.poc.msc.bypass.bean;

/**
 * Dto ejemplo para entrada de datos
 * 
 * @author ccontrerasc
 *
 */
public class PersonaInput {

	private String rut;
	private String nroCuenta;

	/** GET Y SET **/
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	@Override
	public String toString() {
		return "PersonaInput [rut=" + rut + ", nroCuenta=" + nroCuenta + "]";
	}

}