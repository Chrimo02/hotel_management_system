package hotelmanagementsystem.infrastructure.startup;


import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class TestDataLoader {

    @Inject
    GuestService guestService;

    @Inject
    HotelService hotelService;

    @Inject
    RoomService roomService;

    /**
     * Diese Methode wird automatisch aufgerufen, wenn Quarkus startet.
     * Hier erstellen wir 5 Gäste, 3 Hotels und je 2 Räume (Single+Double) pro Hotel.
     */
    public void loadTestData(@Observes StartupEvent ev) {
        createGuests();
        createHotelsAndRooms();
        System.out.println("TestDataLoader: Successfully inserted test data into in-memory DB.");
    }

    private void createGuests() {
        guestService.createGuest("Alice", "Smith", 1990, 5, 1,  "alice@example.com", "11111");
        guestService.createGuest("Bob",   "Brown", 1985, 3, 10, "bob@example.com",   "22222");
        guestService.createGuest("Clara", "Jones", 1977, 12, 5, "clara@example.com", "33333");
        guestService.createGuest("Derek", "Adams", 1995, 7, 20, "derek@example.com", "44444");
        guestService.createGuest("Eve",   "Miller",1988, 10, 9, "eve@example.com",   "55555");
    }

    private void createHotelsAndRooms() {
        try {


            // --- Hotel 1 ---
            HotelLocation loc1 = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Sonnenallee 1")
                    .withCity("Sonneberg")
                    .withCountry("Germany")
                    .build();
            Hotel hotel1 = hotelService.createHotel("SunHotel", "Sommer, Sonne, Kaktus", loc1);

            // Zwei Räume anlegen: 1x Single, 1x Double
            roomService.createRoom(
                    99.99,
                    new RoomIdentifier("MainBuilding", 1, "101"),
                    hotel1.getId(),
                    SingleRoom.class
            );
            roomService.createRoom(
                    149.99,
                    new RoomIdentifier("MainBuilding", 1, "102"),
                    hotel1.getId(),
                    DoubleRoom.class
            );

            // --- Hotel 2 ---
            HotelLocation loc2 = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Mondstraße 2")
                    .withCity("MoonCity")
                    .withCountry("Germany")
                    .build();
            Hotel hotel2 = hotelService.createHotel("MoonHotel", "Nacht und Sterne", loc2);

            // Zwei Räume anlegen
            roomService.createRoom(
                    120.0,
                    new RoomIdentifier("WingA", 2, "201A"),
                    hotel2.getId(),
                    SingleRoom.class
            );
            roomService.createRoom(
                    180.0,
                    new RoomIdentifier("WingA", 2, "202A"),
                    hotel2.getId(),
                    DoubleRoom.class
            );

            // --- Hotel 3 ---
            HotelLocation loc3 = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Meeresweg 5")
                    .withCity("Atlantis")
                    .withCountry("Atlantik")
                    .build();
            Hotel hotel3 = hotelService.createHotel("SeaHotel", "Tauche ein in die Tiefen", loc3);

            // Zwei Räume anlegen
            roomService.createRoom(
                    110.0,
                    new RoomIdentifier("Bungalow", 1, "B1"),
                    hotel3.getId(),
                    SingleRoom.class
            );
            roomService.createRoom(
                    190.0,
                    new RoomIdentifier("Bungalow", 2, "B2"),
                    hotel3.getId(),
                    DoubleRoom.class
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
