package de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryHotelIdGenerator implements IdGenerator {
    private final AtomicLong hotelCounter = new AtomicLong(1);

    @Override
    public String generateId() {
        return String.valueOf(hotelCounter.getAndIncrement());
    }
}