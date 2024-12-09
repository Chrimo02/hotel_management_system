package de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryLocationIdGenerator implements IdGenerator {
    private final AtomicLong locationCounter = new AtomicLong(1);

    @Override
    public String generateId() {
        return String.valueOf(locationCounter.getAndIncrement());
    }
}