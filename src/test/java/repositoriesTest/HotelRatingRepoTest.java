package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelRatingMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.HotelRatingDatabaseAdapter;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class HotelRatingRepoTest {

    @Mock
    private HotelRatingDAO hotelRatingDAO;
    @Mock
    private HotelRatingMapper hotelRatingMapper;

    @InjectMocks
    private HotelRatingDatabaseAdapter adapter;

    private HotelRating dummyRating;
    private HotelRatingEntity dummyRatingEntity;
    private Guest dummyGuest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyGuest = new Guest.GuestBuilder()
                .withId(50L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
        dummyRating = new HotelRating.Builder()
                .withId(10L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(dummyGuest)
                .build();
        dummyRatingEntity = new HotelRatingEntity.Builder()
                .withId(dummyRating.getId())
                .withRating(dummyRating.getStarRating())
                .withComment(dummyRating.getGuestComment())
                .withGuest(null) // guest wird hier nicht geprüft
                .build();
    }

    @Test
    public void testSave_NewRating() {
        // Simuliere, dass rating.getId() == null
        HotelRating newRating = new HotelRating.Builder()
                .withRating(4)
                .withComment("Good")
                .withGuest(dummyGuest)
                .build();
        HotelRatingEntity newEntity = new HotelRatingEntity.Builder()
                .withRating(4)
                .withComment("Good")
                .withGuest(null)
                .build();
        when(hotelRatingMapper.mapToEntity(newRating)).thenReturn(newEntity);
        when(hotelRatingDAO.createRating(newEntity)).thenReturn(newEntity);
        when(hotelRatingMapper.mapToDomain(newEntity)).thenReturn(newRating);

        HotelRating result = adapter.save(newRating);
        assertNotNull(result);
        assertEquals(newRating.getStarRating(), result.getStarRating());
    }

    @Test
    public void testSave_UpdateRating() {
        // Simuliere, dass rating.getId() != null
        when(hotelRatingMapper.mapToEntity(dummyRating)).thenReturn(dummyRatingEntity);
        doNothing().when(hotelRatingDAO).updateRating(dummyRatingEntity);
        when(hotelRatingDAO.findById(dummyRating.getId())).thenReturn(dummyRatingEntity);
        when(hotelRatingMapper.mapToDomain(dummyRatingEntity)).thenReturn(dummyRating);

        HotelRating result = adapter.save(dummyRating);
        assertNotNull(result);
        assertEquals(dummyRating.getStarRating(), result.getStarRating());
    }

    @Test
    public void testFindById() {
        when(hotelRatingDAO.findById(10L)).thenReturn(dummyRatingEntity);
        when(hotelRatingMapper.mapToDomain(dummyRatingEntity)).thenReturn(dummyRating);
        HotelRating result = adapter.findById(10L);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    public void testDelete() {
        // Bei delete wird einfach hotelRatingDAO.deleteById aufgerufen, sofern id nicht null
        dummyRating = new HotelRating.Builder()
                .withId(10L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(dummyGuest)
                .build();
        doNothing().when(hotelRatingDAO).deleteById(10L);
        assertDoesNotThrow(() -> adapter.delete(dummyRating));
        verify(hotelRatingDAO, times(1)).deleteById(10L);
    }

    @Test
    public void testFindFilteredRatings() {
        List<HotelRatingEntity> entities = Collections.singletonList(dummyRatingEntity);
        when(hotelRatingDAO.findFilteredRatings(1L, 5, true)).thenReturn(entities);
        when(hotelRatingMapper.mapToDomainList(entities)).thenReturn(Collections.singletonList(dummyRating));
        List<HotelRating> results = adapter.findFilteredRatings(1L, 5, true);
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(5, results.get(0).getStarRating());
    }
}
