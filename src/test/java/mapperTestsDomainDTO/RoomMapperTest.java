package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.mapper.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomMapperTest {

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
    public void testToDTO_SingleRoom() {
        SingleRoom room = new SingleRoom.Builder(150.0, dummyRoomIdentifier, dummyHotel)
                .withId(10L)
                .build();
        room.setBookings(new HashSet<>());

        RoomDTO dto = RoomMapper.toDTO(room);
        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals(150.0, dto.getPricePerNight(), 0.001);
        assertEquals(dummyRoomIdentifier.getBuilding(), dto.getRoomIdentifierDTO().getBuilding());
        assertEquals(dummyHotel.getId(), dto.getHotelId());
        assertTrue(dto.getBookingDTOs().isEmpty());
        assertEquals("SingleRoom", dto.getType());
    }

    @Test
    public void testToDomain_SingleRoom() {
        hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO identifierDTO =
                new hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO("BuildingY", 2, "202Y");
        RoomDTO dto = new RoomDTO(20L, 200.0, identifierDTO, 1L, Arrays.asList(), "SingleRoom");

        Set<Booking> bookings = new HashSet<>();
        Room room = RoomMapper.toDomain(dto, dummyHotel, bookings);
        assertNotNull(room);
        assertEquals(20L, room.getId());
        assertEquals(200.0, room.getPricePerNight(), 0.001);
        assertNotNull(room.getRoomIdentifier());
        assertEquals("BuildingY", room.getRoomIdentifier().getBuilding());
        assertEquals(dummyHotel, room.getHotel());
        assertEquals(bookings, room.getBookings());
    }
}
