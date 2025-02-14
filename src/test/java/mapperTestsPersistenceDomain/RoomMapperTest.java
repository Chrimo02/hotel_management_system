package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.infrastructure.persistence.entities.*;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RoomMapperTest {

    // Wir verwenden hier echte Instanzen der Hilfsmodule (RoomIdentifierMapper und BookingMapper)
    private RoomIdentifierMapper roomIdentifierMapper = new RoomIdentifierMapper();
    // Für BookingMapper verwenden wir einen einfachen Stub, da er in diesem Test nicht im Detail benötigt wird.
    private BookingMapper bookingMapper = Mockito.mock(BookingMapper.class);
    private RoomMapper roomMapper = new RoomMapper(roomIdentifierMapper, bookingMapper);

    private Hotel dummyHotel;
    private RoomIdentifier dummyRoomIdentifier;

    @BeforeEach
    public void setUp() {
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        dummyRoomIdentifier = new RoomIdentifier("BuildingX", 3, "303X");
    }

    @Test
    public void testDomainToEntity_SingleRoom() {
        SingleRoom domainRoom = new SingleRoom.Builder(150.0, dummyRoomIdentifier, dummyHotel)
                .withId(10L)
                .build();

        RoomEntity entity = roomMapper.domainToEntity(domainRoom);
        assertNotNull(entity);
        assertEquals(150.0, entity.getPricePerNight(), 0.001);
        // Überprüfe, ob RoomIdentifier korrekt gemappt wurde
        assertNotNull(entity.getRoomIdentifier());
        assertEquals(dummyRoomIdentifier.getBuilding(), entity.getRoomIdentifier().getBuilding());
        // MinimalHotelEntity wird erzeugt (nur ID)
        assertNotNull(entity.getHotel());
        assertEquals(dummyHotel.getId(), entity.getHotel().getId());
    }

    @Test
    public void testToEntityListAndToDomainList() {
        SingleRoom room1 = new SingleRoom.Builder(150.0, dummyRoomIdentifier, dummyHotel)
                .withId(10L)
                .build();
        DoubleRoom room2 = new DoubleRoom.Builder(200.0, dummyRoomIdentifier, dummyHotel)
                .withId(20L)
                .build();
        List<Room> domainRooms = Arrays.asList(room1, room2);

        List<RoomEntity> entities = roomMapper.toEntityList(domainRooms);
        assertNotNull(entities);
        assertEquals(2, entities.size());

        List<Room> mappedBack = roomMapper.toDomainList(entities);
        assertNotNull(mappedBack);
        assertEquals(2, mappedBack.size());
        // Prüfe anhand der IDs
        Set<Long> ids = new HashSet<>();
        for (Room r : mappedBack) {
            ids.add(r.getId());
        }
        assertTrue(ids.contains(10L));
        assertTrue(ids.contains(20L));
    }

    @Test
    public void testToMinimalDomain() {
        // Erzeuge ein Dummy RoomEntity (z. B. für einen SingleRoom)
        RoomIdentifierEntity riEntity = new RoomIdentifierEntity("BuildingX", 3, "303X");
        HotelEntity minimalHotelEntity = new HotelEntity.HotelBuilder()
                .withId(dummyHotel.getId())
                .withName(dummyHotel.getName())
                .build();
        // Erzeuge ein Dummy RoomEntity (hier als SingleRoomEntity)
        RoomEntity roomEntity = new SingleRoomEntity(10L, 150.0, riEntity, minimalHotelEntity);
        // Simuliere, dass Bookings in der Entity vorhanden sind – hier setzen wir sie leer
        roomEntity.setBookings(new HashSet<>());

        Room minimalDomain = roomMapper.toMinimalDomain(roomEntity);
        assertNotNull(minimalDomain);
        assertEquals(10L, minimalDomain.getId());
        assertEquals(150.0, minimalDomain.getPricePerNight(), 0.001);
        // Hotel im Minimal-Domain-Modell haben wir hier als null gesetzt (siehe Implementation)
        assertNull(minimalDomain.getHotel());
        // Prüfe den RoomIdentifier
        assertNotNull(minimalDomain.getRoomIdentifier());
        assertEquals(riEntity.getBuilding(), minimalDomain.getRoomIdentifier().getBuilding());
    }
}
