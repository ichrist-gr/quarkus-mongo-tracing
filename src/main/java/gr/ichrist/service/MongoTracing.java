package gr.ichrist.service;

import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@LookupIfProperty(name = "mongo-tracing.enabled", stringValue = "true")
public class MongoTracing implements CommandListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTracing.class);

    @Override
    public void commandStarted(CommandStartedEvent event) {
        LOGGER.info("Mongo execution: {}", event.getCommand());
    }

    @Override
    public void commandSucceeded(CommandSucceededEvent event) {
        LOGGER.info("Mongo result: {}", event.getResponse());
    }

    @Override
    public void commandFailed(CommandFailedEvent event) {
        LOGGER.error("Mongo failed: {}", event.getThrowable().getMessage());
    }
}
