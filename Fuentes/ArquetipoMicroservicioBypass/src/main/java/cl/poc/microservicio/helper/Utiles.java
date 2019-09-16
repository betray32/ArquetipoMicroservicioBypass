package cl.poc.microservicio.helper;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utiles
 * 
 * @author ccontrerasc
 *
 */
public class Utiles {

	/**
	 * LOG
	 */
	private static final Log log = LogFactory.getLog(Utiles.class);

	/**
	 * Validar rut chileno
	 * 
	 * @param rut
	 * @return
	 */
	public static boolean validarRut(String rut) {

		boolean validacion = false;
		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (java.lang.NumberFormatException e) {
			log.error("Rut ingresado incorrecto > " + rut);
		} catch (Exception e) {
			log.error("Rut ingresado incorrecto > " + rut);
		}
		return validacion;
	}

	/**
	 * SimpleDateFormat Fecha
	 */
	public static SimpleDateFormat formatoDate = new SimpleDateFormat("dd-MM-yy");

	/**
	 * 
	 * Metodo encargado de formatear los decimales a dos numeros despues de la coma
	 * 
	 * @param numeroFormatea
	 * 
	 * @return
	 * 
	 */
	public static double formateaDecimal(double numeroFormatea) {
		return (double) Math.round(numeroFormatea * 100d) / 100d;
	}

	/**
	 * Obtener IP
	 * 
	 * @return
	 */
	public static String obtenerIP() {
		return InetAddress.getLoopbackAddress().getHostAddress();
	}

	/**
	 * obtenerUsuario
	 * 
	 * @return
	 */
	public static String obtenerUsuario() {
		return System.getProperty("user.name");
	}

	/**
	 * Permite limpiar los signos
	 * 
	 * @param input
	 * @return
	 */
	public static String limpiarSignos(String input) {

		String res = "";
		if (input != null && !"".equals(input)) {
			res = input.replaceAll("[^\\dA-Za-z ]", "");
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Limpia el rut de caracteres extras
	 * 
	 * @param rutLimpia
	 * @return
	 */
	public static String limpiaRut(String rutLimpia) {

		String sinPunto = rutLimpia.replace(".", "");
		return sinPunto.replace("-", "");

	}

	/**
	 * Obtiene la fecha y hora especifica
	 * 
	 * @return
	 */
	public static java.sql.Date getDateTime() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

	/**
	 * Retorna fecha formateada para ser insertada en BD
	 * 
	 * @param fechaFormatea
	 * @param formatoDeseado
	 * @return
	 */
	public static Date formateaFechaSQL(String fechaFormatea, SimpleDateFormat formatoDeseado) {
		java.util.Date fechaInicio;
		Date fechaSQL = null;
		try {
			fechaInicio = formatoDeseado.parse(fechaFormatea);
			fechaSQL = new java.sql.Date(fechaInicio.getTime());
		} catch (ParseException e) {
			return null;
		}

		return fechaSQL;
	}

	/**
	 * Desencriptador base 64
	 * 
	 * @param input
	 * @return
	 */
	public static String base64Decoder(String input) {

		byte[] decodedReceptor = Base64.decodeBase64(input);
		String RES_HTML = null;

		try {
			RES_HTML = new String(decodedReceptor, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("No se ha podido desencriptar el base 64, ERROR > " + e.getMessage());
		}

		return RES_HTML;
	}

}
