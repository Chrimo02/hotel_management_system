package repositoriesTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.HotelDatabaseAdapter;
import hotelmanagementsystem.domain.models.PagedHotels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class HotelRepoTest {

    @Mock
    private HotelDAO hotelDAO;
    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelDatabaseAdapter adapter;

    private Hotel dummyHotel;
    private HotelEntity dummyHotelEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .withAverageRating(4.5)
                .withRoomsList(Collections.emptyList())
                .withBookingList(Collections.emptyList())
                .withRatingMap(Collections.emptyList())
                .build();

        dummyHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .withAverageRating(4.5)
                .withRooms(Collections.emptyList())
                .withBookings(Collections.emptyList())
                .withRatings(Collections.emptyList())
                .build();
    }

    @Test
    public void testCreateHotel() {
        when(hotelMapper.mapDomainHotelToHotelEntity(dummyHotel)).thenReturn(dummyHotelEntity);
        when(hotelDAO.createHotel(dummyHotelEntity)).thenReturn(dummyHotelEntity);
        when(hotelMapper.mapHotelEntityToDomainHotel(dummyHotelEntity)).thenReturn(dummyHotel);

        Hotel result = adapter.createHotel(dummyHotel);
        assertNotNull(result);
        assertEquals(dummyHotel.getId(), result.getId());
    }

    @Test
    public void testFindById() {
        when(hotelDAO.findById(1L)).thenReturn(dummyHotelEntity);
        when(hotelMapper.mapHotelEntityToDomainHotel(dummyHotelEntity)).thenReturn(dummyHotel);

        Hotel result = adapter.findById(1L);
        assertNotNull(result);
        assertEquals(dummyHotel.getId(), result.getId());
    }

    @Test
    public void testFindPagedByFilter() {
        int pageNumber = 1;
        int pageSize = 10;
        String city = "TestCity";
        double minRating = 3.5;
        when(hotelDAO.countByFilter(city, minRating)).thenReturn(1L);
        when(hotelDAO.findByFilter(city, minRating, 0, pageSize)).thenReturn(Collections.singletonList(dummyHotelEntity));
        when(hotelMapper.mapHotelEntityToDomainHotel(dummyHotelEntity)).thenReturn(dummyHotel);

        PagedHotels paged = adapter.findPagedByFilter(city, minRating, pageNumber, pageSize);
        assertNotNull(paged);
        assertEquals(1, paged.hotels().size());
        assertEquals(1, paged.totalCount());
    }

    @Test
    public void testUpdateHotel() {
        when(hotelMapper.mapDomainHotelToHotelEntity(dummyHotel)).thenReturn(dummyHotelEntity);
        when(hotelDAO.updateHotel(dummyHotelEntity)).thenReturn(dummyHotelEntity);
        when(hotelMapper.mapHotelEntityToDomainHotel(dummyHotelEntity)).thenReturn(dummyHotel);

        Hotel updated = adapter.update(dummyHotel);
        assertNotNull(updated);
        assertEquals(dummyHotel.getId(), updated.getId());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(hotelDAO).deleteById(1L);
        assertDoesNotThrow(() -> adapter.deleteById(1L));
        verify(hotelDAO, times(1)).deleteById(1L);
    }
}
