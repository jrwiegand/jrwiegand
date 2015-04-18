package jmodern;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import com.sun.jersey.api.client.Client;
import feign.Feign;
import feign.jackson.*;
import feign.jaxrs.*;

public class Main extends Application<Main.JModernConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public void initialize(Bootstrap<JModernConfiguration> bootstrap) {
    }

    @Override
    public void run(JModernConfiguration cfg, Environment env) {
        Feign.Builder feignBuilder = Feign.builder()
                .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
                .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
                .decoder(new JacksonDecoder());
        env.jersey().register(new ConsumerResource(feignBuilder));
    }

    // YAML Configuration
    public static class JModernConfiguration extends Configuration {
        @JsonProperty private @NotEmpty String template;
        @JsonProperty private @NotEmpty String defaultName;

        public String getTemplate()    { return template; }
        public String getDefaultName() { return defaultName; }

        @Valid @NotNull @JsonProperty JerseyClientConfiguration httpClient = new JerseyClientConfiguration();
        public JerseyClientConfiguration getJerseyClientConfiguration() { return httpClient; }
    }

    // The actual service
    @Path("/hello-world")
    @Produces(MediaType.APPLICATION_JSON)
    public static class HelloWorldResource {
        private final AtomicLong counter = new AtomicLong();
        private final String template;
        private final String defaultName;

        public HelloWorldResource(JModernConfiguration cfg) {
            this.template = cfg.getTemplate();
            this.defaultName = cfg.getDefaultName();
        }

        @Timed // monitor timing of this service with Metrics
        @GET
        public Saying sayHello(@QueryParam("name") Optional<String> name) throws InterruptedException {
            final String value = String.format(template, name.of(defaultName));
            Thread.sleep(ThreadLocalRandom.current().nextInt(10, 500));
            return new Saying(counter.incrementAndGet(), value);
        }
    }

    // JSON (immutable!) payload
    public static class Saying {
        private long id;
        private @Length(max = 10) String content;

        public Saying(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public Saying() {} // required for deserialization

        @JsonProperty public long getId() { return id; }
        @JsonProperty public String getContent() { return content; }
    }

    @Path("/consumer")
    @Produces(MediaType.TEXT_PLAIN)
    public static class ConsumerResource {
        private final HelloWorldAPI hellowWorld;

        public ConsumerResource(Feign.Builder feignBuilder) {
            this.hellowWorld = feignBuilder.target(HelloWorldAPI.class, "http://localhost:8080");
        }

        @Timed
        @GET
        public String consume() {
            Saying saying = hellowWorld.hi("consumer");
            return String.format("The service is saying: %s (id: %d)",  saying.getContent(), saying.getId());
        }
    }

    interface HelloWorldAPI {
        @GET @Path("/hello-world")
        Saying hi(@QueryParam("name") String name);

        @GET @Path("/hello-world")
        Saying hi();
    }
}