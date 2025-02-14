package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import org.junit.jupiter.api.Test;

public class HotelLocationEntityTest {

    @Test
    public void testHotelLocationBuilderAndGetters() {
        HotelLocationEntity location = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();

        assertEquals("123 Main St", location.getAddress());
        assertEquals("TestCity", location.getCity());
        assertEquals("TestCountry", location.getCountry());
    }

    @Test
    public void testSetters() {
        HotelLocationEntity location = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("Old Address")
                .withCity("OldCity")
                .withCountry("OldCountry")
                .build();

        location.setAddress("New Address");
        location.setCity("NewCity");
        location.setCountry("NewCountry");

        assertEquals("New Address", location.getAddress());
        assertEquals("NewCity", location.getCity());
        assertEquals("NewCountry", location.getCountry());
    }
}
