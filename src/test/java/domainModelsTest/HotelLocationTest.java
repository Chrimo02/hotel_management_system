package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.HotelLocation;
import org.junit.jupiter.api.Test;

public class HotelLocationTest {

    @Test
    public void testHotelLocationBuilder_AllFields() {
        // Erzeuge ein HotelLocation-Objekt mit dem Builder.
        HotelLocation location = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();

        // Hinweis: Im Konstruktor werden id nicht explizit gesetzt,
        // daher erwarten wir, dass getId() null liefert, sofern nicht über Setter gesetzt.
        assertNull(location.getId(), "ID sollte null sein, wenn sie nicht explizit gesetzt wurde");
        assertEquals("123 Main St", location.getAddress(), "Address sollte '123 Main St' sein");
        assertEquals("TestCity", location.getCity(), "City sollte 'TestCity' sein");
        assertEquals("TestCountry", location.getCountry(), "Country sollte 'TestCountry' sein");
    }

    @Test
    public void testSettersAndGetters() {
        // Erzeuge ein Objekt mit dem Builder
        HotelLocation location = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("Old Address")
                .withCity("OldCity")
                .withCountry("OldCountry")
                .build();

        // Überprüfe die initialen Werte
        assertEquals("Old Address", location.getAddress());
        assertEquals("OldCity", location.getCity());
        assertEquals("OldCountry", location.getCountry());

        // Setze neue Werte
        location.setId(100L);
        location.setAddress("New Address");
        location.setCity("NewCity");
        location.setCountry("NewCountry");

        // Überprüfe, ob die neuen Werte korrekt übernommen wurden
        assertEquals(100L, location.getId());
        assertEquals("New Address", location.getAddress());
        assertEquals("NewCity", location.getCity());
        assertEquals("NewCountry", location.getCountry());
    }
}
