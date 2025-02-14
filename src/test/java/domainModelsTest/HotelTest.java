package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.domain.models.*;
import org.junit.jupiter.api.Test;

public class HotelTest {

    // Erzeuge ein Dummy-HotelLocation (sofern vorhanden, nutzen wir den Builder)
    private HotelLocation createDummyLocation() {
        return new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("123 Test Street")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
    }

    // Dummy-Instanzen für Room, Booking und HotelRating können wir hier als einfache Objekte erstellen
    private Room createDummyRoom() {
        // Da Room abstrakt ist, verwenden wir hier den Builder aus der abstrakten Klasse, der einen anonymen Typ zurückgibt.
        return new Room.Builder()
                .withId(10L)
                .withPricePerNight(100.0)
                .withRoomIdentifier(new RoomIdentifier("Building A", 1, "101"))
                // Wir übergeben hier ein Dummy-Hotel, das wir minimal erzeugen
                .withHotel(new Hotel.HotelBuilder()
                        .withId(999L)
                        .withName("Dummy Hotel")
                        .withDescription("Dummy Description")
                        .withLocation(createDummyLocation())
                        .build())
                .build();
    }

    private Booking createDummyBooking() {
        // Erzeuge ein Dummy-Hotel, das für die Buchung benötigt wird.
        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(999L)
                .withName("Dummy Hotel")
                .withDescription("Dummy Description")
                .withLocation(createDummyLocation())
                .build();
        // Erzeuge eine Buchung mit gültigen CheckIn- und CheckOut-Daten (z.B. heute und morgen)
        return new Booking(
                20L,
                dummyHotel,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                new ArrayList<>(),
                new ArrayList<>(),
                true,
                null,
                null
        );
    }

    private HotelRating createDummyHotelRating() {
        return new HotelRating.Builder()
                .withId(30L)
                .withRating(5)
                .withComment("Excellent")
                .build();
    }

    @Test
    public void testHotelBuilder_WithAllFields() {
        HotelLocation location = createDummyLocation();
        List<Room> rooms = Arrays.asList(createDummyRoom(), createDummyRoom());
        List<Booking> bookings = Arrays.asList(createDummyBooking());
        List<HotelRating> ratings = Arrays.asList(createDummyHotelRating());
        double avgRating = 4.5;

        Hotel hotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("A great hotel")
                .withLocation(location)
                .withRoomsList(rooms)
                .withBookingList(bookings)
                .withRatingMap(ratings)
                .withAverageRating(avgRating)
                .build();

        assertEquals(1L, hotel.getId());
        assertEquals("Test Hotel", hotel.getName());
        assertEquals("A great hotel", hotel.getDescription());
        assertEquals(location, hotel.getLocation());
        assertEquals(avgRating, hotel.getAverageRating(), 0.001);
        assertEquals(rooms, hotel.getRooms());
        assertEquals(bookings, hotel.getBookings());
        assertEquals(ratings, hotel.getRatings());
    }

    @Test
    public void testHotelBuilder_WithNullLists() {
        // Falls null übergeben wird, sollte eine leere Liste erzeugt werden (bei Rooms)
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(2L)
                .withName("Minimal Hotel")
                .withDescription("Minimal Description")
                .withLocation(createDummyLocation())
                .withRoomsList(null)
                .withBookingList(null)
                .withRatingMap(null)
                .withAverageRating(0.0)
                .build();

        assertNotNull(hotel.getRooms(), "Rooms-Liste sollte nicht null sein");
        // Je nach Implementierung der anderen Listen (Bookings und Ratings) hier ggf. weitere Prüfungen einfügen.
    }

    @Test
    public void testSettersAndGetters() {
        Hotel hotel = new Hotel.HotelBuilder().build();

        hotel.setId(100L);
        hotel.setName("Updated Hotel");
        hotel.setDescription("Updated Description");
        hotel.setAverageRating(3.8);
        HotelLocation newLocation = createDummyLocation();
        hotel.setLocation(newLocation);
        List<Room> newRooms = Arrays.asList(createDummyRoom());
        hotel.setRoom(newRooms);
        List<Booking> newBookings = Arrays.asList(createDummyBooking());
        hotel.setBookings(newBookings);

        assertEquals(100L, hotel.getId());
        assertEquals("Updated Hotel", hotel.getName());
        assertEquals("Updated Description", hotel.getDescription());
        assertEquals(3.8, hotel.getAverageRating(), 0.001);
        assertEquals(newLocation, hotel.getLocation());
        assertEquals(newRooms, hotel.getRooms());
        assertEquals(newBookings, hotel.getBookings());
    }

    @Test
    public void testAddRatingAndAddRoom() {
        // Erzeuge ein Hotel mit leeren Listen
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Description")
                .withLocation(createDummyLocation())
                .withRoomsList(new ArrayList<>())
                .withBookingList(new ArrayList<>())
                .withRatingMap(new ArrayList<>())
                .withAverageRating(0.0)
                .build();

        // Füge ein Rating hinzu und überprüfe
        HotelRating rating = createDummyHotelRating();
        hotel.addRating(rating);
        assertEquals(1, hotel.getRatings().size());
        assertTrue(hotel.getRatings().contains(rating));

        // Füge einen Room hinzu und überprüfe
        Room room = createDummyRoom();
        hotel.addRoom(room);
        assertEquals(1, hotel.getRooms().size());
        assertTrue(hotel.getRooms().contains(room));
    }
}
