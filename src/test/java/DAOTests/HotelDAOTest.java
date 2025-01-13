package DAOTests;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.DataAccessException;
import hotelmanagementsystem.infrastructure.persistence.dao.implementation.HotelDAOImpl;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HotelDAOTest {


    @Spy
    private HotelDAOImpl hotelDAO;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    // Optional, falls du Abfragen via TypedQuery nutzt
    @Mock
    private TypedQuery<HotelEntity> mockTypedQuery;

    private HotelEntity mockHotelEntity;

    @BeforeEach
    void setUp() {
        // Lenient-Stubbings, damit Mockito nicht bei jedem Test aufruft "meckert".
        lenient().doReturn(mockEntityManager).when(hotelDAO).entityManager();
        lenient().when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        lenient().when(mockTransaction.isActive()).thenReturn(true);

        // Beispiel-HotelEntity (so wie in deinem alten Code)
        HotelLocationEntity location = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("Test Address")
                .withCity("Test City")
                .withCountry("Test Country")
                .build();

        mockHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("A nice test hotel")
                .withLocation(location)
                .withRooms(List.of())
                .withBookings(List.of())
                .withRatings(List .of())
                .build();
    }

    @Test
    void testCreateHotel_Success() {
        // Kein Fehler simulieren -> normaler Durchlauf
        assertDoesNotThrow(() -> hotelDAO.createHotel(mockHotelEntity));

        // Überprüfen, dass Transaktion gestartet, persist aufgerufen, committet und geschlossen wurde
        verify(mockTransaction).begin();
        verify(mockEntityManager).persist(mockHotelEntity);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testCreateHotel_ExceptionRollback() {
        // Wir simulieren, dass persist eine Exception wirft
        doThrow(new RuntimeException("Persistence error"))
                .when(mockEntityManager).persist(any(HotelEntity.class));

        DataAccessException exception =
                assertThrows(DataAccessException.class, () -> hotelDAO.createHotel(mockHotelEntity));

        assertTrue(exception.getMessage().contains("Error while creating hotel entity"));
        // Rollback wird erwartet
        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testFindById_Success() {
        when(mockEntityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);

        Optional<HotelEntity> result = hotelDAO.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Test Hotel", result.get().getName());

        verify(mockEntityManager).find(HotelEntity.class, 1L);
        verify(mockEntityManager).close();
    }

    @Test
    void testFindById_NotFound() {
        when(mockEntityManager.find(HotelEntity.class, 2L)).thenReturn(null);
        Optional<HotelEntity> result = hotelDAO.findById(2L);
        assertTrue(result.isEmpty());
        verify(mockEntityManager).find(HotelEntity.class, 2L);
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdateHotel_Success() {
        assertDoesNotThrow(() -> hotelDAO.updateHotel(mockHotelEntity));
        verify(mockTransaction).begin();
        verify(mockEntityManager).merge(mockHotelEntity);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdateHotel_ExceptionRollback() {
        doThrow(new RuntimeException("Update error"))
                .when(mockEntityManager).merge(any(HotelEntity.class));

        DataAccessException exception =
                assertThrows(DataAccessException.class, () -> hotelDAO.updateHotel(mockHotelEntity));

        assertTrue(exception.getMessage().contains("Error updating Hotel"));
        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testDeleteById_Success() {
        when(mockEntityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);
        assertDoesNotThrow(() -> hotelDAO.deleteById(1L));
        verify(mockTransaction).begin();
        verify(mockEntityManager).find(HotelEntity.class, 1L);
        verify(mockEntityManager).remove(mockHotelEntity);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testDeleteById_NotFound() {
        when(mockEntityManager.find(HotelEntity.class, 2L)).thenReturn(null);
        assertDoesNotThrow(() -> hotelDAO.deleteById(2L));
        verify(mockTransaction).begin();
        verify(mockEntityManager).find(HotelEntity.class, 2L);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testDeleteById_ExceptionRollback() {
        when(mockEntityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);
        doThrow(new RuntimeException("Delete error")).when(mockEntityManager).remove(mockHotelEntity);

        DataAccessException exception =
                assertThrows(DataAccessException.class, () -> hotelDAO.deleteById(1L));

        assertTrue(exception.getMessage().contains("Error deleting Hotel"));
        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }
}

