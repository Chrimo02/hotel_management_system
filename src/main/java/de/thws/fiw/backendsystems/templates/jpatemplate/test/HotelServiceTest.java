//package de.thws.fiw.backendsystems.templates.jpatemplate.test;
//
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.services.HotelService;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.IdGenerator;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.InMemoryHotelIdGenerator;
//import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.InMemoryLocationIdGenerator;
//import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;
//import org.junit.Test;
//
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//public class HotelServiceTest {
//
//    @Test
//    public void testCreateHotel() {
//
//        HotelService hotelService = new HotelService(hotelRepository);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        assertNotNull(hotel);
//        assertEquals(1, hotel.getId());
//        assertEquals("Hotel Sunshine", hotel.getName());
//        assertEquals("A lovely seaside hotel", hotel.getDescription());
//        assertEquals("123 Beach Avenue", hotel.getLocation().getAddress());
//        assertEquals("Malibu", hotel.getLocation().getCity());
//        assertEquals("USA", hotel.getLocation().getCountry());
//    }
//
//    @Test
//    public void testDeleteHotel() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        assertNotNull(hotelService.getHotelById(hotel.getId()));
//
//        hotelService.deleteHotel(hotel.getId());
//
//        assertNull(hotelService.getHotelById(hotel.getId()));
//    }
//
//    @Test
//    public void testUpdateHotel() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        Hotel updatedHotel = hotelService.updateHotel(
//                hotel.getId(),
//                "Hotel Sunset",
//                "A luxurious seaside resort",
//                "456 Ocean Drive",
//                "Santa Monica",
//                "USA"
//        );
//
//        assertEquals("Hotel Sunset", updatedHotel.getName());
//        assertEquals("A luxurious seaside resort", updatedHotel.getDescription());
//        assertEquals("456 Ocean Drive", updatedHotel.getLocation().getAddress());
//        assertEquals("Santa Monica", updatedHotel.getLocation().getCity());
//    }
//
//    @Test
//    public void testGetHotels() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//        hotelService.createHotel(
//                "Hotel Rainforest",
//                "A sustainable and welcoming hotel",
//                "45 Linton Street",
//                "San Francisco",
//                "USA"
//        );
//
//        List<Hotel> hotels = hotelService.getHotels();
//        assertEquals(2, hotels.size());
//    }
//
//    @Test
//    public void testFindHotelById() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        Hotel retrievedHotel = hotelService.getHotelById(hotel.getId());
//        assertNotNull(retrievedHotel);
//        assertEquals(hotel.getId(), retrievedHotel.getId());
//        assertEquals("Hotel Sunshine", retrievedHotel.getName());
//    }
//
//    @Test
//    public void testFindAvailableRooms() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        // Assume rooms are added and logic for availability is tested elsewhere
//        List<Room> availableRooms = hotelService.findAvailableRooms(hotel.getId(), null);
//        assertNotNull(availableRooms);
//    }
//
//    @Test
//    public void testRateHotel() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        hotelService.rateHotel(1, hotel.getId(), 5);
//        hotelService.rateHotel(2, hotel.getId(), 4);
//
//        List<HotelRating> ratings = hotelService.filterHotelRatings(hotel.getId(), 0, false);
//        assertEquals(2, ratings.size());
//    }
//
//    @Test
//    public void testFilterHotelRatings() {
//        IdGenerator hotelIdGenerator = new InMemoryHotelIdGenerator();
//        IdGenerator locationIdGenerator = new InMemoryLocationIdGenerator();
//        HotelService hotelService = new HotelService(hotelIdGenerator, locationIdGenerator);
//
//        Hotel hotel = hotelService.createHotel(
//                "Hotel Sunshine",
//                "A lovely seaside hotel",
//                "123 Beach Avenue",
//                "Malibu",
//                "USA"
//        );
//
//        hotelService.rateHotel(1, hotel.getId(), 5, "Great stay!");
//        hotelService.rateHotel(2, hotel.getId(), 3, "Average experience");
//
//        List<HotelRating> ratings = hotelService.filterHotelRatings(hotel.getId(), 5, true);
//        assertEquals(1, ratings.size());
//        assertEquals("Great stay!", ratings.get(0).getGuestComment());
//    }
//}