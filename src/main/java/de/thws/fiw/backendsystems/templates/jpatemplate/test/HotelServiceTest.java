package de.thws.fiw.backendsystems.templates.jpatemplate.test;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.services.HotelService;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.IdGenerator;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.InMemoryHotelIdGenerator;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.InMemoryLocationIdGenerator;
import org.junit.Test;


import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class HotelServiceTest {

    @Test
    public void testCreateHotel() {
        // Arrange
        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);

        // Act
        Hotel hotel = hotelService.createHotel(
                "Hotel Sunshine",
                "A lovely seaside hotel",
                "123 Beach Avenue",
                "Malibu",
                "USA"
        );

        // Assert
        assertNotNull(hotel);
        assertEquals(1, hotel.getId());
        assertEquals("Hotel Sunshine", hotel.getName());
        assertEquals("A lovely seaside hotel", hotel.getDescription());
        assertEquals("123 Beach Avenue", hotel.getLocation().getAddress());
    }

    @Test
    public void testFindHotelById() {
        // Arrange
        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);

        // Act
        Hotel hotel0 = hotelService.createHotel(
                "Hotel Sunshine",
                "A lovely seaside hotel",
                "123 Beach Avenue",
                "Malibu",
                "USA"
        );

        Hotel hotel1 = hotelService.createHotel(
                "Hotel Rainforest",
                "A sustainable and welcoming hotel",
                "45 Linton Street",
                "San Francisco",
                "USA"
        );

        // Retrieve hotels by their IDs
        Hotel retrievedHotel0 = hotelService.getHotelById(hotel0.getId());
        Hotel retrievedHotel1 = hotelService.getHotelById(hotel1.getId());

        // Assert
        assertNotNull(retrievedHotel0);
        assertNotNull(retrievedHotel1);
        assertEquals(hotel0, retrievedHotel0);
        assertEquals(hotel1, retrievedHotel1);

        assertEquals("Hotel Sunshine", retrievedHotel0.getName());
        assertEquals("Hotel Rainforest", retrievedHotel1.getName());
        assertEquals("123 Beach Avenue", retrievedHotel0.getLocation().getAddress());
        assertEquals("45 Linton Street", retrievedHotel1.getLocation().getAddress());
    }
}
