package DAOTests;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.DataAccessException;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.HotelDAOImpl;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelDAOTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private HotelDAOImpl hotelDAO;

    private HotelEntity mockHotelEntity;
    private HotelLocationEntity mockLocationEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Use the builder to create the HotelLocationEntity
        mockLocationEntity = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("Test Address")
                .withCity("Test City")
                .withCountry("Test Country")
                .build();

        // Create the HotelEntity using the builder pattern
        mockHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("A nice test hotel")
                .withLocation(mockLocationEntity)
                .withRooms(List.of())
                .withBookings(List.of())
                .withRatings(Map.of())
                .build();

        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void testCreateHotel_Success() {
        doNothing().when(entityManager).persist(mockHotelEntity);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        HotelEntity result = hotelDAO.createHotel(mockHotelEntity);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).persist(mockHotelEntity);
        verify(transaction, times(1)).commit();

        assertNotNull(result);
        assertEquals(mockHotelEntity.getId(), result.getId());
        assertEquals("Test Hotel", result.getName());
    }

    @Test
    public void testCreateHotel_ExceptionRollback() {
        doThrow(new RuntimeException("Persistence error")).when(entityManager).persist(mockHotelEntity);
        when(transaction.isActive()).thenReturn(true);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            hotelDAO.createHotel(mockHotelEntity);
        });

        assertTrue(exception.getMessage().contains("Error saving Hotel"));
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).rollback();
    }

    @Test
    public void testFindById_Success() {
        when(entityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);

        Optional<HotelEntity> result = hotelDAO.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Test Hotel", result.get().getName());
        verify(entityManager, times(1)).find(HotelEntity.class, 1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(entityManager.find(HotelEntity.class, 2L)).thenReturn(null);

        Optional<HotelEntity> result = hotelDAO.findById(2L);
        assertTrue(result.isEmpty());
        verify(entityManager, times(1)).find(HotelEntity.class, 2L);
    }

    @Test
    public void testUpdateHotel_Success() {
        doNothing().when(entityManager).merge(mockHotelEntity);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        assertDoesNotThrow(() -> hotelDAO.updateHotel(mockHotelEntity));

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).merge(mockHotelEntity);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testUpdateHotel_ExceptionRollback() {
        doThrow(new RuntimeException("Update error")).when(entityManager).merge(mockHotelEntity);
        when(transaction.isActive()).thenReturn(true);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            hotelDAO.updateHotel(mockHotelEntity);
        });

        assertTrue(exception.getMessage().contains("Error updating Hotel"));
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).rollback();
    }

    @Test
    public void testDeleteById_Success() {
        when(entityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);
        doNothing().when(entityManager).remove(mockHotelEntity);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        assertDoesNotThrow(() -> hotelDAO.deleteById(1L));

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).find(HotelEntity.class, 1L);
        verify(entityManager, times(1)).remove(mockHotelEntity);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testDeleteById_NotFound() {
        when(entityManager.find(HotelEntity.class, 2L)).thenReturn(null);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        assertDoesNotThrow(() -> hotelDAO.deleteById(2L));

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).find(HotelEntity.class, 2L);
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testDeleteById_ExceptionRollback() {
        when(entityManager.find(HotelEntity.class, 1L)).thenReturn(mockHotelEntity);
        doThrow(new RuntimeException("Delete error")).when(entityManager).remove(mockHotelEntity);
        when(transaction.isActive()).thenReturn(true);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            hotelDAO.deleteById(1L);
        });

        assertTrue(exception.getMessage().contains("Error deleting Hotel"));
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).rollback();
    }
}
