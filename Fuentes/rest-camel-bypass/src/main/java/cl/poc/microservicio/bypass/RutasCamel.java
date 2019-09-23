package cl.poc.microservicio.bypass;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RutasCamel extends RouteBuilder {

    @Autowired
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        rest().get("/hello")
                .to("direct:hello");

        from("direct:hello")
                .log(LoggingLevel.INFO, "Logging :: route hello. returning Hello World")
                .transform().simple("Hello World");

    }
}
