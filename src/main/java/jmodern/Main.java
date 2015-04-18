package jmodern;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class Main extends Application<Main.JModernConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public void initialize(Bootstrap<JModernConfiguration> bootstrap) {
    }

    @Override
    public void run(JModernConfiguration cfg, Environment env) {
        JmxReporter.forRegistry(env.metrics()).build().start(); // Manually add JMX reporting (Dropwizard regression)

        env.jersey().register(new HelloWorldResource(cfg));
    }

    // YAML Configuration
    public static class JModernConfiguration extends Configuration {
        @JsonProperty private @NotEmpty String template;
        @JsonProperty private @NotEmpty String defaultName;

        public String getTemplate()    { return template; }
        public String getDefaultName() { return defaultName; }
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
            final String value = String.format(template, name.or(defaultName));
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
}