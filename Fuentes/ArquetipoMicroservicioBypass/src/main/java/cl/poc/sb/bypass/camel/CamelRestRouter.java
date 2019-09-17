package cl.poc.sb.bypass.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Service;

/**
 * Expositor de servicio rest mediante camel
 * 
 * @author ccontrerasc
 *
 */
@Service
public class CamelRestRouter extends RouteBuilder {

	/**
	 * Configuracion de la rutina
	 * https://stackoverflow.com/questions/46798079/apache-camel-rest-endpoint-with-dsl-in-spring-boot-adds-camel-to-the-path
	 */
	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet")
	      .contextPath("/my")
	      .apiContextPath("/api-doc")
	      .apiProperty("api.title", "My REST API")
	      .apiProperty("cors", "true")
	      .apiContextRouteId("my-api")
	      .bindingMode(RestBindingMode.json);

	rest("/my").description("My REST Services")
	      .get("foo/{id}").route().routeId("foo")
	      .to("direct:foo");

	from("direct:foo")
	      .log("Done");
		
	}
}
