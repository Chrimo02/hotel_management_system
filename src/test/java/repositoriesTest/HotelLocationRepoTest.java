package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelLocationMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.HotelLocationDatabaseAdapter;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class HotelLocationRepoTest {

    @Mock
    private HotelLocationDAO hotelLocationDAO;
    @Mock
    private HotelDAO hotelDAO;
    @Mock
    private HotelLocationMapper hotelLocationMapper;

    @InjectMocks
    private HotelLocationDatabaseAdapter adapter;

    private HotelLocation dummyLocation;
    private HotelLocationEntity dummyLocationEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyLocation = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
        dummyLocationEntity = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
    }

    @Test
    public void testCreateHotelLocation() {
        when(hotelLocationMapper.mapToEntity(dummyLocation)).thenReturn(dummyLocationEntity);
        doNothing().when(hotelLocationDAO).create(dummyLocationEntity);

        assertDoesNotThrow(() -> adapter.createHotelLocation(dummyLocation));
        verify(hotelLocationDAO, times(1)).create(dummyLocationEntity);
    }

    @Test
    public void testDeleteHotelLocation() {
        when(hotelLocationDAO.read(1L)).thenReturn(dummyLocationEntity);
        doNothing().when(hotelLocationDAO).delete(dummyLocationEntity);
        assertDoesNotThrow(() -> adapter.deleteHotelLocation(1L));
        verify(hotelLocationDAO, times(1)).delete(dummyLocationEntity);
    }

}
