package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.HotelLocation;
import org.junit.jupiter.api.Test;

public class HotelLocationTest {

    @Test
    public void testHotelLocationBuilder_AllFields() {
        HotelLocation location = new HotelLocation.HotelLocationBuilder()
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();

        assertNull(location.getId(), "ID sollte null sein, wenn sie nicht explizit gesetzt wurde");
        assertEquals("123 Main St", location.getAddress(), "Address sollte '123 Main St' sein");
        assertEquals("TestCity", location.getCity(), "City sollte 'TestCity' sein");
        assertEquals("TestCountry", location.getCountry(), "Country sollte 'TestCountry' sein");
    }


    @Test
    public void testSettersAndGetters() {
        HotelLocation location = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("Old Address")
                .withCity("OldCity")
                .withCountry("OldCountry")
                .build();

        assertEquals("Old Address", location.getAddress());
        assertEquals("OldCity", location.getCity());
        assertEquals("OldCountry", location.getCountry());

        location.setId(100L);
        location.setAddress("New Address");
        location.setCity("NewCity");
        location.setCountry("NewCountry");

        assertEquals(100L, location.getId());
        assertEquals("New Address", location.getAddress());
        assertEquals("NewCity", location.getCity());
        assertEquals("NewCountry", location.getCountry());
    }
}
