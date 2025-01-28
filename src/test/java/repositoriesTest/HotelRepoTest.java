package repositoriesTest;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.HotelDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelRepoTest {

    @Mock
    private HotelDAO hotelDAO;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelDatabaseAdapter hotelDatabaseAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHotel() {
        // Given: a domain Hotel object
        Hotel domainHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .build();

        HotelEntity hotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .build();

        // Stubbing the mapper and DAO interactions
        when(hotelMapper.mapDomainHotelToHotelEntity(domainHotel)).thenReturn(hotelEntity);
        when(hotelDAO.createHotel(hotelEntity)).thenReturn(hotelEntity);
        when(hotelMapper.mapHotelEntityToDomainHotel(hotelEntity)).thenReturn(domainHotel);

        // When
        Hotel createdHotel = hotelDatabaseAdapter.createHotel(domainHotel);

        // Then
        assertNotNull(createdHotel, "Created hotel should not be null");
        assertEquals(domainHotel, createdHotel,
                "The returned domain object should match the mocked domain hotel");
        verify(hotelDAO, times(1)).createHotel(hotelEntity);
    }


    @Test
    void testFindById() {
        // Given
        Long hotelId = 1L;
        HotelEntity hotelEntity = new HotelEntity.HotelBuilder()
                .withId(hotelId)
                .withName("Existing Hotel")
                .withDescription("Existing Description")
                .build();

        // You also need a corresponding domain Hotel to be returned by the mapper:
        Hotel domainHotel = new Hotel.HotelBuilder()
                .withId(hotelId)
                .withName("Existing Hotel")
                .withDescription("Existing Description")
                .build();

        // Mock the DAO to return the entity
        when(hotelDAO.findById(hotelId)).thenReturn(Optional.of(hotelEntity));

        // Mock the mapper to convert the entity to a domain Hotel
        when(hotelMapper.mapHotelEntityToDomainHotel(hotelEntity)).thenReturn(domainHotel);

        // When
        Optional<Hotel> result = hotelDatabaseAdapter.findById(hotelId);

        // Then
        assertTrue(result.isPresent(), "Expected a result for the given hotel ID");
        assertEquals("Existing Hotel", result.get().getName());
        assertEquals("Existing Description", result.get().getDescription());
        verify(hotelDAO, times(1)).findById(hotelId);
    }


    @Test
    void testFindAll() {
        // Given
        // First HotelEntity
        HotelEntity hotelEntity1 = new HotelEntity.HotelBuilder().build();

        // Second HotelEntity
        HotelEntity hotelEntity2 = new HotelEntity.HotelBuilder().build();

        List<HotelEntity> entityList = new ArrayList<>();
        entityList.add(hotelEntity1);
        entityList.add(hotelEntity2);

        // Corresponding domain Hotels
        Hotel domainHotel1 = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Hotel One")
                .withDescription("Description One")
                .build();
        Hotel domainHotel2 = new Hotel.HotelBuilder()
                .withId(2L)
                .withName("Hotel Two")
                .withDescription("Description Two")
                .build();

        when(hotelDAO.findAll()).thenReturn(entityList);
        when(hotelMapper.mapHotelEntityToDomainHotel(hotelEntity1)).thenReturn(domainHotel1);
        when(hotelMapper.mapHotelEntityToDomainHotel(hotelEntity2)).thenReturn(domainHotel2);

        // When
        List<Hotel> hotels = hotelDatabaseAdapter.findAll();

        // Then
        assertNotNull(hotels, "Returned list should not be null");
        assertEquals(2, hotels.size(), "Should return two hotels");
        assertEquals("Hotel One", hotels.get(0).getName());
        assertEquals("Hotel Two", hotels.get(1).getName());
        verify(hotelDAO, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        // Given
        Hotel domainHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Updated Hotel")
                .withDescription("Updated Description")
                .build();

        HotelEntity hotelEntity = new HotelEntity.HotelBuilder()
                .withName("Test Update Name")
                .build();
        when(hotelMapper.mapDomainHotelToHotelEntity(domainHotel)).thenReturn(hotelEntity);

        // When
        // If no exceptions are thrown, we consider it a success
        assertDoesNotThrow(() -> hotelDatabaseAdapter.update(domainHotel));

        // Then
        verify(hotelDAO, times(1)).updateHotel(hotelEntity);
    }

    @Test
    void testDeleteById() {
        // Given
        Long hotelId = 1L;

        // When
        // If no exceptions are thrown, we consider it a success
        assertDoesNotThrow(() -> hotelDatabaseAdapter.deleteById(hotelId));

        // Then
        verify(hotelDAO, times(1)).deleteById(hotelId);
    }
}
