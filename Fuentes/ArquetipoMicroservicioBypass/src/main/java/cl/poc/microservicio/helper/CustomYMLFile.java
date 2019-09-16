package cl.poc.microservicio.helper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * CustomYMLFile
 * 
 * @author ccontrerasc
 *
 */
@Component
@ConfigurationProperties
public class CustomYMLFile {

	/**
	 * Version del Microservicio
	 */
	private String VersionMicroservicio;

	/**
	 * Definicion de clases
	 */
	private Procedures Procedures;
	private Endpoints Endpoints;

	/**
	 * Procedimientos
	 * 
	 * @author ccontrerasc
	 *
	 */
	public static class Procedures {

		private String ConsultaCliente;

		/** GET Y SET **/
		public String getConsultaCliente() {
			return ConsultaCliente;
		}

		public void setConsultaCliente(String consultaCliente) {
			ConsultaCliente = consultaCliente;
		}

	}

	/**
	 * Endpoints
	 * 
	 * @author ccontrerasc
	 *
	 */
	public static class Endpoints {
		private String ConsultaAutenticacionCliente;

		/** GET Y SET **/
		public String getConsultaAutenticacionCliente() {
			return ConsultaAutenticacionCliente;
		}

		public void setConsultaAutenticacionCliente(String consultaAutenticacionCliente) {
			ConsultaAutenticacionCliente = consultaAutenticacionCliente;
		}

	}

	public String getVersionMicroservicio() {
		return VersionMicroservicio;
	}

	public void setVersionMicroservicio(String versionMicroservicio) {
		VersionMicroservicio = versionMicroservicio;
	}

	public Procedures getProcedures() {
		return Procedures;
	}

	public void setProcedures(Procedures procedures) {
		Procedures = procedures;
	}

	public Endpoints getEndpoints() {
		return Endpoints;
	}

	public void setEndpoints(Endpoints endpoints) {
		Endpoints = endpoints;
	}

}
