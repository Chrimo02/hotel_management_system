package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HotelMapperTest {

    @Mock
    private BookingMapper bookingMapper;
    @Mock
    private HotelLocationMapper hotelLocationMapper;
    @Mock
    private RoomMapper roomMapper;
    @Mock
    private HotelRatingMapper hotelRatingMapper;

    private HotelMapper hotelMapper;

    @BeforeEach
    public void setUp() {
        hotelMapper = new HotelMapper(bookingMapper, hotelLocationMapper, roomMapper, hotelRatingMapper);
    }

    @Test
    public void testMapDomainHotelToHotelEntity() {
        // Erstelle ein Dummy-Hotel-Domainobjekt
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Description")
                .withAverageRating(4.2)
                .withLocation(null)
                .withBookingList(Collections.emptyList())
                .withRoomsList(Collections.emptyList())
                .withRatingMap(Collections.emptyList())
                .build();

        // Stub Dependencies
        // HotelLocationMapper
        when(hotelLocationMapper.mapToEntity(null)).thenReturn(null);
        // BookingMapper und RoomMapper und HotelRatingMapper stubben wir hier als leere Listen
        when(bookingMapper.toEntityList(anyList())).thenReturn(Collections.emptyList());
        when(roomMapper.toEntityList(anyList())).thenReturn(Collections.emptyList());
        when(hotelRatingMapper.mapToEntityList(anyList())).thenReturn(Collections.emptyList());

        HotelEntity entity = hotelMapper.mapDomainHotelToHotelEntity(hotel);
        assertNotNull(entity);
        assertEquals(hotel.getId(), entity.getId());
        assertEquals(hotel.getName(), entity.getName());
        assertEquals(hotel.getDescription(), entity.getDescription());
        assertEquals(hotel.getAverageRating(), entity.getAverageRating(), 0.001);
        // Da Location null, Bookings, Rooms, Ratings leer
        assertNull(entity.getLocation());
        assertTrue(entity.getBookings().isEmpty());
        assertTrue(entity.getRooms().isEmpty());
        assertTrue(entity.getRatings().isEmpty());
    }

    @Test
    public void testMapHotelEntityToDomainHotel() {
        // Erstelle ein Dummy-HotelEntity
        HotelEntity entity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Description")
                .withAverageRating(4.2)
                .withRooms(Collections.emptyList())
                .withBookings(Collections.emptyList())
                .withRatings(Collections.emptyList())
                .build();

        // Stub Dependencies
        when(hotelLocationMapper.mapToDomain(any())).thenReturn(null);
        // BookingMapper, RoomMapper, HotelRatingMapper stubben als leere Listen
        when(bookingMapper.toDomainList(anyList())).thenReturn(Collections.emptyList());
        when(roomMapper.toDomainList(anyList())).thenReturn(Collections.emptyList());
        when(hotelRatingMapper.mapToDomainList(anyList())).thenReturn(Collections.emptyList());

        Hotel hotel = hotelMapper.mapHotelEntityToDomainHotel(entity);
        assertNotNull(hotel);
        assertEquals(entity.getId(), hotel.getId());
        assertEquals(entity.getName(), hotel.getName());
        assertEquals(entity.getDescription(), hotel.getDescription());
        assertEquals(entity.getAverageRating(), hotel.getAverageRating(), 0.001);
        // Da Location null und leere Listen
        assertNull(hotel.getLocation());
        assertTrue(hotel.getBookings().isEmpty());
        assertTrue(hotel.getRooms().isEmpty());
        assertTrue(hotel.getRatings().isEmpty());
    }
}
